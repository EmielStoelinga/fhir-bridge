package org.ehrbase.fhirbridge.ehr.converter;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.prozedurcomposition.ProzedurComposition;
import org.ehrbase.fhirbridge.ehr.opt.prozedurcomposition.definition.DetailsZurKoerperstelleCluster;
import org.ehrbase.fhirbridge.ehr.opt.prozedurcomposition.definition.ProzedurAction;
import org.hl7.fhir.r4.model.Annotation;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Procedure;

import java.util.ArrayList;
import java.util.List;

public class ProcedureCompositionConverter implements CompositionConverter<ProzedurComposition, Procedure> {

    @Override
    public Procedure fromComposition(ProzedurComposition composition) {
        if (composition == null) {
            return null;
        }

        Procedure result = new Procedure();
        result.setId(composition.getVersionUid().toString());
        return result;
    }

    @Override
    public ProzedurComposition toComposition(Procedure procedure) {
        if (procedure == null) {
            return null;
        }

        ProzedurComposition result = new ProzedurComposition();

        // set feeder audit
        FeederAudit fa = CommonData.constructFeederAudit(procedure);
        result.setFeederAudit(fa);

        Coding code = procedure.getCode().getCoding().get(0);

        DateTimeType performed = procedure.getPerformedDateTimeType();

        Coding bodySite = null;
        List<CodeableConcept> bodySites = procedure.getBodySite();
        if (!bodySites.isEmpty()) {
            CodeableConcept bodySiteCodes = bodySites.get(0); // could be empty
            if (bodySiteCodes != null) {
                bodySite = bodySiteCodes.getCoding().get(0);
            }
        }

        Annotation note = null;
        List<Annotation> notes = procedure.getNote();
        if (!notes.isEmpty()) {
            note = procedure.getNote().get(0); // could be empty
        }

        ProzedurAction action = new ProzedurAction();
        action.setTimeValue(performed.getValueAsCalendar().toZonedDateTime());
        action.setNameDerProzedurValue(code.getDisplay());

        if (note != null) {
            action.setFreitextbeschreibungValue(note.getText());
        }

        // anatomical location
        if (bodySite != null) {
            DetailsZurKoerperstelleCluster anatomicalLocationCluster = new DetailsZurKoerperstelleCluster();

            // mapping
            anatomicalLocationCluster.setNameDerKoerperstelleValue(bodySite.getDisplay());

            action.setDetailsZurKoerperstelle(new ArrayList<>());
            action.getDetailsZurKoerperstelle().add(anatomicalLocationCluster);
        }

        // mandatory ism_transition

        action.setProzedurBeendetDefiningcode(org.ehrbase.fhirbridge.ehr.opt.shareddefinition.ProzedurBeendetDefiningcode.COMPLETED);
        action.setProzedurBeendetDefiningcodeCareflowStep(org.ehrbase.fhirbridge.ehr.opt.prozedurcomposition.definition.ProzedurBeendetDefiningcode.PROZEDUR_BEENDET);

        // mandatory subject
        action.setSubject(new PartySelf());

        // mandatory langauge
        action.setLanguage(Language.DE);


        result.setProzedur(action);

        // ======================================================================================
        // Required fields by API
        result.setLanguage(Language.DE);
        result.setLocation("test");
        result.setSettingDefiningCode(Setting.EMERGENCY_CARE);
        result.setTerritory(Territory.DE);
        result.setCategoryDefiningCode(Category.EVENT);

        result.setStartTimeValue(performed.getValueAsCalendar().toZonedDateTime());

        // https://github.com/ehrbase/ehrbase_client_library/issues/31
        PartyIdentified composer = new PartyIdentified();
        DvIdentifier identifier = new DvIdentifier();
        identifier.setId(procedure.getRecorder().getReference()); // TODO: if there is no recorder, try with the performer
        composer.addIdentifier(identifier);
        result.setComposer(composer);

        return result;
    }
}

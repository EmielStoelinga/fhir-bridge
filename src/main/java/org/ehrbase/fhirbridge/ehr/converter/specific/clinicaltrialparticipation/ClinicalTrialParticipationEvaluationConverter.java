package org.ehrbase.fhirbridge.ehr.converter.specific.clinicaltrialparticipation;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToEvaluationConverter;
import org.ehrbase.fhirbridge.ehr.converter.parser.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.definition.GeccoStudienteilnahmeEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.definition.RegisternameDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.definition.StudiePruefungCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.definition.StudienteilnahmeCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.definition.StudiePruefungRegistrierungCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.definition.TitelDerStudiePruefungDefiningCode;
import org.hl7.fhir.r4.model.Observation;

import java.util.List;

import static org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem.SNOMED;

public class ClinicalTrialParticipationEvaluationConverter extends ObservationToEvaluationConverter<GeccoStudienteilnahmeEvaluation> {

    @Override
    protected GeccoStudienteilnahmeEvaluation convertInternal(Observation resource) {
        GeccoStudienteilnahmeEvaluation geccoStudienteilnahmeEvaluation = new GeccoStudienteilnahmeEvaluation();
        DvCodedTextParser.parseFHIRCoding(resource.getValueCodeableConcept().getCoding().get(0)).ifPresent(geccoStudienteilnahmeEvaluation::setBereitsAnInterventionellenKlinischenStudienTeilgenommen);
        if(geccoStudienteilnahmeEvaluation.getBereitsAnInterventionellenKlinischenStudienTeilgenommen().getDefiningCode().getCodeString().equals("373066001")) {
            geccoStudienteilnahmeEvaluation.setStudienteilnahme(createStudyCluster(resource));
        }
        return geccoStudienteilnahmeEvaluation;
    }


    private StudienteilnahmeCluster createStudyCluster(Observation resource){
        StudienteilnahmeCluster studienteilnahmeCluster = new StudienteilnahmeCluster();
        StudiePruefungCluster studiePruefungCluster = new StudiePruefungCluster();
        studienteilnahmeCluster.setStudiePruefung(studiePruefungCluster);
        studiePruefungCluster.setTitelDerStudiePruefungDefiningCode(TitelDerStudiePruefungDefiningCode.PARTICIPATION_IN_INTERVENTIONAL_CLINICAL_TRIALS);

        if(resource.getCode().hasText()) {
            studiePruefungCluster.setBeschreibungValue(resource.getCode().getText());
        }

        if(resource.hasComponent()) {
            studiePruefungCluster.setRegistrierung(createRegistryCluster(resource));
        }

        return studienteilnahmeCluster;
    }

    private List<StudiePruefungRegistrierungCluster> createRegistryCluster(Observation resource) {
        StudiePruefungRegistrierungCluster studiePruefungRegistrierungCluster = new StudiePruefungRegistrierungCluster();

        for (Observation.ObservationComponentComponent observationComponent
                : resource.getComponent()) {
            mapRegistername(observationComponent, studiePruefungRegistrierungCluster);
            studiePruefungRegistrierungCluster.setRegistrierungsnummerValue(observationComponent.getValueStringType().getValue());
        }
        return List.of(studiePruefungRegistrierungCluster);
    }

    private void mapRegistername(Observation.ObservationComponentComponent observationComponent, StudiePruefungRegistrierungCluster studiePruefungRegistrierungCluster) {
        if(observationComponent.getCode().getCoding().get(0).getCode().equals("04")) {
            studiePruefungRegistrierungCluster.setRegisternameDefiningCode(RegisternameDefiningCode.EUDRACT_NUMBER);
        }else if(observationComponent.getCode().getCoding().get(0).getCode().equals("05")) {
            studiePruefungRegistrierungCluster.setRegisternameDefiningCode(RegisternameDefiningCode.NCT_NUMBER);
        }else{
            throw new ConversionException("value code " + observationComponent.getCode().getCoding().get(0).getCode() + " is not supported");
        }
    }

    private void checkForSnomedSystem(String systemCode) {
        if (!SNOMED.getUrl().equals(systemCode)) {
            throw new ConversionException("The system is not correct. " +
                    "It should be '" + SNOMED.getUrl() + "', but it was '" + systemCode + "'.");
        }
    }
}
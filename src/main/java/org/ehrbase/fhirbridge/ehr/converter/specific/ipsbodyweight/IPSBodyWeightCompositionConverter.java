package org.ehrbase.fhirbridge.ehr.converter.specific.ipsbodyweight;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.ipsbodyweight.internationalpatientsummaryonlybodyweightcomposition.InternationalPatientSummaryOnlyBodyWeightComposition;
import org.ehrbase.fhirbridge.ehr.opt.ipsbodyweight.internationalpatientsummaryonlybodyweightcomposition.definition.BodyWeightObservation;
import org.ehrbase.fhirbridge.ehr.opt.ipsbodyweight.internationalpatientsummaryonlybodyweightcomposition.definition.BodyWeightAnyEventChoice;
import org.ehrbase.fhirbridge.ehr.opt.ipsbodyweight.internationalpatientsummaryonlybodyweightcomposition.definition.BodyWeightAnyEventIntervalEvent;
import org.ehrbase.fhirbridge.ehr.opt.ipsbodyweight.internationalpatientsummaryonlybodyweightcomposition.definition.BodyWeightAnyEventPointEvent;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

public class IPSBodyWeightCompositionConverter
        extends ObservationToCompositionConverter<InternationalPatientSummaryOnlyBodyWeightComposition> {

    @Override
    public InternationalPatientSummaryOnlyBodyWeightComposition convertInternal(@NonNull Observation resource) {
        InternationalPatientSummaryOnlyBodyWeightComposition composition = new InternationalPatientSummaryOnlyBodyWeightComposition();
        // todo: suppor interval events
        BodyWeightAnyEventPointEvent pointEvent = new BodyWeightAnyEventPointEvent();
        pointEvent.setWeightMagnitude(resource.getValueQuantity().getValue().doubleValue());
        List<BodyWeightAnyEventChoice> eventlist = new ArrayList<>();
        eventlist.add(pointEvent);
        BodyWeightObservation bodyWeightObservation = new BodyWeightObservation();
        bodyWeightObservation.setAnyEvent(eventlist);
        composition.setBodyWeight(bodyWeightObservation);
        return composition;
    }

}

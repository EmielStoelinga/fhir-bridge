package org.ehrbase.fhirbridge.ehr.converter.specific.ipsbodyweight;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.ipsbodyweight.internationalpatientsummaryonlybodyweightcomposition.InternationalPatientSummaryOnlyBodyWeightComposition;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

public class IPSBodyWeightCompositionConverter
        extends ObservationToCompositionConverter<InternationalPatientSummaryOnlyBodyWeightComposition> {

    @Override
    public InternationalPatientSummaryOnlyBodyWeightComposition convertInternal(@NonNull Observation resource) {
        InternationalPatientSummaryOnlyBodyWeightComposition composition = new InternationalPatientSummaryOnlyBodyWeightComposition();
        composition.setBodyWeight(new IPSBodyWeightObservationConverter().convert(resource));

        return composition;
    }

}

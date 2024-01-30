package org.ehrbase.fhirbridge.ehr.converter.specific.ipsbodyweight;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.ipsbodyweight.IPSBodyWeightComposition;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

public class IPSBodyWeightCompositionConverter
        extends ObservationToCompositionConverter<IPSBodyWeightComposition> {

    @Override
    public IPSBodyWeightComposition convertInternal(@NonNull Observation resource) {
        IPSBodyWeightComposition composition = new IPSBodyWeightComposition();
        composition.setBodyWeight(new IPSBodyWeightObservationConverter().convert(resource));

        return composition;
    }

}

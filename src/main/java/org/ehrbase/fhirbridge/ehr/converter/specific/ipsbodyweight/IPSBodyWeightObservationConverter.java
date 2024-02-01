package org.ehrbase.fhirbridge.ehr.converter.specific.ipsbodyweight;

import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.ipsbodyweight.definition.BodyWeightAnyEventChoice;
import org.ehrbase.fhirbridge.ehr.opt.ipsbodyweight.definition.BodyWeightObservation;
import org.ehrbase.fhirbridge.ehr.opt.ipsbodyweight.definition.BodyWeightAnyEventPointEvent;
import org.hl7.fhir.r4.model.Observation;

import java.util.ArrayList;

public class IPSBodyWeightObservationConverter extends ObservationToObservationConverter<BodyWeightObservation> {

    @Override
    protected BodyWeightObservation convertInternal(Observation resource) {
        BodyWeightObservation observation = new BodyWeightObservation();

        ArrayList<BodyWeightAnyEventChoice> eventList = new ArrayList<>();
        BodyWeightAnyEventPointEvent event = new BodyWeightAnyEventPointEvent();
        if (resource.hasValueQuantity()) {
            IPSBodyWeightPointEventConverter eventConverter = new IPSBodyWeightPointEventConverter();
            event = eventConverter.convert(resource);
        } else {
            event.setWeightNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        }
        eventList.add(event);
        observation.setAnyEvent(eventList);

        return observation;
    }
}

package org.ehrbase.fhirbridge.ehr.converter.specific.ipsbodyweight;

import java.util.Optional;

import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToPointEventConverter;
import org.ehrbase.fhirbridge.ehr.opt.ipsbodyweight.definition.BodyWeightAnyEventPointEvent;
import org.hl7.fhir.r4.model.Observation;

public class IPSBodyWeightPointEventConverter extends ObservationToPointEventConverter<BodyWeightAnyEventPointEvent> {

    @Override
    protected BodyWeightAnyEventPointEvent convertInternal(Observation resource) {
        BodyWeightAnyEventPointEvent event = new BodyWeightAnyEventPointEvent();
        if (resource.hasValueQuantity()) {
            mapWeightUnits(resource).ifPresent(event::setWeightUnits);
            mapWeightMagnitude(resource).ifPresent(event::setWeightMagnitude);
        } else {
            event.setWeightNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        }
        return event;
    }

    private Optional<String> mapWeightUnits(Observation resource) {
        if (resource.hasValueQuantity()) {
            return Optional.of("kg");
        } else {
            return Optional.empty();
        }
    }

    private Optional<Double> mapWeightMagnitude(Observation resource) {
        if (resource.hasValueQuantity()) {
            return parseWeight(resource);
        } else {
            return Optional.empty();
        }
    }

    private Optional<Double> parseWeight(Observation resource) {
        if (resource.getValueQuantity().getCode().equals("kg")) {
            return Optional.of(resource.getValueQuantity().getValue().doubleValue());
        } else if (resource.getValueQuantity().getCode().equals("[lb_av]")) {
            Double poundInKg = resource.getValueQuantity().getValue().doubleValue() * 0.45359237;
            return Optional.of(poundInKg);
        } else { // gramms
            Double gInKg = resource.getValueQuantity().getValue().doubleValue() / 1000;
            return Optional.of(gInKg);
        }
    }

}

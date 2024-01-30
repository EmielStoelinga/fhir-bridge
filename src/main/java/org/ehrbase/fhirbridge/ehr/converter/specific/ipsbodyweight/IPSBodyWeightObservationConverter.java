package org.ehrbase.fhirbridge.ehr.converter.specific.ipsbodyweight;

import java.util.List;
import java.util.Optional;

import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.ipsbodyweight.internationalpatientsummaryonlybodyweightcomposition.definition.BodyWeightAnyEventChoice;
import org.ehrbase.fhirbridge.ehr.opt.ipsbodyweight.internationalpatientsummaryonlybodyweightcomposition.definition.BodyWeightObservation;
import org.ehrbase.fhirbridge.ehr.opt.ipsbodyweight.internationalpatientsummaryonlybodyweightcomposition.definition.BodyWeightAnyEventPointEvent;
import org.hl7.fhir.r4.model.Observation;

import java.util.ArrayList;

public class IPSBodyWeightObservationConverter extends ObservationToObservationConverter<BodyWeightObservation> {

    @Override
    protected BodyWeightObservation convertInternal(Observation resource) {
        BodyWeightObservation observation = new BodyWeightObservation();
        if (resource.hasValueQuantity()) {
            mapEvents(resource).ifPresent(observation::setAnyEvent);
        } else {
            List<BodyWeightAnyEventChoice> eventList = new ArrayList<>();
            BodyWeightAnyEventPointEvent event = new BodyWeightAnyEventPointEvent();
            event.setWeightNullFlavourDefiningCode(NullFlavour.UNKNOWN);
            eventList.add(event);
            observation.setAnyEvent(eventList);
        }
        return observation;
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

    private Optional<List<BodyWeightAnyEventChoice>> mapEvents(Observation resource) {
        if (resource.hasValueQuantity()) {
            // todo: map other events and intervals
            BodyWeightAnyEventPointEvent event = new BodyWeightAnyEventPointEvent();
            mapWeightUnits(resource).ifPresent(event::setWeightUnits);
            mapWeightMagnitude(resource).ifPresent(event::setWeightMagnitude);
            List<BodyWeightAnyEventChoice> eventList = new ArrayList<>();
            eventList.add(event);

            return Optional.of(eventList);
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
        } else { //gramms
            Double gInKg = resource.getValueQuantity().getValue().doubleValue() / 1000;
            return Optional.of(gInKg);
        }
    }
    
}

package org.ehrbase.fhirbridge.fhir.observation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.time.temporal.TemporalAccessor;
import java.util.List;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.ipsbodyweight.IPSBodyWeightCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.ipsbodyweight.IPSBodyWeightComposition;
import org.ehrbase.fhirbridge.ehr.opt.ipsbodyweight.definition.BodyWeightAnyEventPointEvent;
import org.ehrbase.fhirbridge.ehr.opt.ipsbodyweight.definition.BodyWeightObservation;
import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
import org.hl7.fhir.r4.model.Observation;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.metamodel.clazz.ValueObjectDefinition;
import org.junit.jupiter.api.Test;

public class IPSBodyWeightIT extends AbstractMappingTestSetupIT{
    
    public IPSBodyWeightIT() {
        super("Observation/VitalSigns/", Observation.class); //fhir-Resource
    }

    @Test
    void createBodyWeight() throws IOException {
        create("vital-signs-bodyweight.json");
    }

    @Test
    void testBodyWeightMagnitudeMin() throws IOException {
        testMapping("vital-signs-bodyweight_magnitude-min.json",
                "paragon-vital-signs-bodyweight_magnitude-min.json");
    }

    @Test
    void testBodyWeightMagnitudeMax() throws IOException {
        testMapping("vital-signs-bodyweight_magnitude-max.json",
                "paragon-vital-signs-bodyweight_magnitude-max.json");
    }

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(IPSBodyWeightComposition.class, List.of("location", "feederAudit")))
                .registerValueObject(BodyWeightObservation.class)
                .registerValueObject(BodyWeightAnyEventPointEvent.class)
                .build();
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Observation obs = (Observation) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () ->
                new IPSBodyWeightCompositionConverter().convert(obs)
        );
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Observation observation = (Observation) super.testFileLoader.loadResource(resourcePath);
        IPSBodyWeightCompositionConverter ipsBodyWeightCompositionConverter = new IPSBodyWeightCompositionConverter();
        IPSBodyWeightComposition mapped = ipsBodyWeightCompositionConverter.convert(observation);
        Diff diff = compareCompositions(getJavers(), paragonPath, mapped);
        assertEquals(0, diff.getChanges().size());
    }
}

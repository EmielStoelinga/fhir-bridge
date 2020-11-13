package org.ehrbase.fhirbridge.fhir;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.ICreateTyped;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DiagnosticReportIT {

    private final FhirContext context = FhirContext.forR4();

    private final IGenericClient client = context.newRestfulGenericClient("http://localhost:8888/fhir-bridge-poc/fhir/");

    @Test
    void create() throws IOException {
        String resource = IOUtils.toString(new ClassPathResource("DiagnosticReport/create-with-observation.json").getInputStream(), StandardCharsets.UTF_8);
        MethodOutcome outcome = client.create().resource(resource).execute();

        assertNotNull(outcome.getId());
        assertEquals(true, outcome.getCreated());
    }

    @Test
    void createWithDefaultProfile() throws IOException {
        String resource = IOUtils.toString(new ClassPathResource("DiagnosticReport/create-with-default-profile.json").getInputStream(), StandardCharsets.UTF_8);
        ICreateTyped createTyped = client.create().resource(resource);
        Exception exception = Assertions.assertThrows(UnprocessableEntityException.class, createTyped::execute);

        assertEquals("HTTP 422 : Default profile is not supported", exception.getMessage());
    }

    @Test
    void createWithInvalidCode() throws IOException {
        String resource = IOUtils.toString(new ClassPathResource("DiagnosticReport/create-with-invalid-code.json").getInputStream(), StandardCharsets.UTF_8);
        ICreateTyped createTyped = client.create().resource(resource);
        Exception exception = Assertions.assertThrows(UnprocessableEntityException.class, createTyped::execute);

        assertEquals("HTTP 422 : This element does not match any known slice defined in the profile https://www.medizininformatik-initiative.de/fhir/core/modul-labor/StructureDefinition/DiagnosticReportLab", exception.getMessage());
    }
}
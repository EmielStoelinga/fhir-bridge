package org.ehrbase.fhirbridge.camel.processor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.camel.Exchange;
import org.ehrbase.client.aql.query.Query;
import org.ehrbase.client.aql.record.Record1;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.fhirbridge.ehr.opt.ipsbodyweight.IPSBodyWeightComposition;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Quantity;
import org.hl7.fhir.r4.model.Reference;
import org.openehealth.ipf.commons.ihe.fhir.Constants;
import org.springframework.stereotype.Component;

import com.nedap.archie.rm.composition.Composition;

import ca.uhn.fhir.rest.api.RestOperationTypeEnum;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.param.HasParam;
import ca.uhn.fhir.rest.server.exceptions.InternalErrorException;
import kotlin.NotImplementedError;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(FindObservationOpenEHRProcessor.BEAN_ID)
public class FindObservationOpenEHRProcessor implements FhirRequestProcessor {

    public static final String BEAN_ID = "findObservationOpenEHRProcessor";

    private static final Logger LOG = LoggerFactory.getLogger(FindObservationOpenEHRProcessor.class);

    private final OpenEhrClient openEhrClient;

    public FindObservationOpenEHRProcessor(OpenEhrClient openEhrClient) {
        this.openEhrClient = openEhrClient;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        LOG.trace("Processing...");

        if (isSearchOperation(exchange)) {
            handleSearchOperation(exchange);
        } else {
            handleReadOperation(exchange);
        }
    }

    private void handleReadOperation(Exchange exchange) {
        // TODO Auto-generated method stub
        throw new NotImplementedError(BEAN_ID + " does not support read operations");
    }

    private void handleSearchOperation(Exchange exchange) {
        LOG.debug("Execute 'search' operation");

        List<String> codeValues;
        HasParam tmpParam;

        // these are all the inbound parameters as they come from the client
        /*
        Sample paramName and values:
        _summary:
          count
        _has:Encounter:patient:date:
          2020
        # if the same parameter name appears twice in the request, here we have one name with two values
        # for the 'code' attribute each value could represent a single or list of values, in both cases it is a string that should be parsed as CSV
        _has:Observation:patient:code:
          2160-0,14682-9,3091-6,22664-7
          1743-4,1742-6,30239-8,1920-8,88112-8
        _has:Condition:patient:code:
          C34.0,C34.1,C34.2,C34.3,C34.8,C34.9
        */
        RequestDetails request = getRequestDetails(exchange);
        Map<String, String[]> inParams = request.getParameters();

        // Gather all the information for the parameters including the templateId lookup result for the code params
        List<HasParamTemplate> outParams = new ArrayList<>();

        // aux structure to simplify creation of AQL queries based on templates
        // templateId => list of params which values will be included in the same AQL query
        // because of how this is constructed, only parameters for the 'code' attribute will
        // be in this map, so based on the template, resource type and attribute name (code),
        // we know which path to use in the AQL
        Map<String, List<HasParamTemplate>> templateParamMap = new LinkedHashMap<>();

        // Filters over observation attributes
        Map<String, String[]> observationParams = new HashMap<>();

        for (String paramName : inParams.keySet()) {
            String[] paramValues = inParams.get(paramName);

            if (paramName.startsWith("_profile")) {
                observationParams.put(paramName, paramValues);
            } else if (paramName.startsWith("subject.identifier")) {
                observationParams.put(paramName, paramValues);
            } else {
                LOG.warn("Parameter {} is not supported", paramName);
            }
        }

        int totalQueries = (observationParams.size() == 0 ? 0 : 1);
        int executedQueries = 0;

        // execute queries based on the processed parameters and intersect results
        // note for unsupported parameters, or for codes that don't have a matching template, no AQL will be
        // executed without throwing an exception
        Set<IPSBodyWeightComposition> compositions = new HashSet<>();
        Set<IPSBodyWeightComposition> tmpResult;
        boolean emptyResult = false;

        if (observationParams.size() > 0) {
            try {
                tmpResult = handleQueryForIPSVitalSigns(observationParams);

                if (tmpResult.isEmpty()) {
                    executedQueries++;
                    emptyResult = true;
                } else {
                    if (executedQueries == 0) {
                        compositions.addAll(tmpResult);
                    } else {
                        compositions.retainAll(tmpResult); // does an intersection ~ AND
                    }
                    executedQueries++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        LOG.info("Executed Queries: "+ executedQueries);
        LOG.info("Total Queries: "+ totalQueries);
        LOG.info("Empty Result: "+ emptyResult);

        // Create bundle result of Patient just with the subjectId from openEHR
        List<IBaseResource> result = compositions.stream()
                .map(composition -> convertCompositionToObservation(composition, observationParams.get("subject.identifier")[0]))
                .collect(Collectors.toList());

        exchange.getMessage().setBody(result);

        if (exchange.getIn().getHeaders().containsKey(Constants.FHIR_REQUEST_SIZE_ONLY)) {
            exchange.getMessage().setHeader(Constants.FHIR_REQUEST_SIZE_ONLY, result.size()); // required if param _summary=count
        } else {
            // FIXME: this is not paginated, could be big. Check how the pagination params are read.
            // Integer from = exchange.getIn().getHeader(Constants.FHIR_FROM_INDEX, Integer.class);
            // Integer to = exchange.getIn().getHeader(Constants.FHIR_TO_INDEX, Integer.class);
            exchange.getMessage().setBody(result);
            exchange.getMessage().setHeader(Constants.FHIR_REQUEST_SIZE_ONLY, result.size());
        }
    }

    private Observation convertCompositionToObservation(IPSBodyWeightComposition composition, String subjectId) {
        Observation observation = new Observation();
        // set newly created guid
        observation.setId(composition.getVersionUid().toString());

        observation.setStatus(new Observation.ObservationStatusEnumFactory().fromCode("final"));

        Coding code = new Coding();
        code.setCode("vital-signs");
        code.setSystem("http://hl7.org/fhir/StructureDefinition/vitalsigns");
        observation.setCategory(Collections.singletonList(new CodeableConcept().setCoding(Collections.singletonList(code))));
        observation.setCode(new CodeableConcept().setText("29463-7"));
        observation.setSubject(new Reference().setReference("Patient/" + subjectId));
        observation.setEffective(null);
        observation.setValue(new Quantity().setValue(composition.getBodyWeight().getAnyEvent().get(0).getWeightMagnitude()).setUnit("kg"));

        return observation;
    }

    private Set<IPSBodyWeightComposition> handleQueryForIPSVitalSigns(Map<String, String[]> observationParams) {
        Set<IPSBodyWeightComposition> compositions = new HashSet<>();

        // start building the AQL query and fill in the ehrId
        String ehrId = getEhrId(observationParams.get("subject.identifier")[0]);
        String aql = String.format("SELECT c from EHR e [ehr_id/value='%s'] CONTAINS COMPOSITION c", ehrId);
        
        // add the template to the query
        String where = " WHERE";

        if (observationParams.containsKey("_profile")) {
            if (observationParams.get("_profile")[0].equals("http://hl7.org/fhir/StructureDefinition/vitalsigns")) {
                where += " c/name/value='International Patient Summary - only body weight'";
            }
        }

        if (where.length() > 5) {
            aql += where;
        }

        LOG.info(aql);

        if (aql.contains("WHERE")) {

            // Execute the AQL
            Query<Record1<IPSBodyWeightComposition>> query = Query.buildNativeQuery(aql, IPSBodyWeightComposition.class);

            List<Record1<IPSBodyWeightComposition>> results = new ArrayList<>();

            try {
                results = this.openEhrClient.aqlEndpoint().execute(query);

                for (Record1<IPSBodyWeightComposition> record : results) {
                    compositions.add(record.value1());
                }
            } catch (Exception e) {
                throw new InternalErrorException("There was a problem retrieving the result", e);
            }
        }

        return compositions;
    }

    private String getEhrId(String subjectId) {
        String aql = String.format("select e/ehr_id/value as ehrId from EHR e WHERE e/ehr_status/subject/external_ref/id/value='%s'", subjectId);
        Query<Record1<String>> query = Query.buildNativeQuery(aql, String.class);

        List<Record1<String>> results = new ArrayList<>();
        try {
            results = this.openEhrClient.aqlEndpoint().execute(query);
        } catch (Exception e) {
            throw new InternalErrorException("There was a problem retrieving the ehrId", e);
        }

        return results.get(0).value1();
    }

    private boolean isSearchOperation(Exchange exchange) {
        RequestDetails requestDetails = getRequestDetails(exchange);
        return requestDetails.getRestOperationType() == RestOperationTypeEnum.SEARCH_TYPE;
    }

    /**
     * Represents one code matching a set of templateIds (the same code could be
     * referenced by
     * different paths on different templates)
     */
    private static class HasParamTemplate {

        private final String targetResource; // Observation, Encounter, Condition
        private final String targetParamName; // code, date
        private final String value; // code value or single value of the param name
        private final Set<String> templateIds; // template_ids matching any of the codes, can be empty if there are no
                                               // matches or if the paramName is not a code, or multiple if different
                                               // codes in the list match different templates

        public HasParamTemplate(String targetResource, String targetParamName, String value, Set<String> templateIds) {
            this.targetResource = targetResource;
            this.targetParamName = targetParamName;
            this.value = value;
            this.templateIds = templateIds;
        }

        public String getTargetResource() {
            return targetResource;
        }

        public String getTargetParamName() {
            return targetParamName;
        }

        public String getValue() {
            return value;
        }

        public Set<String> getTemplateIds() {
            return templateIds;
        }
    }

}

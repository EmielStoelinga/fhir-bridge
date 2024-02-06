package org.ehrbase.fhirbridge.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "fhir-bridge.search")
public class FhirSearchProperties {

    private SearchMode searchMode = SearchMode.DATABASE;

    public SearchMode getSearchMode() {
        return searchMode;
    }

    public void setSearchMode(SearchMode searchMode) {
        this.searchMode = searchMode;
    }

    public boolean isDatabaseSearch() {
        return this.searchMode == SearchMode.DATABASE;
    }

    public enum SearchMode {
        DATABASE, OPENEHR
    }
}

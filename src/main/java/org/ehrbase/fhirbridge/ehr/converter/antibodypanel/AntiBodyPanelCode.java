package org.ehrbase.fhirbridge.ehr.converter.antibodypanel;

public enum AntiBodyPanelCode {
    LABRATORY_STUDIES_CODE("http://loinc.org", "26436-6");

    private final String system;
    private final String code;


    AntiBodyPanelCode( String system, String code) {
        this.code = code;
        this.system = system;
    }

    public String getSystem() {
        return system;
    }

    public String getCode() {
        return code;
    }
}

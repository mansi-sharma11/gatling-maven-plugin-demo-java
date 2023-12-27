package fhirproprietaryapi;

public enum FhirCredentials {
    FHIR_V2_PATIENT("/fhir/v2/Patient"),
    FHIR_DERM_USERNAME("fhir_fVYdX"),
    FHIR_DERM_PASSWORD("5RO07FMOaY");


    private final String value;

    FhirCredentials(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}


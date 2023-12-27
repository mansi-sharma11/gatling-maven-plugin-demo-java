package fhirproprietaryapi;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static fhirproprietaryapi.FhirCredentials.FHIR_DERM_PASSWORD;
import static fhirproprietaryapi.FhirCredentials.FHIR_DERM_USERNAME;
import static fhirproprietaryapi.FhirCredentials.FHIR_V2_PATIENT;
import static io.gatling.javaapi.core.CoreDsl.rampUsers;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.core.CoreDsl.stressPeakUsers;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class FhirPatientGetSimulation extends Simulation {
    HttpProtocolBuilder httpProtocol = http.baseUrl("https://derm.m2perf.com/ema")
        .acceptHeader("application/json");

    ScenarioBuilder getPatient = scenario("Get Fhir Patient")
        .exec(http("Get Patient")
            .get(FHIR_V2_PATIENT.value())
            .basicAuth(FHIR_DERM_USERNAME.value(), FHIR_DERM_PASSWORD.value())
            .check(status().is(200))
        );

    {
        setUp(getPatient.injectOpen(
            rampUsers(25).during(60),
            stressPeakUsers(5).during(30)).protocols(httpProtocol));
    }
}




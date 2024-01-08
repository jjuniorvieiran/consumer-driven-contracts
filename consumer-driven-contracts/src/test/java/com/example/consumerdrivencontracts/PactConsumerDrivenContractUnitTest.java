package com.example.consumerdrivencontracts;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.model.RequestResponsePact;

import java.util.HashMap;
import java.util.Map;

@PactTestFor(providerName = "test_provider", hostInterface = "localhost")
class PactConsumerDrivenContractUnitTest {

    @Pact(provider = "test_provider", consumer = "test_consumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        return builder
                .given("test GET")
                    .uponReceiving("GET REQUEST")
                    .path("/pact").method("GET")
                .willRespondWith().status(200)
                    .headers(headers)
                    .body("{\"condition\": true, \"name\": \"tom\"}")
                .given("test POST")
                    .uponReceiving("POST REQUEST")
                    .method("POST").headers(headers)
                    .body("{\"name\": \"Michael\"}")
                    .path("/pact")
                .willRespondWith().status(201).toPact();
    }
}

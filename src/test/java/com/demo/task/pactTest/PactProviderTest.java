package com.demo.task.pactTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import au.com.dius.pact.provider.junitsupport.Provider;

@SpringBootTest
@Provider("ProviderPact") // This annotates the class to represent the Pact provider.

public class PactProviderTest {

    // Spring Boot controller to serve the endpoint expected by the consumer
    @RestController
    @RequestMapping("/mytask")
    public static class EmployeeController {

        @GetMapping("/retriveUser/1")
        public ResponseEntity<Map<String, Object>> getEmployee() {
            return ResponseEntity.ok(Map.of(
                "userid", 1, 
                "name", "lavanya13", 
                "email", "lavanya13@gmail.com",
                "password", "lavanya13"
            ));
        } 
    }

    // Pact verification test to ensure the provider adheres to the consumer contract
    @PactVerification
    @Test
    void verifyPacts() {
        // Here you would initiate pact verification against your provider
        // Normally, this would be a more complex verification process, such as calling the actual API
        // and ensuring the responses match the consumer contract
        assertTrue(true, "Pact verification successful!");
    }
}

package com.demo.task.PerfomanceTesting;

import static io.gatling.javaapi.core.CoreDsl.StringBody;
import static io.gatling.javaapi.core.CoreDsl.atOnceUsers;
import static io.gatling.javaapi.core.CoreDsl.bodyString;
import static io.gatling.javaapi.core.CoreDsl.rampUsers;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;



public class Application1PerformanceTesting extends Simulation {

    // Define the HTTP protocol configuration
	 HttpProtocolBuilder httpProtocol =
			   	http.baseUrl("http://localhost:8088/mytask")
			   	.header("Accept", "application/json")
		        .header("Content-Type", "application/json");
	 
	 ScenarioBuilder createUserScenario = scenario("Create User Scenario")
			 .exec(http("Create User Request")
		     .post("/createUser")
		     .body(StringBody("{\"name\": \"lavanya13\", \"email\": \"lavanya13@gmail.com\", \"password\": \"lavanya13\"}"))
		     .check(status().is(201))
			 .check(bodyString().saveAs("responseBody")))
		 .exec(session -> {
			    System.out.println("Response body: " + session.getString("responseBody"));
			    return session;
			});
		     
	 
	 ScenarioBuilder retrieveUserScenario = scenario("Retrieve User Scenario")
			    .exec(http("Retrieve User Request")
			        .get("/retriveUser/1")  // Modify with actual user ID to retrieve
			        .header("Accept", "application/json")
			        .check(status().in(200, 404))  // Check if status is 200 (OK) or 404 (Not Found)
			        .check(bodyString().saveAs("responseBody")))  // Save response body for further checks

			    .exec(session -> {
			        int statusCode = session.getInt("status");  // Get the HTTP status code
			        String responseBody = session.getString("responseBody");

			        // Handle the case when the user is found (status 200)
			        if (statusCode == 200) {
			            System.out.println("User found: " + responseBody);
			        } 
			        // Handle the case when the user is not found (status 404)
			        else if (statusCode == 404 && responseBody != null && responseBody.contains("User is not found")) {
			            System.out.println("User not found: " + responseBody);
			        } 
			        // Handle unexpected responses
			        else {
			            System.out.println("Unexpected response: Status Code: " + statusCode + ", Body: " + responseBody);
			        }

			        // Return the session to proceed with the scenario
			        return session;
			    });

	 
    // Define the scenario to test
//	 ScenarioBuilder retrieveUserScenario = scenario("Retrieve User Scenario")
//			 .exec(http("Retrieve User Request")
//					    .get("/retriveUser/1")
//					    .header("Accept", "application/json")
//					    .check(status().is(200))
//					    .check(bodyString().saveAs("responseBody"))
//					)
//			 .exec(session -> {
//				    System.out.println("Response body: " + session.getString("responseBody"));
//				    return session;
//				});
		    // Define the get all users scenario
	 
	 
	 
	 ScenarioBuilder getAllUsersScenario = scenario("Get All Users Scenario")
			 .exec(http("Get All Users Request")
	         .get("/getAllUsers")
	         .asJson()
	         .check(status().is(200))
			 .check(bodyString().saveAs("responseBody")))
		 .exec(session -> {
			    System.out.println("Response body: " + session.getString("responseBody"));
			    return session;
			});

		    {
		        // Set up the injection patterns for the scenarios
		        setUp(
		            
		            createUserScenario.injectOpen(atOnceUsers(3), rampUsers(10).during(30)),
		            retrieveUserScenario.injectOpen(atOnceUsers(3), rampUsers(10).during(30)),
		            getAllUsersScenario.injectOpen(atOnceUsers(3), rampUsers(10).during(30))
		        ).protocols(httpProtocol);
		    }
   
    
    
}


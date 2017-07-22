package com.ontestautomation.wiremock;

import org.junit.Rule;
import org.junit.Test;
import org.junit.Assert;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.jayway.restassured.RestAssured.*;

public class AFirstWireMockTest {
	
	String bodyText = "You've reached a valid WireMock endpoint";
	
	@Rule
	public WireMockRule wireMockRule = new WireMockRule(8090);
	
	@Test
	public void testStatusCodePositive() {
		
		setupStub();
		
		given().
		when().
			get("http://localhost:8090/an/endpoint").
		then().
			assertThat().statusCode(200);
	}
	
	@Test
	public void testStatusCodeNegative() {
		
		setupStub();
		
		given().
		when().
			get("http://localhost:8090/another/endpoint").
		then().
			assertThat().statusCode(404);
	}
	

	
	public void setupStub() {
		
		stubFor(get(urlEqualTo("/an/endpoint"))
	            .willReturn(aResponse()
	                .withHeader("Content-Type", "text/plain")
	                .withStatus(200)
	                .withBody(bodyText)));
		stubFor(get(urlEqualTo("/another/endpoint"))
	            .willReturn(aResponse()
	                .withHeader("Content-Type", "text/plain")
	                .withStatus(404)));
	}
}
package com.smartbrief.keywords;

import static com.jayway.restassured.RestAssured.expect;
import static com.xebialabs.restito.builder.stub.StubHttp.whenHttp;
import static com.xebialabs.restito.builder.verify.VerifyHttp.verifyHttp;
import static com.xebialabs.restito.semantics.Action.status;
import static com.xebialabs.restito.semantics.Condition.get;
import static com.xebialabs.restito.semantics.Condition.method;
import static com.xebialabs.restito.semantics.Condition.uri;

import org.glassfish.grizzly.http.Method;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.xebialabs.restito.server.StubServer;

public class WatsonHttpClientRestitoTest {
	private StubServer server;

	@Before
	public void setUp() throws Exception {
		server = new StubServer().run();
		RestAssured.port = server.getPort();
	}

	@After
	public void stop() {
		server.stop();
	}

	@Test
	public void shouldPassVerification() {
		whenHttp(server).match(get("/analyze?version=2018-03-16")).then(status(HttpStatus.OK_200));
		expect().statusCode(200).when().get("/analyze?version=2018-03-16");
		verifyHttp(server).once(method(Method.GET), uri("/analyze?version=2018-03-16"));
	}
}
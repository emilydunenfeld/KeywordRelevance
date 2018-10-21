package com.smartbrief.keywords;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class WatsonHttpClientTest {
	private static final String TEST_TEXT = "How many times have you wished you were compensated for the time you spend on the phone with clients? And how often have clients said they wished they had a better way of knowing whether an in-person visit to the office was really necessary? Probably more times than you can count";
	private static final String MOCK_URL = "http://mockapi.smartbrief.com:8080/mockapis/rest/watson-nlu/v1/analyze";
	private static final String WATSON_URL = "https://gateway.watsonplatform.net/natural-language-understanding/api/v1/analyze?version=2018-03-16";

	// tests watson response returned from watson http client is correct using the
	// test url
	@Test
	public void testExecute() throws Exception {
		WatsonRequest watsonRequest = new WatsonRequest("");
		WatsonHttpClient watsonHttpClient = new WatsonHttpClient(MOCK_URL);
		WatsonResponse value = watsonHttpClient.execute(watsonRequest);
		WatsonResponse.KeywordsJson keyword1 = new WatsonResponse.KeywordsJson(0.993518,
				"American multinational technology");
		WatsonResponse.KeywordsJson keyword2 = new WatsonResponse.KeywordsJson(0.613816, "New York");
		WatsonResponse expected = new WatsonResponse(Arrays.asList(keyword1, keyword2));
		assertEquals(expected, value);
	}

	// tests watson response returned from watson http client is correct using the
	// watson url
	@Test
	public void testAPIExecute() throws Exception {
		WatsonRequest watsonRequest = new WatsonRequest(TEST_TEXT, false);
		WatsonHttpClient watsonHttpClient = new WatsonHttpClient(WATSON_URL);
		WatsonResponse value = watsonHttpClient.execute(watsonRequest);
		WatsonResponse.KeywordsJson keyword1 = new WatsonResponse.KeywordsJson(0.947961, "in-person visit");
		WatsonResponse.KeywordsJson keyword2 = new WatsonResponse.KeywordsJson(0.763361, "better way");
		WatsonResponse expected = new WatsonResponse(Arrays.asList(keyword1, keyword2));
		assertEquals(expected, value);
	}
}
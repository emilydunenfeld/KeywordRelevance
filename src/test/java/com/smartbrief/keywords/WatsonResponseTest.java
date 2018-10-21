package com.smartbrief.keywords;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class WatsonResponseTest {
	private static final double testRelevance = 110.0;
	private static final String testText = "testText";

	@Before
	public void setUp() throws Exception {

	}

	// tests deserialization of watson response
	@Test
	public void testDeserialize() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		String json = "{\"keywords\": [ { \"text\": \"" + testText + "\", \"relevance\":\"" + testRelevance + "\"} ] }";
		WatsonResponse value = mapper.readValue(json, WatsonResponse.class);
		WatsonResponse.KeywordsJson testKeywords = new WatsonResponse.KeywordsJson(testRelevance, testText);
		WatsonResponse expected = new WatsonResponse(Arrays.asList(testKeywords));
		assertEquals(expected, value);
	}

	// tests conversion to list of keywords
	@Test
	public void testToKeywordList() {
		Keyword expected = new Keyword(testRelevance, testText);
		WatsonResponse.KeywordsJson testKeywords = new WatsonResponse.KeywordsJson(testRelevance, testText);
		WatsonResponse watsonResponse = new WatsonResponse(Arrays.asList(testKeywords));
		List<Keyword> value = watsonResponse.toKeywordList();
		assertEquals(Arrays.asList(expected), value);
	}
}
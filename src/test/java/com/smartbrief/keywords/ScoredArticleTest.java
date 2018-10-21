package com.smartbrief.keywords;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ScoredArticleTest {
	private static final String testHeadline = "testHeadline";
	private static final String testLeadText = "testLeadText";
	private static final String testFullBody = "testFullBody";

	@Before
	public void setUp() throws Exception {

	}

	// tests deserialization of scored article
	@Test
	public void testDeserialize() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		String json = "{" + "\"headline\": \"" + testHeadline + "\" ," + "\"leadText\": \"" + testLeadText + "\" ,"
				+ "\"fullBody\": \"" + testFullBody + "\"}";
		ScoredArticle value = mapper.readValue(json, ScoredArticle.class);
		ScoredArticle expected = new ScoredArticle(testHeadline, testFullBody, testLeadText);
		assertEquals(expected, value);
	}
}
package com.smartbrief.keywords;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class WatsonRequestTest {
	private static final String testText = "testText";

	@Before
	public void setUp() throws Exception {

	}

	// tests serialization of watson request
	@Test
	public void testSerialize() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		String expected = "{\"text\":\"" + testText + "\",\"features\":{\"keywords\":{}},\"language\":\"en\"}";
		WatsonRequest wr = new WatsonRequest(testText);
		String value = mapper.writeValueAsString(wr);
		assertEquals(expected, value);
	}
}
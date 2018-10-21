package com.smartbrief.keywords;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class AveragingResultHandlerTest {

	@Before
	public void setUp() throws Exception {
	}

	// tests that map returned by averaging result handler contains keywords matched
	// with correct relevances in proper order
	@Test
	public void testMap() {
		Collection<Keyword> keywords = new HashSet<>();
		Keyword keyword1 = new Keyword(0.947961, "in-person visit");
		Keyword keyword2 = new Keyword(0.763361, "better way");
		keywords.add(keyword1);
		keywords.add(keyword2);
		ScoredArticle scoredArticle = new ScoredArticle("", "", "");
		NodeKeywords articleKeywords = new NodeKeywords();
		articleKeywords.put(scoredArticle, keywords);
		ResultHandler<Map<String, Double>> resultHandler = new AveragingResultHandler();
		Map<String, Double> map = resultHandler.handleKeywords(articleKeywords);

		assertEquals(map.size(), 2);
		assertEquals(map.keySet().iterator().next(), keyword1.getText());
		assertEquals(map.get(keyword1.getText()), keyword1.getRelevance(), 0.000001);
		assertTrue(map.containsKey(keyword2.getText()));
		assertEquals(map.get(keyword2.getText()), keyword2.getRelevance(), 0.000001);
	}
}

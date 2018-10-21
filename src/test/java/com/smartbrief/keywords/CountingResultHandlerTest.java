package com.smartbrief.keywords;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Multiset;

public class CountingResultHandlerTest {

	@Before
	public void setUp() throws Exception {
	}

	// tests that multiset returned by counting result handler contains both
	// keywords
	@Test
	public void testMultiset() {
		Collection<Keyword> keywords = new HashSet<>();
		Keyword keyword1 = new Keyword(0.947961, "in-person visit");
		Keyword keyword2 = new Keyword(0.763361, "better way");
		keywords.add(keyword1);
		keywords.add(keyword2);
		ScoredArticle scoredArticle = new ScoredArticle("", "", "");
		NodeKeywords articleKeywords = new NodeKeywords();
		articleKeywords.put(scoredArticle, keywords);
		ResultHandler<Multiset<String>> resultHandler = new CountingResultHandler();
		Multiset<String> multiset = resultHandler.handleKeywords(articleKeywords);
		assertEquals(2, multiset.size());
		assertTrue(multiset.contains(keyword1.getText()));
		assertTrue(multiset.contains(keyword2.getText()));
	}
}
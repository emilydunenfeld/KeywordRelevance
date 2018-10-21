package com.smartbrief.keywords;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

public class KeywordServiceTest {
	private static final String TEST_TEXT = "How many times have you wished you were compensated for the time you spend on the phone with clients? And how often have clients said they wished they had a better way of knowing whether an in-person visit to the office was really necessary? Probably more times than you can count";
	private static final String MOCK_URL = "http://mockapi.smartbrief.com:8080/mockapis/rest/watson-nlu/v1/analyze";
	private static final String WATSON_URL = "https://gateway.watsonplatform.net/natural-language-understanding/api/v1/analyze?version=2018-03-16";
	private static final WatsonHttpClient MOCK_HTTP_CLIENT = new WatsonHttpClient(MOCK_URL);
	private static final WatsonHttpClient WATSON_HTTP_CLIENT = new WatsonHttpClient(WATSON_URL);
	@Before
	public void setUp() throws Exception {

	}

	// tests collection of keywords returned from keyword service contain the
	// correct keywords using the test url
	@Test
	public void testFindKeywords() throws Exception {
		KeywordService keywordService = new KeywordService(MOCK_HTTP_CLIENT);
		ScoredArticle scoredArticle = new ScoredArticle("", "", "");
		Collection<Keyword> keywords = keywordService.findKeywords(scoredArticle);
		Keyword keyword1 = new Keyword(0.993518, "American multinational technology");
		Keyword keyword2 = new Keyword(0.613816, "New York");
		assertTrue(keywords.contains(keyword1));
		assertTrue(keywords.contains(keyword2));
		assertTrue(keywords.contains(keyword2));
		assertEquals(2, keywords.size());
	}

	// tests collection of keywords returned from keyword service contain the
	// correct keywords using the watson url
	@Test
	public void testAPIFindKeywords() throws Exception {
		KeywordService keywordService = new KeywordService(WATSON_HTTP_CLIENT);
		ScoredArticle scoredArticle = new ScoredArticle("", TEST_TEXT, "");
		Collection<Keyword> keywords = keywordService.findKeywords(scoredArticle);
		Keyword keyword1 = new Keyword(0.947961, "in-person visit");
		Keyword keyword2 = new Keyword(0.763361, "better way");
		assertTrue(keywords.contains(keyword1));
		assertTrue(keywords.contains(keyword2));
		assertEquals(2, keywords.size());
	}

	// tests value of type ArticleKeywords returned by keyword service contain the
	// correct scored article and keyword pairing using the test url
	@Test
	public void findArticleKeywords() throws Exception {
		Collection<Keyword> keywords = new ArrayList<>();
		Keyword keyword1 = new Keyword(0.993518, "American multinational technology");
		Keyword keyword2 = new Keyword(0.613816, "New York");
		keywords.add(keyword1);
		keywords.add(keyword2);

		KeywordService keywordService = new KeywordService(MOCK_HTTP_CLIENT);
		ScoredArticle article1 = new ScoredArticle("", "", "");
		Collection<ScoredArticle> articles = new ArrayList<>();
		articles.add(article1);
		NodeKeywords value = keywordService.findKeywords(articles);

		assertTrue(value.asMap().containsEntry(article1, keywords));
		assertEquals(1, value.asMap().size());
	}

	// tests value of type ArticleKeywords returned by keyword service contain the
	// correct scored article and keyword pairing using the watson url
	@Test
	public void findAPIArticleKeywords() throws Exception {
		Collection<Keyword> keywords = new ArrayList<>();
		Keyword keyword1 = new Keyword(0.947961, "in-person visit");
		Keyword keyword2 = new Keyword(0.763361, "better way");
		keywords.add(keyword1);
		keywords.add(keyword2);

		KeywordService keywordService = new KeywordService(WATSON_HTTP_CLIENT);
		ScoredArticle article1 = new ScoredArticle("", TEST_TEXT, "");
		Collection<ScoredArticle> articles = new ArrayList<>();
		articles.add(article1);
		NodeKeywords value = keywordService.findKeywords(articles);
		assertTrue(value.asMap().containsEntry(article1, keywords));
		assertEquals(1, value.asMap().size());
	}
}
package com.smartbrief.keywords;

import java.util.Collection;

import org.springframework.stereotype.Component;

@Component
public class KeywordService {
	private final WatsonHttpClient watsonHttpClient;

	public KeywordService(WatsonHttpClient watsonHttpClient) {
		this.watsonHttpClient = watsonHttpClient;
	}

	public Collection<Keyword> findKeywords(ScoredArticle article) throws Exception {
		WatsonRequest watsonRequest = new WatsonRequest(article.getFullBody(), false);
		WatsonResponse watsonResponse = watsonHttpClient.execute(watsonRequest);
		return watsonResponse.toKeywordList();
	}

	public NodeKeywords findKeywords(Collection<ScoredArticle> articles) throws Exception {
		NodeKeywords nodeKeywords = new NodeKeywords();
		for (ScoredArticle article : articles) {
			nodeKeywords.put(article, findKeywords(article));
		}
		return nodeKeywords;
	}

	public <T> T findKeywords(Collection<ScoredArticle> articles, ResultHandler<T> resultHandler) throws Exception {
		NodeKeywords keywords = findKeywords(articles);
		return resultHandler.handleKeywords(keywords);
	}
}
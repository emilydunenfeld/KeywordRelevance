package com.smartbrief.keywords;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;

@Component
@ComponentScan("com.smartbrief.keywords")
@EnableAutoConfiguration
public class KeywordsApp {
	private final ArticleReader articleReader;
	private final KeywordService keywordService;

	public KeywordsApp(ArticleReader articleReader, KeywordService keywordService) {
		this.articleReader = articleReader;
		this.keywordService = keywordService;
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(KeywordsApp.class, args);
	}
	
	public void run(Collection<UUID> ids) throws Exception {
		for (UUID id : ids) {
			Multimap<NodeId, ScoredArticle> scoredArticlesByNode = articleReader.loadArticlesByNode(id);
			System.out.println(scoredArticlesByNode);

			Multiset<String> multiset = keywordService.findKeywords(scoredArticlesByNode.values(), new CountingResultHandler());
			System.out.println("sorted by count: " + multiset);

			Map<String, Double> map = keywordService.findKeywords(scoredArticlesByNode.values(), new AveragingResultHandler());
			System.out.println("sorted by relavence: " + map);
		}
	}
	
	public <T> T runForId(UUID id, ResultHandler<T> resultHandler) throws Exception {
		Multimap<NodeId, ScoredArticle> scoredArticlesByNode = articleReader.loadArticlesByNode(id);
		return keywordService.findKeywords(scoredArticlesByNode.values(), resultHandler);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
}
package com.smartbrief.keywords;

import java.util.Collection;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class NodeKeywords {
	private final Multimap<ScoredArticle, Collection<Keyword>> map = HashMultimap.create();
	
	public void put(ScoredArticle scoredArticle, Collection<Keyword> keywords) {
		map.put(scoredArticle, keywords);
	}

	public Multimap<ScoredArticle, Collection<Keyword>> asMap() {
		return map;
	}
}
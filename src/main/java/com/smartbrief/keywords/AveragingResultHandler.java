package com.smartbrief.keywords;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class AveragingResultHandler implements ResultHandler<Map<String, Double>> {
	@Override
	public Map<String, Double> handleKeywords(NodeKeywords articleKeywords) {
		Multiset<String> multiset = HashMultiset.create();
		Map<String, Double> keywordRel = new HashMap<String, Double>();

		// add values to map and use multiset to track count
		Collection<Collection<Keyword>> keyRevs = articleKeywords.asMap().values();
		for (Collection<Keyword> keyRev : keyRevs) {
			for (Keyword keyword : keyRev) {
				multiset.add(keyword.getText());
				if (keywordRel.containsKey(keyword.getText())) {
					keywordRel.put(keyword.getText(), keywordRel.get(keyword.getText()) + keyword.getRelevance());
				} else {
					keywordRel.put(keyword.getText(), keyword.getRelevance());
				}
			}
		}

		// average
		for (Map.Entry<String, Double> keyword : keywordRel.entrySet()) {
			keywordRel.put(keyword.getKey(), keyword.getValue() / multiset.count(keyword.getKey()));
		}

		// sort
		Map<String, Double> sortedKeywordRel = new LinkedHashMap<String, Double>();
		keywordRel.entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue().reversed())
				.forEach(keyword -> {
					sortedKeywordRel.put(keyword.getKey(), keyword.getValue());
				});

		return sortedKeywordRel;
	}
}
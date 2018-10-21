package com.smartbrief.keywords;

import java.util.Collection;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;

public class CountingResultHandler implements ResultHandler<Multiset<String>> {
	@Override
	public Multiset<String> handleKeywords(NodeKeywords articleKeywords) {
		Multiset<String> multiset = HashMultiset.create();
		Collection<Collection<Keyword>> keyRevs = articleKeywords.asMap().values();

		for (Collection<Keyword> keyRev : keyRevs) {
			for (Keyword keyword : keyRev) {
				multiset.add(keyword.getText());
			}
		}
		
		multiset = Multisets.copyHighestCountFirst(multiset);
		return multiset;
	}
}
package com.smartbrief.keywords;

import java.util.LinkedHashMap;
import java.util.Map;

import com.google.common.collect.Multiset;
import com.smartbrief.keywords.WebResult.KeywordInfo;

public class WebResultHandler implements ResultHandler<WebResult> {
	private final CountingResultHandler countingResultHandler = new CountingResultHandler();
	private final AveragingResultHandler averagingResultHandler = new AveragingResultHandler();
	private final Map<String, KeywordInfo> map = new LinkedHashMap<>();

	@Override
	public WebResult handleKeywords(NodeKeywords articleKeywords) {
		Multiset<String> multiset = countingResultHandler.handleKeywords(articleKeywords);
		Map<String, Double> averageScores = averagingResultHandler.handleKeywords(articleKeywords);
		if (multiset.entrySet().size() == averageScores.size()) {
				for(Map.Entry<String, Double> averageScore : averageScores.entrySet()) {
					KeywordInfo keywordInfo = new KeywordInfo(multiset.count(averageScore.getKey()), averageScore.getValue());
					map.put(averageScore.getKey(), keywordInfo);
				}
		} else {
			throw new RuntimeException();
		}
		return WebResult.fromMap(map);
	}
}
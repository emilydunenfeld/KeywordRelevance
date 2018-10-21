package com.smartbrief.keywords;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WebResult {
	@JsonProperty
	private final Map<String, KeywordInfo> data;

	public WebResult(Map<String, KeywordInfo> data) {
		this.data = data;
	}

	public static class KeywordInfo {
		@JsonProperty
		private final int count;
		@JsonProperty
		private final double averageScore;

		public KeywordInfo(int count, double averageScore) {
			this.count = count;
			this.averageScore = averageScore;
		}
	}

	static WebResult fromMap(Map<String, KeywordInfo> map) {
		return new WebResult(map);
	}
}
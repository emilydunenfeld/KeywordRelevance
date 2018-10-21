package com.smartbrief.keywords;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class WatsonResponse {
	private final List<KeywordsJson> keywords;

	@JsonCreator
	public WatsonResponse(@JsonProperty("keywords") List<KeywordsJson> keywords) {
		this.keywords = keywords;
	}

	public List<KeywordsJson> getKeywords() {
		return keywords;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	static class KeywordsJson {
		private Keyword keyword;

		@JsonCreator
		public KeywordsJson(@JsonProperty("relevance") double relevance, @JsonProperty("text") String text) {
			this.keyword = new Keyword(relevance, text);
		}

		@JsonProperty
		public double getRelevance() {
			return keyword.getRelevance();
		}

		@JsonProperty
		public String getText() {
			return keyword.getText();
		}

		@Override
		public int hashCode() {
			return keyword.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			KeywordsJson other = (KeywordsJson) obj;
			return Objects.equals(keyword, other.keyword);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(keywords);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		WatsonResponse other = (WatsonResponse) obj;
		return Objects.equals(keywords, other.keywords);
	}

	public List<Keyword> toKeywordList() {
		List<Keyword> keywordList = new ArrayList<>();
		for (KeywordsJson keyword : keywords) {
			// Keyword tempKeyword = new Keyword(keyword.getRelevance(), keyword.getText());
			keywordList.add(keyword.keyword);
		}
		return keywordList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (KeywordsJson keyword : keywords) {
			builder.append("[" + keyword.getText() + "][" + keyword.getRelevance() + "]");
		}
		return getClass().getSimpleName() + builder;
	}
}
package com.smartbrief.keywords;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class ScoredArticle {
	private final String headline;
	private final String fullBody;
	private final String leadText;

	@JsonCreator
	public ScoredArticle(@JsonProperty("headline") String headline, @JsonProperty("fullBody") String fullBody,
			@JsonProperty("leadText") String leadText) {
		this.headline = headline;
		this.fullBody = fullBody;
		this.leadText = leadText;
	}

	public String getHeadline() {
		return headline;
	}

	public String getFullBody() {
		return fullBody.replaceAll("[^\\n\\r\\t\\p{Print}]", "");
	}

	public String getLeadText() {
		return leadText;
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
		ScoredArticle other = (ScoredArticle) obj;
		return Objects.equals(headline, other.headline) && Objects.equals(leadText, other.leadText)
				&& Objects.equals(fullBody, other.fullBody);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[" + headline + "]";
	}
}
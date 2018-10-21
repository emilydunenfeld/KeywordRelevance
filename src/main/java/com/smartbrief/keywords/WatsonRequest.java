package com.smartbrief.keywords;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRawValue;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class WatsonRequest {
	private static final String KEYWORDS_PRODUCTION = "{\"keywords\":{}}";
	private static final String KEYWORDS_TEST = "{\"keywords\":{\"limit\": 2}}";

	private final String text;
	@JsonRawValue
	private final String features;
	private static final String language = "en";

	public WatsonRequest(String text) {
		this(text, true);
	}

	WatsonRequest(String text, boolean production) {
		this.text = text;
		this.features = production ? KEYWORDS_PRODUCTION : KEYWORDS_TEST;
	}

	public String getText() {
		return text;
	}

	public String getFeatures() {
		return features;
	}

	public String getLanguage() {
		return language;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[" + text + "]" + "[" + features + "]" + "[" + language + "]";
	}
}
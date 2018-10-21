package com.smartbrief.keywords;

import java.util.Objects;

public class Keyword {
	private final double relevance;
	private final String text;

	public Keyword(double relevance, String text) {
		this.relevance = relevance;
		this.text = text;
	}

	public double getRelevance() {
		return relevance;
	}

	public String getText() {
		return text;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getRelevance(), getText());
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
		Keyword other = (Keyword) obj;
		return Objects.equals(getRelevance(), other.getRelevance()) && Objects.equals(getText(), other.getText());
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[" + getText() + "] [" + getRelevance() + "]";
	}
}
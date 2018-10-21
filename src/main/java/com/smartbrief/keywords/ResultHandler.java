package com.smartbrief.keywords;

public interface ResultHandler<T> {
	T handleKeywords(NodeKeywords articleKeywords);
}
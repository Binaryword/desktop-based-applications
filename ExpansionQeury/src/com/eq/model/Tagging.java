package com.eq.model;

import java.util.List;

public class Tagging {

	private String word;
	private List<String> tags;

	public Tagging(String word, List<String> tags) {
		this.word = word;
		this.tags = tags;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public String toString() {

		return String.format("word (%s) ---- > tag (Expansion) %s", getWord(), getTags());
	}

}

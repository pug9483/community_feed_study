package com.sample.post.domain;

public class PostContent {
	private final String content;

	public PostContent(String content) {
		if (content == null || content.length() < 5 || content.length() > 500) {
			throw new IllegalArgumentException();
		}
		this.content = content;
	}

	public String getContent() {
		return content;
	}
}

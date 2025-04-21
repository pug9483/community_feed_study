package com.sample.post.domain.comment;

import com.sample.post.domain.Post;
import com.sample.post.domain.content.Content;
import com.sample.user.domain.User;

public class Comment {
	private final Long id;
	private final Post post;
	private final User Author;
	private final Content content;

	public Comment(Long id, Post post, User author, Content content) {
		if (author == null) {
			throw new IllegalArgumentException();
		}

		if (post == null) {
			throw new IllegalArgumentException();
		}

		if (content == null) {
			throw new IllegalArgumentException();
		}

		this.id = id;
		this.post = post;
		this.Author = author;
		this.content = content;
	}
}

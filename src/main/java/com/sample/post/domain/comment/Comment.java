package com.sample.post.domain.comment;

import com.sample.common.domain.PositiveIntegerCounter;
import com.sample.post.domain.content.CommentContent;
import com.sample.post.domain.post.Post;
import com.sample.post.domain.content.Content;
import com.sample.user.domain.User;

public class Comment {
	private final Long id;
	private final Post post;
	private final User author;
	private final Content content;
	private final PositiveIntegerCounter likeCount;

	public static Comment createComment(Post post, User author, String content) {
		return new Comment(null, post, author, new CommentContent(content));
	}

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
		this.author = author;
		this.content = content;
		this.likeCount = new PositiveIntegerCounter();
	}

	public void updateComment(User user, String updateContent) {
		if (!this.author.equals(user)) {
			throw new IllegalArgumentException();
		}

		this.content.updateContent(updateContent);
	}

	public void like(User user) {
		if (this.author.equals(user)) {
			throw new IllegalArgumentException();
		}
		this.likeCount.increase();
	}

	public void unlike() {
		this.likeCount.decrease();
	}

	public int getLikeCount() {
		return likeCount.getCount();
	}

	public String getContent() {
		return content.getContentText();
	}
}

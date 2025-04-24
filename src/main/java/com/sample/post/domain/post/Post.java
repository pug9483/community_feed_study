package com.sample.post.domain.post;

import com.sample.common.domain.PositiveIntegerCounter;
import com.sample.post.domain.content.Content;
import com.sample.post.domain.content.PostContent;
import com.sample.post.domain.content.PostPublicationState;
import com.sample.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import static com.sample.post.domain.content.PostPublicationState.PUBLIC;

@Builder
@Getter
@EqualsAndHashCode
public class Post {
	private final Long id;
	private final User author;
	private final Content content;
	private PostPublicationState state;
	private final PositiveIntegerCounter likeCount;

	@Builder
	public Post(Long id, User author, Content content, PostPublicationState state, PositiveIntegerCounter positiveIntegerCounter) {
		if (author == null) {
			throw new IllegalArgumentException("author should not be null");
		}
		if (content == null) {
			throw new IllegalArgumentException("content should not be null or empty");
		}

		this.id = id;
		this.author = author;
		this.content = content;
		this.state = state;
		this.likeCount = positiveIntegerCounter;
	}

	public Post(Long id, User author, Content content) {
		this(id, author, content, PUBLIC, new PositiveIntegerCounter());
	}

	public Post(Long id, User author, String content) {
		this(id, author, new PostContent(content), PUBLIC, new PositiveIntegerCounter());
	}

	public void updatePost(User user, String updateContent, PostPublicationState state) {
		if (!this.author.equals(user)) {
			throw new IllegalArgumentException();
		}

		this.state = state;
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

	public Content getContentObject() {
		return content;
	}
}

package com.sample.post.repository.entity.post;

import com.sample.post.domain.content.PostPublicationState;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PostPublicationStateConverter implements AttributeConverter<PostPublicationState, String> {
    @Override
    public String convertToDatabaseColumn(PostPublicationState postPublicationState) {
        return postPublicationState.name();
    }

    @Override
    public PostPublicationState convertToEntityAttribute(String dbData) {
        return PostPublicationState.valueOf(dbData);
    }
}

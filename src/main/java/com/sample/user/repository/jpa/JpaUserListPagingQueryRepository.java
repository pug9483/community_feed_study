package com.sample.user.repository.jpa;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sample.user.application.dto.GetUserListResponseDto;
import com.sample.user.repository.entity.QUserEntity;
import com.sample.user.repository.entity.QUserRelationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaUserListPagingQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private static final QUserEntity user = QUserEntity.userEntity;
    private static final QUserRelationEntity relation = QUserRelationEntity.userRelationEntity;

    public List<GetUserListResponseDto> getFollowerList(Long userId, Long lastFollowerId) {
        return jpaQueryFactory
                .select(
                        Projections.fields(
                                GetUserListResponseDto.class
                        )
                )
                .from(relation)
                .join(user).on(relation.followingUserId.eq(user.id))
                .where(
                        relation.followerUserId.eq(userId),
                        hasLastData(lastFollowerId)
                )
                .orderBy(user.id.desc())
                .limit(20)
                .fetch();
    }

    public BooleanExpression hasLastData(Long lastFollowerId) {
        if (lastFollowerId == null) {
            return null;
        }

        return user.id.lt(lastFollowerId);
    }
}

package com.sample.user.repository;

import com.sample.user.application.interfaces.UserRepository;
import com.sample.user.domain.User;
import com.sample.user.repository.entity.UserEntity;
import com.sample.user.repository.jpa.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final JpaUserRepository jpaUserRepository;

    @Override
    public User save(User user) {
        UserEntity entity = new UserEntity(user);
        entity = jpaUserRepository.save(entity);
        return entity.toUser();
    }

    @Override
    public User findById(Long id) {
        return jpaUserRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new)
                .toUser();
    }
}

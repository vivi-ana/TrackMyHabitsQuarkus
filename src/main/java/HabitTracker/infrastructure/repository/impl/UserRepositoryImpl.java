package HabitTracker.infrastructure.repository.impl;


import HabitTracker.domain.entity.User;
import HabitTracker.domain.port.UserRepository;
import HabitTracker.infrastructure.database.entity.UserEntity;
import HabitTracker.infrastructure.database.mapper.UserMapper;
import HabitTracker.infrastructure.repository.QueryUserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.util.Optional;


@ApplicationScoped
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserMapper userMapper;
    private final QueryUserRepository queryUserRepository;

    @Override
    @Transactional
    public void save(User user) {
        UserEntity entity = userMapper.toEntity(user);
        queryUserRepository.persist(entity);
    }

    @Override
    public boolean existsByUsername(String username) {
        return queryUserRepository.findByUsername(username).isPresent();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return queryUserRepository.findByUsername(username)
                .map(userMapper::toDomain);
    }
}
package HabitTracker.domain.port;

import HabitTracker.domain.entity.User;

import java.util.Optional;


public interface UserRepository {
    void save(User user);
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}
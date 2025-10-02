package HabitTracker.infrastructure.repository;

import HabitTracker.infrastructure.database.entity.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class QueryUserRepository implements PanacheRepository<UserEntity> {
    public Optional<UserEntity> findByUsername(String username) {
        return find("username", username).firstResultOptional();
    }
}
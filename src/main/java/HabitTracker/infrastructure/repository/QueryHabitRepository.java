package HabitTracker.infrastructure.repository;

import HabitTracker.infrastructure.database.entity.HabitEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QueryHabitRepository implements PanacheRepository<HabitEntity> {
}

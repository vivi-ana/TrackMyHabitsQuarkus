package HabitTracker.domain.port;

import HabitTracker.domain.entity.Habit;

import java.util.List;
import java.util.Optional;

public interface HabitRepository {

    void save(Habit habit);
    void update(Habit habit);
    Optional<Habit> findById(Long id);
    List<Habit> findAll();
    void deleteById(Long id);
}
package HabitTracker.application.services;

import HabitTracker.domain.entity.Habit;
import HabitTracker.domain.port.HabitRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class HabitService {
    private final HabitRepository habitRepository;

    public HabitService(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    @Transactional
    public void save(Habit habit) {
        habitRepository.save(habit);
    }

    @Transactional
    public void update(Habit habit) {
        habitRepository.update(habit);
    }

    public Optional<Habit> getById(Long id) {
        return habitRepository.findById(id);
    }


    public List<Habit> getAll() {
        return habitRepository.findAll();
    }

    @Transactional
    public void delete(Long id) {
        habitRepository.deleteById(id);
    }
}
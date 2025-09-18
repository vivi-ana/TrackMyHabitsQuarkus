package HabitTracker.infrastructure.repository.impl;

import HabitTracker.domain.entity.Habit;
import HabitTracker.domain.port.HabitRepository;
import HabitTracker.infrastructure.database.entity.HabitEntity;
import HabitTracker.infrastructure.database.mapper.HabitMapper;
import HabitTracker.infrastructure.repository.QueryHabitRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;


@ApplicationScoped
@AllArgsConstructor
public class HabitRepositoryImpl implements HabitRepository {
    private final HabitMapper mapper;
    private final QueryHabitRepository queryHabitRepository;


    @Override
    @Transactional
    public void save(Habit habit) {
        HabitEntity entity = mapper.toEntity(habit);
        queryHabitRepository.persist(entity);
    }

    @Override
    @Transactional
    public void update(Habit habit) {
        HabitEntity existingEntity = queryHabitRepository.findById(habit.getId());

        if (existingEntity == null) {
            throw new EntityNotFoundException("Habit not found for ID " + habit.getId());
        }

        existingEntity.setName(habit.getName());
        existingEntity.setDescription(habit.getDescription());
        existingEntity.setFrequency(habit.getFrequency());
        existingEntity.setLocalDate(habit.getLocalDate());
    }

    @Override
    public Optional<Habit> findById(Long id) {
        return Optional.ofNullable(queryHabitRepository.findById(id))
                .map(mapper::toDomain);
    }

    @Override
    public List<Habit> findAll() {
        return queryHabitRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        queryHabitRepository.deleteById(id);
    }
}
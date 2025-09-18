package HabitTracker.infrastructure.database.mapper;

import HabitTracker.domain.entity.Habit;
import HabitTracker.infrastructure.api.dto.HabitRequestDTO;
import HabitTracker.infrastructure.api.dto.HabitResponseDTO;
import HabitTracker.infrastructure.database.entity.HabitEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface HabitMapper {

    HabitEntity toEntity(Habit domain);

    Habit toDomain(HabitEntity entity);

    Habit toDomain(HabitRequestDTO dto);

    HabitResponseDTO toResponseDTO(Habit domain);
}
package HabitTracker.infrastructure.api.dto;

import common.Frequency;

import java.time.LocalDate;

public record HabitResponseDTO(
        Long id,
        String name,
        String description,
        Frequency frequency,
        LocalDate localDate
) {}
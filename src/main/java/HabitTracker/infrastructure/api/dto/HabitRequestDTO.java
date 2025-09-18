package HabitTracker.infrastructure.api.dto;

import common.Frequency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record HabitRequestDTO(
        @NotBlank String name,
        String description,
        @NotNull Frequency frequency,
        @NotNull LocalDate localDate
) {}
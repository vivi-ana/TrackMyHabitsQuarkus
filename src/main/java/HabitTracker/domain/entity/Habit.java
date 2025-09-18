package HabitTracker.domain.entity;

import common.Frequency;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Habit {
    private Long id;
    private String name;
    private String description;
    private Frequency frequency;
    private LocalDate localDate;
}
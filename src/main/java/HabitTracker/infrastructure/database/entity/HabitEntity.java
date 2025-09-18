package HabitTracker.infrastructure.database.entity;

import common.Frequency;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "habit")
public class HabitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Frequency frequency;

    @Column(name = "local_date")
    private LocalDate localDate;
}
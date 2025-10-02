package HabitTracker.domain.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class User {
    private Long id;
    private String username;
    private String password;
    private Set<Role> roles;
}
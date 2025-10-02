package HabitTracker.infrastructure.database.mapper;

import HabitTracker.domain.entity.Role;
import HabitTracker.infrastructure.database.entity.RoleEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "cdi")
public interface RoleMapper {
    Role toDomain(RoleEntity entity);
}
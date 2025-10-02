package HabitTracker.infrastructure.database.mapper;


import HabitTracker.domain.entity.User;
import HabitTracker.infrastructure.api.dto.UserRequestDTO;
import HabitTracker.infrastructure.database.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface UserMapper {

    UserEntity toEntity(User domain);

    User toDomain(UserRequestDTO dto);

    User toDomain(UserEntity entity);

}

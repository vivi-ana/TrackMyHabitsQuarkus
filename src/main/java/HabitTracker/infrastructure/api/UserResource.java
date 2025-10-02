package HabitTracker.infrastructure.api;

import HabitTracker.application.services.UserService;
import HabitTracker.domain.entity.User;
import HabitTracker.infrastructure.api.dto.UserRequestDTO;
import HabitTracker.infrastructure.database.mapper.UserMapper;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
public class UserResource {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserResource(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(@Valid UserRequestDTO userRequestDTO) {
        User user = userMapper.toDomain(userRequestDTO);
        userService.createUser(user, userRequestDTO.roleIds());
        return Response.status(Response.Status.CREATED).build();
    }
}
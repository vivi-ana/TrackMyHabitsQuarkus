package HabitTracker.infrastructure.api;

import HabitTracker.application.services.AuthService;
import HabitTracker.infrastructure.api.dto.LoginRequestDTO;
import jakarta.annotation.security.PermitAll;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


import java.util.Map;

@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    private final AuthService authService;

    public AuthResource(AuthService authService) {
        this.authService = authService;
    }

    @POST
    @Transactional
    @PermitAll
    public Response login(LoginRequestDTO request) {
        String token = authService.login(request.username(), request.password());
        return Response.ok().entity(Map.of("token", token)).build();
    }
}
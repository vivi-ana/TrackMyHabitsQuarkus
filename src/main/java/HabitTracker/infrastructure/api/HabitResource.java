package HabitTracker.infrastructure.api;

import HabitTracker.application.services.HabitService;
import HabitTracker.domain.entity.Habit;
import HabitTracker.infrastructure.api.dto.HabitRequestDTO;
import HabitTracker.infrastructure.api.dto.HabitResponseDTO;
import HabitTracker.infrastructure.database.mapper.HabitMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/habits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HabitResource {

    private final HabitService habitService;
    private final HabitMapper habitMapper;

    public HabitResource(HabitService habitService, HabitMapper habitMapper) {
        this.habitService = habitService;
        this.habitMapper = habitMapper;
    }

    @GET
    public List<HabitResponseDTO> getAllHabits() {
        return habitService.getAll()
                .stream()
                .map(habitMapper::toResponseDTO)
                .toList();
    }

    @GET
    @Path("/{id}")
    public Response getHabitById(@PathParam("id") Long id) {
        return habitService.getById(id)
                .map(habitMapper::toResponseDTO)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    @Transactional
    public Response createHabit(@Valid HabitRequestDTO habitRequestDTO) {
        Habit habit = habitMapper.toDomain(habitRequestDTO);
        habitService.save(habit);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateHabit(@PathParam("id") Long id, @Valid HabitRequestDTO habitRequestDTO) {
        if (habitService.getById(id).isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Habit habit = habitMapper.toDomain(habitRequestDTO);
        habit.setId(id);
        habitService.update(habit);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteHabit(@PathParam("id") Long id) {
        if (habitService.getById(id).isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        habitService.delete(id);
        return Response.noContent().build();
    }
}
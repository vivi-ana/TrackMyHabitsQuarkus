package HabitTracker.application.services;

import HabitTracker.domain.entity.User;
import HabitTracker.domain.entity.Role;
import HabitTracker.domain.port.PasswordEncoder;
import HabitTracker.domain.port.UserRepository;
import io.quarkus.security.UnauthorizedException;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UnauthorizedException("Usuario no encontrado"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new UnauthorizedException("Contraseña incorrecta");
        }

        Set<String> roles = user.getRoles().stream()
                .map(Role::getRoleName)
                .collect(Collectors.toSet());

        return jwtService.generateToken(user.getUsername(), roles);
    }
}
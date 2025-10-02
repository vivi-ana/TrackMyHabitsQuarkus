package HabitTracker.application.services;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Duration;
import java.util.Set;

@ApplicationScoped
public class JwtService {
    public String generateToken(String username, Set<String> roles) {
        return Jwt.issuer("mi-app")
                .subject(username)
                .groups(roles)
                .expiresIn(Duration.ofHours(1))
                .sign();
    }
}
package HabitTracker.infrastructure.security;

import HabitTracker.domain.port.PasswordEncoder;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BcryptPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(String rawPassword) {
        return BcryptUtil.bcryptHash(rawPassword);
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return BcryptUtil.matches(rawPassword, encodedPassword);
    }
}
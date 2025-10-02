package HabitTracker.application.services;


import HabitTracker.domain.entity.Role;
import HabitTracker.domain.entity.User;
import HabitTracker.domain.port.PasswordEncoder;
import HabitTracker.domain.port.RoleRepository;
import HabitTracker.domain.port.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Set;

@ApplicationScoped
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void createUser(User user, List<Long> roleIds) {

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new EntityExistsException("El usuario ya existe");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<Role> roles = roleRepository.findRoleByIds(roleIds);
        user.setRoles(roles);

        userRepository.save(user);
    }
}
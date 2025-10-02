package HabitTracker.infrastructure.repository.impl;

import HabitTracker.domain.entity.Role;
import HabitTracker.domain.port.RoleRepository;
import HabitTracker.infrastructure.database.mapper.RoleMapper;
import HabitTracker.infrastructure.repository.QueryRoleRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
@AllArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {
    private final QueryRoleRepository queryRoleRepository;
    private final RoleMapper roleMapper;

    @Override
    public Set<Role> findRoleByIds(List<Long> roleIds) {
        return queryRoleRepository.findRoleByIds(roleIds)
                .stream()
                .map(roleMapper::toDomain)
                .collect(Collectors.toSet());
    }
}
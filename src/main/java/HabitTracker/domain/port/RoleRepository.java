package HabitTracker.domain.port;


import HabitTracker.domain.entity.Role;

import java.util.List;
import java.util.Set;

public interface RoleRepository {
    Set<Role> findRoleByIds(List<Long> roleIds);
}
package HabitTracker.infrastructure.repository;

import HabitTracker.infrastructure.database.entity.RoleEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.*;


@ApplicationScoped
public class QueryRoleRepository implements PanacheRepository<RoleEntity> {
    public Set<RoleEntity> findRoleByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptySet();
        }

        return new HashSet<>(find("id in ?1", ids).list());
    }
}
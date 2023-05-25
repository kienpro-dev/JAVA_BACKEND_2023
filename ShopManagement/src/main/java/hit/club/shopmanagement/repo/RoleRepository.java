package hit.club.shopmanagement.repo;

import hit.club.shopmanagement.enums.EnumRole;
import hit.club.shopmanagement.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findRoleByRoleName(EnumRole roleName);
}

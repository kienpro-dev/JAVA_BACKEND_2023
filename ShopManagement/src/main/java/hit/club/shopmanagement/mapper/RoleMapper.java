package hit.club.shopmanagement.mapper;

import hit.club.shopmanagement.dto.RoleDTO;
import hit.club.shopmanagement.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "roleName", source = "roleDTO.roleName")
    Role roleDTOToRole(RoleDTO roleDTO);
}

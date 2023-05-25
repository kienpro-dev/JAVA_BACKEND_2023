package hit.club.shopmanagement.mapper;

import hit.club.shopmanagement.dto.UserDTO;
import hit.club.shopmanagement.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
            @Mapping(target = "fullName", source = "userDTO.fullName"),
            @Mapping(target = "address", source = "userDTO.address"),
            @Mapping(target = "email", source = "userDTO.email"),
            @Mapping(target = "birthday", source = "userDTO.birthday"),
            @Mapping(target = "username", source = "userDTO.username"),
            @Mapping(target = "password", source = "userDTO.password")

    })
    User userDTOToUser(UserDTO userDTO);
}

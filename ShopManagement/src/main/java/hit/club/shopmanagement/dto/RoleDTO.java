package hit.club.shopmanagement.dto;

import hit.club.shopmanagement.enums.EnumRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    @Enumerated(value = EnumType.STRING)
    private EnumRole roleName;
}

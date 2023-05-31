package hit.club.shopmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotBlank(message = "Name must not blank")
    private String fullName;

    @NotBlank(message = "Address must not blank")
    private String address;

    @Email(message = "Invalid email format!")
    private String email;

    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$", message = "Invalid birthday format!")
    private String birthday;

    @NotBlank(message = "Username must not blank")
    private String username;

    @NotBlank(message = "Password must not blank")
    private String password;
}

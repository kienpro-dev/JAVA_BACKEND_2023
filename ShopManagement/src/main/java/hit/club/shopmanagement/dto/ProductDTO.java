package hit.club.shopmanagement.dto;

import jdk.jfr.ContentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    @NotBlank(message = "Name must not blank")
    private String productName;

    @Min(value = 0, message = "Count must start at 0")
    private int count;

    @NotNull(message = "Photo must not null")
    private MultipartFile photo;

    @NotBlank(message = "Description must not blank")
    private String description;
}

package hit.club.shopmanagement.mapper;

import hit.club.shopmanagement.dto.CategoryDTO;
import hit.club.shopmanagement.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mappings({
            @Mapping(target = "categoryName", source = "categoryDTO.categoryName"),
            @Mapping(target = "description", source = "categoryDTO.description")
    })
    Category categoryDTOToCategory(CategoryDTO categoryDTO);
}

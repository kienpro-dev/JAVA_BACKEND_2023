package hit.club.shopmanagement.service;

import hit.club.shopmanagement.dto.CategoryDTO;
import hit.club.shopmanagement.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    Category createNewCategory(CategoryDTO categoryDTO);

    Category getCategoryById(int id);

    List<Category> getAllCategory();

    Category editCategoryById(int id, CategoryDTO categoryDTO);

    void deleteCategoryById(int id);

    Category searchCategoryByName(String categoryName);
}

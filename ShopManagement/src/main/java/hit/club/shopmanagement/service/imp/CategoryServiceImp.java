package hit.club.shopmanagement.service.imp;

import hit.club.shopmanagement.dto.CategoryDTO;
import hit.club.shopmanagement.exception.InternalServerException;
import hit.club.shopmanagement.exception.NotFoundException;
import hit.club.shopmanagement.mapper.CategoryMapper;
import hit.club.shopmanagement.model.Category;
import hit.club.shopmanagement.repo.CategoryRepository;
import hit.club.shopmanagement.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CategoryServiceImp implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);

    @Override
    public Category createNewCategory(CategoryDTO categoryDTO) {
        try {
            return categoryRepository.save(categoryMapper.categoryDTOToCategory(categoryDTO));
        } catch (Exception e) {
            throw new InternalServerException("Data error creating category");
        }
    }

    @Override
    public Category getCategoryById(int id) {
        Optional<Category> categoryFind = categoryRepository.findById(id);

        if(categoryFind.isEmpty()) {
            throw new NotFoundException("Not found category has ID: " + id);
        }

        return categoryFind.get();
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public void editCategoryById(int id, CategoryDTO categoryDTO) {
        try {
            categoryRepository.editCategory(id, categoryDTO.getCategoryName(), categoryDTO.getDescription());
        } catch (Exception e) {
            throw new InternalServerException("Data error updating category");
        }
    }

    @Override
    public void deleteCategoryById(int id) {
        Optional<Category> categoryFind = categoryRepository.findById(id);

        if(categoryFind.isEmpty()) {
            throw new NotFoundException("Not found category has ID: " + id);
        }

        try {
            categoryRepository.deleteById(id);
        } catch (Exception e) {
            throw new InternalServerException("Data error deleting category");
        }
    }

    @Override
    public Category searchCategoryByName(String categoryName) {
        Category categoryFind = categoryRepository.searchByName(categoryName);

        if(categoryFind == null) {
            throw new NotFoundException("Not found category has name: " + categoryName);
        }

        return categoryFind;
    }
}

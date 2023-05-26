package hit.club.shopmanagement.controller;

import hit.club.shopmanagement.dto.CategoryDTO;
import hit.club.shopmanagement.model.Category;
import hit.club.shopmanagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.createNewCategory(categoryDTO));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable int id) {
        Category category = categoryService.getCategoryById(id);

        return ResponseEntity.ok(categoryService.editCategoryById(id, categoryDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable int id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable int id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String categoryName) {
        return ResponseEntity.ok(categoryService.searchCategoryByName(categoryName));
    }

    @GetMapping("/admin/find-all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(categoryService.getAllCategory());
    }
}

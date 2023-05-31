package hit.club.shopmanagement.controller;

import hit.club.shopmanagement.dto.CategoryDTO;
import hit.club.shopmanagement.model.Category;
import hit.club.shopmanagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.createNewCategory(categoryDTO));
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editCategory(@RequestBody CategoryDTO categoryDTO, @RequestParam int id) {
        Category category = categoryService.getCategoryById(id);

        return ResponseEntity.ok(categoryService.editCategoryById(id, categoryDTO));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCategory(@RequestParam int id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-category")
    public ResponseEntity<?> getCategoryById(@RequestParam int id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @GetMapping("/get-category-name")
    public ResponseEntity<?> getUserByUsername(@RequestParam String categoryName) {
        return ResponseEntity.ok(categoryService.searchCategoryByName(categoryName));
    }

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(categoryService.getAllCategory());
    }
}

package hit.club.shopmanagement.controller;

import hit.club.shopmanagement.dto.CategoryDTO;
import hit.club.shopmanagement.model.Category;
import hit.club.shopmanagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/categories")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDTO categoryDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return resultValidation(bindingResult);
        }
        return ResponseEntity.ok(categoryService.createNewCategory(categoryDTO));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/categories/{id}")
    public ResponseEntity<?> editCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable int id, BindingResult bindingResult) {
        Category category = categoryService.getCategoryById(id);

        if(bindingResult.hasErrors()) {
            return resultValidation(bindingResult);
        }
        categoryService.editCategoryById(id, categoryDTO);
        return ResponseEntity.ok(categoryDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable int id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/categories/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable int id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

//    @GetMapping("/get-category-name/{categoryName}")
//    public ResponseEntity<?> getUserByUsername(@PathVariable String categoryName) {
//        return ResponseEntity.ok(categoryService.searchCategoryByName(categoryName));
//    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/categories")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(categoryService.getAllCategory());
    }

    public ResponseEntity<?> resultValidation(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();

        bindingResult.getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage())
        );
        StringBuilder errorMsg = new StringBuilder();
        for (String key : errors.keySet()) {
            errorMsg.append("Error in: ").append(key).append(", Reason: ").append(errors.get(key)).append("\n");
        }
        return ResponseEntity.ok(errorMsg.toString());
    }
}

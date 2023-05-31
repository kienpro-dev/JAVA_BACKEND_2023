package hit.club.shopmanagement.controller;

import hit.club.shopmanagement.dto.ProductDTO;
import hit.club.shopmanagement.model.Product;
import hit.club.shopmanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.createNewProduct(productDTO));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editProduct(@Valid @RequestBody ProductDTO productDTO, @PathVariable int id) {
        Product product = productService.getProductById(id);

        return ResponseEntity.ok(productService.editProductById(id, productDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
        productService.deleteProductById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-product/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/get-product-name/{productName}")
    public ResponseEntity<?> getProductByName(@PathVariable String productName) {
        return ResponseEntity.ok(productService.searchProductByName(productName));
    }

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(productService.getAllProduct());
    }
}

package hit.club.shopmanagement.controller;

import hit.club.shopmanagement.dto.ProductDTO;
import hit.club.shopmanagement.model.Product;
import hit.club.shopmanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.createNewProduct(productDTO));
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editProduct(@RequestBody ProductDTO productDTO, @RequestParam int id) {
        Product product = productService.getProductById(id);

        return ResponseEntity.ok(productService.editProductById(id, productDTO));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProduct(@RequestParam int id) {
        productService.deleteProductById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-product")
    public ResponseEntity<?> getProductById(@RequestParam int id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/get-product-name")
    public ResponseEntity<?> getProductByName(@RequestParam String productName) {
        return ResponseEntity.ok(productService.searchProductByName(productName));
    }

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(productService.getAllProduct());
    }
}

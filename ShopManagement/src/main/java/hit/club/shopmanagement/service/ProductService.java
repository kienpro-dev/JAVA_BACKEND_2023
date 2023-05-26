package hit.club.shopmanagement.service;

import hit.club.shopmanagement.dto.ProductDTO;
import hit.club.shopmanagement.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Product createNewProduct(ProductDTO productDTO);

    Product getProductById(int id);

    List<Product> getAllProduct();

    Product editProductById(int id, ProductDTO productDTO);

    void deleteProductById(int id);

    Product searchProductByName(String productName);

    Product searchProductByCategoryId(int id);
}

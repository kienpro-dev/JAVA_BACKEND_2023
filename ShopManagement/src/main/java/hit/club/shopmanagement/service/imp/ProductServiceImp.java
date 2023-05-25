package hit.club.shopmanagement.service.imp;

import hit.club.shopmanagement.dto.ProductDTO;
import hit.club.shopmanagement.exception.InternalServerException;
import hit.club.shopmanagement.exception.NotFoundException;
import hit.club.shopmanagement.mapper.ProductMapper;
import hit.club.shopmanagement.model.Product;
import hit.club.shopmanagement.repo.ProductRepository;
import hit.club.shopmanagement.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Override
    public Product createNewProduct(ProductDTO productDTO) {
        try {
            return productRepository.save(productMapper.productDTOToProduct(productDTO));
        } catch (Exception e) {
            throw new InternalServerException("Data error creating product");
        }
    }

    @Override
    public Product getProductById(int id) {
        Optional<Product> productFind = productRepository.findById(id);

        if(productFind.isEmpty()) {
            throw new NotFoundException("Not found product has ID: " + id);
        }

        return productFind.get();
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public void editProductById(int id, ProductDTO productDTO) {
        try {
            productRepository.editProduct(id, productDTO.getProductName(), productDTO.getCount(), productDTO.getPhoto().toString(), productDTO.getDescription());
        } catch (Exception e) {
            throw new InternalServerException("Data error updating product");
        }
    }

    @Override
    public void deleteProductById(int id) {
        Optional<Product> productFind = productRepository.findById(id);

        if(productFind.isEmpty()) {
            throw new NotFoundException("Not found product has ID: " + id);
        }

        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            throw new InternalServerException("Data error deleting product");
        }
    }

    @Override
    public Product searchProductByName(String productName) {
        Product productFind = productRepository.searchByName(productName);

        if(productFind == null) {
            throw new NotFoundException("Not found product has name: " + productName);
        }

        return productFind;
    }

    @Override
    public Product searchProductByCategoryId(int id) {
        Product productFind = productRepository.searchByCategoryId(id);

        if(productFind == null) {
            throw new NotFoundException("Not found product has category ID: " + id);
        }

        return productFind;
    }
}

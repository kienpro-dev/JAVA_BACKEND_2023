package hit.club.shopmanagement.repo;

import hit.club.shopmanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findProductByProductName(String productName);

    @Transactional
    @Modifying
    @Query("select u from Product u where u.productName like '%?1%'")
    Product searchByName(String productName);

    @Transactional
    @Modifying
    @Query("select u from Product u where u.category.id = ?1")
    Product searchByCategoryId(int id);

    @Transactional
    @Modifying
    @Query("update Product u set u.productName = ?2, u.count = ?3, u.photo = ?4, u.description = ?5 where u.id = ?1")
    void editProduct(int id, String productName, int count, String photo, String description);
}

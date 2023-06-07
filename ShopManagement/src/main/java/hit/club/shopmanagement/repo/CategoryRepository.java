package hit.club.shopmanagement.repo;

import hit.club.shopmanagement.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findCategoryByCategoryName(String categoryName);

    @Transactional
    @Query("select u from Category u where u.categoryName like '%?1%'")
    Category searchByName(String categoryName);

    @Transactional
    @Modifying
    @Query("update Category u set u.categoryName = ?2, u.description = ?3 where u.id = ?1")
    void editCategory(int id, String categoryName, String description);
}

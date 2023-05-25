package hit.club.shopmanagement.repo;

import hit.club.shopmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);

    @Transactional
    @Modifying
    @Query("select u from User u where u.username like '%?1%'")
    User searchByName(String username);

    @Transactional
    @Modifying
    @Query("update User u set u.fullName = ?2, u.address = ?3, u.email = ?4, u.birthday = ?5, u.username = ?6, u.password = ?7 where u.id = ?1")
    void editUser(int id, String fullName, String address, String email, Date birthday, String username, String password);
}

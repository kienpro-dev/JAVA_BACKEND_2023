package hit.club.shopmanagement.service;

import hit.club.shopmanagement.dto.UserDTO;
import hit.club.shopmanagement.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User createNewUser(UserDTO userDTO);

    User getUserById(int id);

    List<User> getAllUser();

    void editUserById(int id, UserDTO userDTO);

    void deleteUserById(int id);

    User searchUserByName(String username);
}

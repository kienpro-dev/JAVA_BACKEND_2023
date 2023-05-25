package hit.club.shopmanagement.service.imp;

import hit.club.shopmanagement.dto.UserDTO;
import hit.club.shopmanagement.exception.InternalServerException;
import hit.club.shopmanagement.exception.NotFoundException;
import hit.club.shopmanagement.mapper.UserMapper;
import hit.club.shopmanagement.model.User;
import hit.club.shopmanagement.repo.UserRepository;
import hit.club.shopmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Override
    public User createNewUser(UserDTO userDTO) {
        try {
            return userRepository.save(userMapper.userDTOToUser(userDTO));
        } catch (Exception e) {
            throw new InternalServerException("Data error creating user");
        }
    }

    @Override
    public User getUserById(int id) {
        Optional<User> userFind = userRepository.findById(id);

        if(userFind.isEmpty()) {
            throw new NotFoundException("Not found user has ID: " + id);
        }

        return userFind.get();
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void editUserById(int id, UserDTO userDTO) {
        try {
            userRepository.editUser(id, userDTO.getFullName(), userDTO.getAddress(), userDTO.getEmail(), userDTO.getBirthday(), userDTO.getUsername(), userDTO.getPassword());
        } catch (Exception e) {
            throw new InternalServerException("Data error updating user");
        }
    }

    @Override
    public void deleteUserById(int id) {
        Optional<User> userFind = userRepository.findById(id);

        if(userFind.isEmpty()) {
            throw new NotFoundException("Not found user has ID: " + id);
        }

        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new InternalServerException("Data error deleting user");
        }
    }

    @Override
    public User searchUserByName(String username) {
        User userFind = userRepository.searchByName(username);

        if(userFind == null) {
            throw new NotFoundException("Not found user has username: " + username);
        }

        return userFind;
    }
}

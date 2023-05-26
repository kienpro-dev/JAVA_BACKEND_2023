package hit.club.shopmanagement.service.imp;

import hit.club.shopmanagement.dto.UserDTO;
import hit.club.shopmanagement.enums.EnumRole;
import hit.club.shopmanagement.exception.InternalServerException;
import hit.club.shopmanagement.exception.NotFoundException;
//import hit.club.shopmanagement.mapper.UserMapper;
import hit.club.shopmanagement.model.Role;
import hit.club.shopmanagement.model.User;
import hit.club.shopmanagement.repo.RoleRepository;
import hit.club.shopmanagement.repo.UserRepository;
import hit.club.shopmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
//import org.mapstruct.factory.Mappers;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

//    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    private final ModelMapper modelMapper;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public User createNewUser(UserDTO userDTO) {
        try {
//            return userRepository.save(userMapper.userDTOToUser(userDTO));
            PropertyMap<UserDTO, User> userMap = new PropertyMap<UserDTO, User>() {
                @Override
                protected void configure() {
                    skip().setBirthday(null);
                }
            };
            modelMapper.addMappings(userMap);
            User user = modelMapper.map(userDTO, User.class);
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setRole(roleRepository.findRoleByRoleName(EnumRole.ROLE_USER));
            user.setBirthday(dateFormat.parse(userDTO.getBirthday()));
            return userRepository.save(user);
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
    public User editUserById(int id, UserDTO userDTO) {
        try {
            return userRepository.editUser(id, userDTO.getFullName(), userDTO.getAddress(), userDTO.getEmail(), dateFormat.parse(userDTO.getBirthday()), userDTO.getUsername(), userDTO.getPassword());
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

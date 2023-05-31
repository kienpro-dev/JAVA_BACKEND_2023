package hit.club.shopmanagement.controller;

import hit.club.shopmanagement.dto.UserDTO;
import hit.club.shopmanagement.jwt.JwtUtils;
import hit.club.shopmanagement.model.User;
import hit.club.shopmanagement.request.LoginRequest;
import hit.club.shopmanagement.response.UserResponse;
import hit.club.shopmanagement.service.UserService;
import hit.club.shopmanagement.service.imp.UserDetailImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/public/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailImp userDetail = (UserDetailImp) authentication.getPrincipal();

        try {
            String accessToken = jwtUtils.generateTokenByUsername(userDetail.getUsername());
            return ResponseEntity.ok(new UserResponse(userDetail.getId(), userDetail.getFullName(), accessToken, userDetail.getAuthorities()));
        } catch (Exception e) {
            return ResponseEntity.ok("Login failed: " + e.getMessage());
        }
    }

    @PostMapping("/public/create")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.createNewUser(userDTO));
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editUser(@RequestBody UserDTO userDTO, @RequestParam int id) {
        User user = userService.getUserById(id);

        return ResponseEntity.ok(userService.editUserById(id, userDTO));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam int id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-user")
    public ResponseEntity<?> getUserById(@RequestParam int id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/get-user-name")
    public ResponseEntity<?> getUserByUsername(@RequestParam String username) {
        return ResponseEntity.ok(userService.searchUserByName(username));
    }

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(userService.getAllUser());
    }
}

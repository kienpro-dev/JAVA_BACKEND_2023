package hit.club.shopmanagement.controller;

import hit.club.shopmanagement.dto.UserDTO;
import hit.club.shopmanagement.email.EmailService;
import hit.club.shopmanagement.jwt.JwtUtils;
import hit.club.shopmanagement.model.User;
import hit.club.shopmanagement.request.LoginRequest;
import hit.club.shopmanagement.response.UserResponse;
import hit.club.shopmanagement.service.UserService;
import hit.club.shopmanagement.service.imp.UserDetailImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private EmailService emailService;

    @PostMapping("/login")
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

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return resultValidation(bindingResult);
        }
        return ResponseEntity.ok(userService.createNewUser(userDTO));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PutMapping("/users/{id}")
    public ResponseEntity<?> editUser(@Valid @RequestBody UserDTO userDTO, @PathVariable int id, BindingResult bindingResult) {
        User user = userService.getUserById(id);
        if(bindingResult.hasErrors()) {
            return resultValidation(bindingResult);
        }
        userService.editUserById(id, userDTO);
        return ResponseEntity.ok(userDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable(value = "id") int id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

//    @GetMapping("/users/{username}")
//    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
//        return ResponseEntity.ok(userService.searchUserByName(username));
//    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/users/forgot-password/{username}")
    public ResponseEntity<?> forgotPassword(@PathVariable String username, @RequestParam String password, @RequestParam String password2, @RequestParam String to, @RequestParam String message) throws MessagingException {
        return ResponseEntity.ok(emailService.sentEmail(to, message, password));
    }

    public ResponseEntity<?> resultValidation(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();

        bindingResult.getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage())
        );
        StringBuilder errorMsg = new StringBuilder();
        for (String key : errors.keySet()) {
            errorMsg.append("Error in: ").append(key).append(", Reason: ").append(errors.get(key)).append("\n");
        }
        return ResponseEntity.ok(errorMsg.toString());
    }
}

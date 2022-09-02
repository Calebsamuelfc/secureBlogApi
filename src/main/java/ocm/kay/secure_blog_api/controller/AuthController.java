package ocm.kay.secure_blog_api.controller;

import ocm.kay.secure_blog_api.dto.LoginDto;
import ocm.kay.secure_blog_api.dto.SignUpDto;
import ocm.kay.secure_blog_api.entity.AppUser;
import ocm.kay.secure_blog_api.entity.Role;
import ocm.kay.secure_blog_api.repository.RoleRepository;
import ocm.kay.secure_blog_api.repository.UserRepository;
import ocm.kay.secure_blog_api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User login successful", HttpStatus.OK);
    }
    @PostMapping("/user/signup")
    public ResponseEntity<String> registerUser (@RequestBody SignUpDto signUpDto){
        authService.registerUser(signUpDto);
        return new ResponseEntity<>("User signup successful", HttpStatus.OK);
    }

    @PostMapping("/admin/signup")
    public ResponseEntity<String> registerAdmin (@RequestBody SignUpDto signUpDto){
        authService.registerUser(signUpDto);
        return new ResponseEntity<>("User signup successful", HttpStatus.OK);
    }

}

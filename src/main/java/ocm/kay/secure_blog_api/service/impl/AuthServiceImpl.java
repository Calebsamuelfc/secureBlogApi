package ocm.kay.secure_blog_api.service.impl;

import lombok.RequiredArgsConstructor;
import ocm.kay.secure_blog_api.dto.SignUpDto;
import ocm.kay.secure_blog_api.entity.AppUser;
import ocm.kay.secure_blog_api.entity.Role;
import ocm.kay.secure_blog_api.exceptions.ResourceAlreadyExists;
import ocm.kay.secure_blog_api.repository.RoleRepository;
import ocm.kay.secure_blog_api.repository.UserRepository;
import ocm.kay.secure_blog_api.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser registerUser(SignUpDto signUpDto){
        if(userRepository.existsByUserName(signUpDto.getUserName())){
            throw new ResourceAlreadyExists(signUpDto.getUserName());
        }
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            throw new ResourceAlreadyExists(signUpDto.getEmail());
        }
        AppUser user = AppUser.builder()
                .userName(signUpDto.getUserName())
                .email(signUpDto.getEmail())
                .name(signUpDto.getName())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .build();

        Role roles = roleRepository.findByName("ROLE_USER").get();
        user.setRoles(Collections.singleton(roles));
        return userRepository.save(user);
    }

    @Override
    public AppUser registerAdmin (SignUpDto signUpDto){
        if(userRepository.existsByUserName(signUpDto.getUserName())){
            throw new ResourceAlreadyExists(signUpDto.getUserName());
        }
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            throw new ResourceAlreadyExists(signUpDto.getEmail());
        }
        AppUser user = AppUser.builder()
                .userName(signUpDto.getUserName())
                .email(signUpDto.getEmail())
                .name(signUpDto.getName())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .build();

        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));
        return userRepository.save(user);
    }
}

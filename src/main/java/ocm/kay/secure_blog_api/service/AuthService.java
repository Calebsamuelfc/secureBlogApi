package ocm.kay.secure_blog_api.service;

import ocm.kay.secure_blog_api.dto.SignUpDto;
import ocm.kay.secure_blog_api.entity.AppUser;

public interface AuthService {
    AppUser registerUser (SignUpDto signUpDto);

    AppUser registerAdmin(SignUpDto signUpDto);
}

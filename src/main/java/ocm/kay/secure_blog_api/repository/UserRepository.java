package ocm.kay.secure_blog_api.repository;

import ocm.kay.secure_blog_api.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail (String email);
    Optional<AppUser> findByUserNameOrEmail(String email, String userName);
    Optional<AppUser> findByUserName(String userName);
    Boolean existsByEmail(String email);
    Boolean existsByUserName(String userName);
}

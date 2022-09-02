package ocm.kay.secure_blog_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class SignUpDto {
    private String name;
    private String userName;
    private String email;
    private String password;
}

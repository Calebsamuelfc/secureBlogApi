package ocm.kay.secure_blog_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class CommentDto {
    private Long id;
    @NotEmpty(message = "name should not be null or empty")
    private String name;
    @NotEmpty
    @Email(message = "Email should not be null or empty")
    private String email;
    @NotEmpty
    @Size(min = 10, message = "Comment body should have at least 10 characters")
    private String body;
}

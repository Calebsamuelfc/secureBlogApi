package ocm.kay.secure_blog_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class CommentDto {
    private Long id;
    private String name;
    private String email;
    private String body;
}

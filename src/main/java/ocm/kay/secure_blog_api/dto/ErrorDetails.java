package ocm.kay.secure_blog_api.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ErrorDetails {
    private Date timeStamp;
    private String message;
    private String details;
}

package ocm.kay.secure_blog_api.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceAlreadyExists extends RuntimeException {
    private String resourceName;


    public ResourceAlreadyExists( String resourceName) {
        super(String.format("%s already exists ",resourceName));
        this.resourceName = resourceName;
    }
}

package ocm.kay.secure_blog_api.controller;

import lombok.RequiredArgsConstructor;
import ocm.kay.secure_blog_api.dto.CommentDto;
import ocm.kay.secure_blog_api.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;


    @PostMapping("posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
                                                    @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);
    }
}

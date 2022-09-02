package ocm.kay.secure_blog_api.controller;

import lombok.RequiredArgsConstructor;
import ocm.kay.secure_blog_api.dto.PostDto;
import ocm.kay.secure_blog_api.dto.PostResponse;
import ocm.kay.secure_blog_api.service.PostService;
import ocm.kay.secure_blog_api.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static ocm.kay.secure_blog_api.utils.AppConstants.*;

@RestController
@RequestMapping("api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;


    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost (@Valid @PathVariable(name = "id") Long id, @Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.updatePost(postDto,id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost (@PathVariable(name = "id") Long id){
        postService.deletePost(id);
        return new ResponseEntity<>("post entity deleted successfully", HttpStatus.OK);
    }
}

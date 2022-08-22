package ocm.kay.secure_blog_api.controller;

import lombok.RequiredArgsConstructor;
import ocm.kay.secure_blog_api.dto.PostDto;
import ocm.kay.secure_blog_api.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/posts")
@RequiredArgsConstructor
public class PostController {
    private PostService postService;


    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<PostDto> getAllPosts(){
        return postService.getAllPosts();
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost (@PathVariable(name = "id") Long id, @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.update(postDto,id), HttpStatus.OK);
    }
}

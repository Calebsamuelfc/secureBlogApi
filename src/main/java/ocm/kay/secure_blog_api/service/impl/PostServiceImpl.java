package ocm.kay.secure_blog_api.service.impl;

import lombok.RequiredArgsConstructor;
import ocm.kay.secure_blog_api.dto.PostDto;
import ocm.kay.secure_blog_api.entity.Post;
import ocm.kay.secure_blog_api.exceptions.ResourceNotFoundException;
import ocm.kay.secure_blog_api.repository.PostRepository;
import ocm.kay.secure_blog_api.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;


    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = convertToEntity(postDto);
        Post dbPost = postRepository.save(post);
        return convertToDto(dbPost);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post -> convertToDto(post)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id",id));
        return convertToDto(post);
    }

    private PostDto convertToDto(Post post){
        return PostDto.builder()
                .content(post.getContent())
                .description(post.getDescription())
                .id(post.getId())
                .title(post.getTitle())
                .build();
    }
    private Post convertToEntity(PostDto postDto){
        return Post.builder()
                .content(postDto.getContent())
                .description(postDto.getDescription())
                .title(postDto.getTitle())
                .build();
    }
}

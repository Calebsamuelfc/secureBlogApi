package ocm.kay.secure_blog_api.service.impl;

import lombok.RequiredArgsConstructor;
import ocm.kay.secure_blog_api.dto.PostDto;
import ocm.kay.secure_blog_api.dto.PostResponse;
import ocm.kay.secure_blog_api.entity.Post;
import ocm.kay.secure_blog_api.exceptions.ResourceNotFoundException;
import ocm.kay.secure_blog_api.repository.PostRepository;
import ocm.kay.secure_blog_api.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;


    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = convertToEntity(postDto);
        Post dbPost = postRepository.save(post);
        return convertToDto(dbPost);
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        PageRequest pageable = PageRequest.of(pageNo,pageSize, sort);
        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> postList = posts.getContent();

        List<PostDto> content = postList.stream().map(post -> convertToDto(post)).collect(Collectors.toList());
        return PostResponse.builder()
                .content(content)
                .pageNo(posts.getNumber())
                .pageSize(posts.getSize())
                .totalElements(posts.getTotalElements())
                .totalPages(posts.getTotalPages())
                .last(posts.isLast())
                .build();
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id",id));
        return convertToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id",id));
        post.setContent(postDto.getContent());
        post.setDescription(post.getDescription());
        post.setTitle(post.getTitle());
        Post savedPost = postRepository.save(post);
        return convertToDto(savedPost);
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id",id));
        postRepository.delete(post);
    }


    private PostDto convertToDto(Post post){
        return modelMapper.map(post,PostDto.class);
//        return PostDto.builder()
//                .content(post.getContent())
//                .description(post.getDescription())
//                .id(post.getId())
//                .title(post.getTitle())
//                .build();
    }
    private Post convertToEntity(PostDto postDto){
        return modelMapper.map(postDto,Post.class);
//        return Post.builder()
//                .content(postDto.getContent())
//                .description(postDto.getDescription())
//                .title(postDto.getTitle())
//                .build();
    }
}

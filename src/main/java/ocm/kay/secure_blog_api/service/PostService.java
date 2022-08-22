package ocm.kay.secure_blog_api.service;

import ocm.kay.secure_blog_api.dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    List<PostDto> getAllPosts();
    PostDto getPostById(Long Id);
    PostDto update(PostDto postDto, Long Id);

}

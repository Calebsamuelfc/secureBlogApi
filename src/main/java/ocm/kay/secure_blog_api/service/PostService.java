package ocm.kay.secure_blog_api.service;

import ocm.kay.secure_blog_api.dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    List<PostDto> getAllPosts(int pageNo, int pageSize);
    PostDto getPostById(Long Id);
    PostDto updatePost(PostDto postDto, Long Id);
    void deletePost(Long id);

}

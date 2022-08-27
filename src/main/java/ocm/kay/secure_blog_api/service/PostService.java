package ocm.kay.secure_blog_api.service;

import ocm.kay.secure_blog_api.dto.PostDto;
import ocm.kay.secure_blog_api.dto.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
    PostDto getPostById(Long Id);
    PostDto updatePost(PostDto postDto, Long Id);
    void deletePost(Long id);

}

package ocm.kay.secure_blog_api.service;

import ocm.kay.secure_blog_api.dto.CommentDto;

public interface CommentService {
    CommentDto createComment (long postId, CommentDto commentDto);
}

package ocm.kay.secure_blog_api.service;

import ocm.kay.secure_blog_api.dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment (long postId, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(long postId);

    CommentDto getCommentById(Long postId, Long commentId);

    CommentDto updateCommentById(Long postId, Long commentId, CommentDto commentRequest);

    void deleteComment(Long postId, Long commentId);
}

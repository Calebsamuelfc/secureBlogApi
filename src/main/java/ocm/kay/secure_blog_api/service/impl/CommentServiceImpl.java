package ocm.kay.secure_blog_api.service.impl;

import lombok.RequiredArgsConstructor;
import ocm.kay.secure_blog_api.dto.CommentDto;
import ocm.kay.secure_blog_api.dto.PostDto;
import ocm.kay.secure_blog_api.entity.Comment;
import ocm.kay.secure_blog_api.entity.Post;
import ocm.kay.secure_blog_api.exceptions.ResourceNotFoundException;
import ocm.kay.secure_blog_api.repository.CommentRepository;
import ocm.kay.secure_blog_api.repository.PostRepository;
import ocm.kay.secure_blog_api.service.CommentService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = convertToEntity(commentDto);
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        comment.setPost(post);
        Comment dbComment = commentRepository.save(comment);
        return convertToDto(dbComment);
    }



    private CommentDto convertToDto(Comment comment){
        return CommentDto.builder()
                .id(comment.getId())
                .body(comment.getBody())
                .email(comment.getEmail())
                .name(comment.getName())
                .build();
    }
    private Comment convertToEntity(CommentDto commentDto){
        return Comment.builder()
                .body(commentDto.getBody())
                .email(commentDto.getEmail())
                .name(commentDto.getName())
                .build();
    }
}

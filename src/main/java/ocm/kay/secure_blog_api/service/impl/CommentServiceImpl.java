package ocm.kay.secure_blog_api.service.impl;

import lombok.RequiredArgsConstructor;
import ocm.kay.secure_blog_api.dto.CommentDto;
import ocm.kay.secure_blog_api.entity.Comment;
import ocm.kay.secure_blog_api.entity.Post;
import ocm.kay.secure_blog_api.exceptions.BlogAPIException;
import ocm.kay.secure_blog_api.exceptions.ResourceNotFoundException;
import ocm.kay.secure_blog_api.repository.CommentRepository;
import ocm.kay.secure_blog_api.repository.PostRepository;
import ocm.kay.secure_blog_api.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    @Override
    public List<CommentDto> getCommentsByPostId(long postId){
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(comment -> convertToDto(comment)).collect(Collectors.toList());
    }
    @Override
    public CommentDto getCommentById(Long postId, Long commentId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }
        return convertToDto(comment);
    }
    @Override
    public CommentDto updateCommentById(Long postId, Long commentId, CommentDto commentRequest){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }
        comment.setBody(commentRequest.getBody());
        comment.setEmail(commentRequest.getEmail());
        comment.setName(commentRequest.getName());
        Comment dbComment = commentRepository.save(comment);
        return convertToDto(dbComment);
    }
    @Override
    public void deleteComment(Long postId, Long commentId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }
        commentRepository.delete(comment);
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

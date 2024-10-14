package org.springboot.blog.springboot_blog_rest_api.service;

import org.springboot.blog.springboot_blog_rest_api.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId,CommentDto commentDto);
    List<CommentDto> getCommentsByPostId(long postId);

    CommentDto getCommentById(Long postId, Long commentId);
    CommentDto updateComment(Long postId,long commentId, CommentDto commentRequest);
    void deleteComment(Long postId, Long commentId);
}

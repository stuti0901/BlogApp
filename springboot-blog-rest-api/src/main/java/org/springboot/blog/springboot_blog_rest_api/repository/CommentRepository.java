package org.springboot.blog.springboot_blog_rest_api.repository;

import org.springboot.blog.springboot_blog_rest_api.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPostId(long postId);


}

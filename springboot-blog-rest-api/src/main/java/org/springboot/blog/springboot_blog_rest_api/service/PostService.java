package org.springboot.blog.springboot_blog_rest_api.service;

import org.springboot.blog.springboot_blog_rest_api.entity.Post;
import org.springboot.blog.springboot_blog_rest_api.payload.PostDto;
import org.springboot.blog.springboot_blog_rest_api.payload.PostResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts(int pageNo, int pageSize,String sortBy);
    PostDto getPostById(long id);
    PostDto updatePost(PostDto postDto,long id);
    void  deletePostById(long id);
}

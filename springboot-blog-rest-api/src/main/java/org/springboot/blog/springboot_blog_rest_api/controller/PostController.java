package org.springboot.blog.springboot_blog_rest_api.controller;

import org.springboot.blog.springboot_blog_rest_api.entity.Post;
import org.springboot.blog.springboot_blog_rest_api.payload.PostDto;
import org.springboot.blog.springboot_blog_rest_api.payload.PostResponse;
import org.springboot.blog.springboot_blog_rest_api.service.PostService;
import org.springboot.blog.springboot_blog_rest_api.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto)
    {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping
    public PostResponse getAllPost(@RequestParam(value = "pageNo",defaultValue= AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                   @RequestParam(value="pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
                                   @RequestParam(value="sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy
                                   )
    {
        return postService.getAllPosts(pageNo,pageSize,sortBy);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name="id") long id)
    {
        return ResponseEntity.ok(postService.getPostById(id));
    }
    @PutMapping("/{id}")
    public  ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable(name="id") long id)
    {
        PostDto postResponse=postService.updatePost(postDto,id);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public  ResponseEntity<String> deletePost(@PathVariable(name="id") long id)
    {
        postService.deletePostById(id);
        return  new ResponseEntity<>("Post entity deleted successfully", HttpStatus.OK);
    }


}

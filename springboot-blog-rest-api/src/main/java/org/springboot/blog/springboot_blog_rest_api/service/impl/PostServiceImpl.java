package org.springboot.blog.springboot_blog_rest_api.service.impl;

import org.springboot.blog.springboot_blog_rest_api.entity.Post;
import org.springboot.blog.springboot_blog_rest_api.exception.ResourceNotFoundException;
import org.springboot.blog.springboot_blog_rest_api.payload.PostDto;
import org.springboot.blog.springboot_blog_rest_api.payload.PostResponse;
import org.springboot.blog.springboot_blog_rest_api.repository.PostRepository;
import org.springboot.blog.springboot_blog_rest_api.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        //convert Dto into entity
//        Post post=new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        Post post=mapToEntity(postDto);
          Post newPost=postRepository.save(post);


        //convert entity to dto

        PostDto postResponse=mapToDto(newPost);

       return postResponse;

    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize,String sortBy)
    {

        Pageable pageable= PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
        Page<Post> posts=postRepository.findAll(pageable);
        List<Post> listOfPosts=posts.getContent();

        List<PostDto> content=  listOfPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id ));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto,long id ) {
        Post post =postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post updatePost=postRepository.save(post);
                return mapToDto(updatePost);
    }

    @Override
    public void deletePostById(long id) {
        Post post =postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        postRepository.delete(post);
    }


    //converting entity into dto
    private PostDto mapToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }
    private Post mapToEntity(PostDto postDto)
    {
        Post post=new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;

    }
}

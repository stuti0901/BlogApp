package org.springboot.blog.springboot_blog_rest_api.payload;

import lombok.Data;

@Data
public class PostDto {
    private long id;
    private String title;
    private String description;
    private String content;


}

package org.springboot.blog.springboot_blog_rest_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    private String name;
    private String email;
    private String body;

    //fetch type lazy tells hibernate to only fetch the related entities from the db when you use the relationship
    @ManyToOne(fetch = FetchType.LAZY)
    //specifying foreign key
    @JoinColumn(name="post_id")
    private Post post;

}

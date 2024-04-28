package com.thisissourik.blog.services;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import com.thisissourik.blog.entitites.Post;
import com.thisissourik.blog.payloads.PostDto;
import com.thisissourik.blog.payloads.PostResponse;

import javax.swing.*;
import java.util.List;

public interface PostService {
    //Create
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    //Update
    PostDto updatePost(PostDto postDto, Integer postId);

    //Delete
    void deletePost(Integer postId);

    //Get all post
    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy);

    //Get post by postId
    PostDto getPostById(Integer postId);

    //Get all post by category
    List<PostDto> getPostByCategory(Integer categoryId);

    //Get all post by user
    List<PostDto> getPostByUser(Integer userId);

    //Search Post
    List<PostDto> searchPost(String id);
}

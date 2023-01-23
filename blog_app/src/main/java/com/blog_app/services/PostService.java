package com.blog_app.services;

import com.blog_app.entities.Category;
import com.blog_app.entities.Post;
import com.blog_app.entities.User;
import com.blog_app.payloads.PostDto;
import com.blog_app.payloads.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto,Integer userId, Integer catId);
    PostDto updatePost(PostDto postDto, Integer postId);
    void deletePost(Integer postId);
    PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
    PostDto getPostById(Integer postId);
    List<PostDto> getPostByCategory(Integer cid);
    List<PostDto> getPostByUser(Integer uid);

    public List<PostDto> searchPosts(String keyword);





}

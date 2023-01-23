package com.blog_app.services.impl;

import com.blog_app.entities.Category;
import com.blog_app.entities.Post;
import com.blog_app.entities.User;
import com.blog_app.exceptions.ResourceNotFoundException;
import com.blog_app.payloads.CategoryDto;
import com.blog_app.payloads.PostDto;
import com.blog_app.payloads.PostResponse;
import com.blog_app.repositories.CategoryRepo;
import com.blog_app.repositories.PostRepo;
import com.blog_app.repositories.UserRepo;
import com.blog_app.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper mm;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId,Integer catId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId ));

        Category cat = this.categoryRepo.findById(catId).orElseThrow(()-> new ResourceNotFoundException("User","id",catId ));



        Post p = mm.map(postDto, Post.class);
        p.setUser(user);
        p.setCategory(cat);
        p.setImageName("default.png");
        p.setAddedDate(new Date());

        return mm.map(this.postRepo.save(p),PostDto.class);


    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("User","id",postId));

        Category category = this.categoryRepo.findById(postDto.getCategory().getCategoryId()).get();

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        post.setCategory(category);

        //p.setUser(postDto.getUser());
        Post pp = this.postRepo.save(post);
        return mm.map(pp, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post p = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("User","id",postId));

        this.postRepo.delete(p);

    }

    @Override
    public  PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);

        Page<Post> pagePost = this.postRepo.findAll(p);

        List<Post> allPosts = pagePost.getContent();

        List<PostDto> postDtos = allPosts.stream().map((post) -> this.mm.map(post, PostDto.class))
                .collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();

        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());

        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post p = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("User","id",postId));
        return mm.map(p, PostDto.class);
    }

    @Override
    public List<PostDto> getPostByCategory(Integer catId) {
        Category cat = this.categoryRepo.findById(catId).orElseThrow(()-> new ResourceNotFoundException("User","id",catId ));
        List<Post> posts = this.postRepo.findByCategory(cat);
        List<PostDto> postsDto=posts.stream().map(post->(mm.map(post, PostDto.class))).collect(Collectors.toList());
        return postsDto;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId ));
        List<Post> posts = this.postRepo.findByUser(user);
        List<PostDto> postsDto=posts.stream().map(post->(mm.map(post, PostDto.class))).collect(Collectors.toList());
        return postsDto;
    }



    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts = this.postRepo.searchByTitle("%" + keyword + "%");
        List<PostDto> postDtos = posts.stream().map((post) -> this.mm.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

}


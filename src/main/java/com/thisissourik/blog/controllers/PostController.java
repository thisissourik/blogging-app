package com.thisissourik.blog.controllers;

//import com.thisissourik.blog.payloads.ApiResponse;
import com.thisissourik.blog.payloads.ApiResponse;
import com.thisissourik.blog.payloads.PostDto;
import com.thisissourik.blog.payloads.PostResponse;
import com.thisissourik.blog.services.FileService;
import com.thisissourik.blog.services.PostService;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    //Create a new post with userId and categoryId
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable("userId") Integer userId, @PathVariable("categoryId") Integer categoryId) {

        PostDto post = this.postService.createPost(postDto, userId, categoryId);

        return new ResponseEntity<PostDto>(post, HttpStatus.CREATED);
    }

    //Get post of a user with userId
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {

        List<PostDto> post = this.postService.getPostByUser(userId);

        return new ResponseEntity<List<PostDto>>(post, HttpStatus.OK);
    }

    //Get post with categoryId
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId) {

        List<PostDto> post = this.postService.getPostByCategory(categoryId);

        return new ResponseEntity<List<PostDto>>(post, HttpStatus.OK);
    }

    //Get all posts
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                    @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                                    @RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy) {
        PostResponse allPost = this.postService.getAllPost(pageNumber, pageSize, sortBy);
        return new ResponseEntity<PostResponse>(allPost, HttpStatus.OK);
    }

    //Get post with postId
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Integer postId) {
        PostDto postById = this.postService.getPostById(postId);
        return new ResponseEntity<PostDto>(postById, HttpStatus.OK);
    }

    //Delete Post
    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId) {
        this.postService.deletePost(postId);
        return new ApiResponse("Post Deleted Successfully", true);
    }

    //Update Post
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
        PostDto updatedPost = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
    }

    //Search Keyword

    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keyword) {
        List<PostDto> postDto = this.postService.searchPost(keyword);
        return new ResponseEntity<List<PostDto>>(postDto, HttpStatus.OK);
    }

    //Post Image Upload
    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> postImage(@RequestParam("image")MultipartFile image,@PathVariable Integer postId) throws IOException {

        PostDto postById = this.postService.getPostById(postId);
        String fileName = this.fileService.uploadImage(path, image);
        postById.setImageName(fileName);
        PostDto updatedPost = this.postService.updatePost(postById, postId);

        return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
    }


    //method to serve files
    @GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse response
    ) throws IOException {

        InputStream resource = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream())   ;

    }
}

package com.myblog8.controller;

import com.myblog8.payload.PostDto;
import com.myblog8.payload.PostResponse;
import com.myblog8.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController
{

//http://localhost:8080/api/posts

    private PostService postService ;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PreAuthorize("hasRole('ADMIN')")
   @PostMapping
   public  ResponseEntity<?> createPost(
           @Valid @RequestBody PostDto postDto ,
           BindingResult result
   ){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
        }

       PostDto dto = postService.createPost(postDto);

       return new ResponseEntity<>(dto ,HttpStatus.OK);
   }





   // to delete a data from database
    //http://localhost:8080/api/posts/{postId}
   @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{postId}")
    public ResponseEntity<String>deletePostById(@PathVariable int postId )
    {

                  postService.deletePostById(postId);
        return new ResponseEntity<>("post is deleted postsId : "+ postId , HttpStatus.OK);
    }

    // to get single data by id from database to posman

    // http://localhost:8080/api/posts/{postId}

    @GetMapping("{postId}")
    public  ResponseEntity<PostDto> getPostById(@PathVariable int postId)
    {
        PostDto dto = postService.getPostById(postId);
        return new ResponseEntity<>(dto , HttpStatus.OK);
    }

   // to get All Post Using Pagination
    //http://localhost:8080/api/posts?pageNo=1&pageSize=3&sortBy=description&sortDir=desc

    @GetMapping
    public PostResponse getAllPost(
    @RequestParam(value = "pageNo",defaultValue = "0",required = false) int pageNo ,
    @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize ,
    @RequestParam(value = "sortBy" ,defaultValue = "id",required = false) String sortBy ,
    @RequestParam(value = "sortDir" , defaultValue =  "asc",required = false) String sortDir
    )
    {

        PostResponse postResponse = postService.getAllPost(pageNo, pageSize, sortBy, sortDir);

        return postResponse ;
    }








}

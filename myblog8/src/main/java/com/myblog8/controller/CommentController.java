package com.myblog8.controller;

import com.myblog8.payload.CommentDto;
import com.myblog8.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController
{

    //http://localhost:8080/api/comments/{postId}

     private CommentService commentService ;
   public CommentController(CommentService commentService)
    {
        this.commentService = commentService;
    }

    // TO save Comments

    @PostMapping("{postId}")
    public ResponseEntity<CommentDto> saveComment(@RequestBody CommentDto commentDto , @PathVariable long postId)
    {
        CommentDto dto = commentService.saveComment(commentDto, postId);

          return new ResponseEntity<>(dto , HttpStatus.OK);
    }

     // to delete Comments
    //http://localhost:8080/api/comments/{id}
   @DeleteMapping("{id}")
   public ResponseEntity<String> deleteCommentById(@PathVariable long id)
   {
       commentService.deleteCommentById(id);

       return  new ResponseEntity<>("comment id deleted by id :"+id , HttpStatus.OK);

   }


  //to update Comments
    //http://localhost:8080/api/comments/{id}

    @PutMapping("{id}")
    public  ResponseEntity<CommentDto> updateCommentById(@RequestBody CommentDto commentDto ,@PathVariable long id)
    {
        CommentDto dto = commentService.updateCommentById(commentDto, id);

        return  new ResponseEntity<>(dto , HttpStatus.OK);

    }






}

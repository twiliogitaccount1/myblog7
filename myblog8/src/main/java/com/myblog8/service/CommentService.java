package com.myblog8.service;

import com.myblog8.payload.CommentDto;

public interface CommentService
{

    // to save Comment

    CommentDto saveComment(CommentDto dto , long postId);

    //to update comments

    CommentDto updateCommentById(CommentDto commentDto, long id) ;

  //to delete comments

    void deleteCommentById(long id);

}



package com.myblog8.service;

import com.myblog8.payload.PostDto;
import com.myblog8.payload.PostResponse;

public interface PostService
{
  // to save data
   public    PostDto  createPost(PostDto postDto);

// to delete data
   void deletePostById(int postId);

    PostDto getPostById(int postId);


    PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);

}


package com.myblog8.service.impl;

import com.myblog8.entity.Comment;
import com.myblog8.entity.Post;
import com.myblog8.exception.ResourseNotFound;
import com.myblog8.payload.CommentDto;
import com.myblog8.repository.CommentRepository;
import com.myblog8.repository.PostRepository;
import com.myblog8.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService
{

    private CommentRepository commentRepo;

    private PostRepository postRepo ;

  private ModelMapper modelMapper ;

    public CommentServiceImpl(
            CommentRepository commentRepo,
            PostRepository postRepo,
            ModelMapper modelMapper
    )
    {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo ;
        this.modelMapper = modelMapper ;
    }

    // to save Comments

    @Override
    public CommentDto saveComment(CommentDto dto, long postId)
    {

        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourseNotFound("Post not found with id :" + postId)
        );


       // Comment comment = new Comment();

       // comment.setId(dto.getId());
       // comment.setName(dto.getName());
       // comment.setBody(dto.getBody());
       // comment.setEmail(dto.getEmail());
      //  comment.setPost(post);


        Comment comment = mapToComment(dto);
        comment.setPost(post);

        Comment savedComment = commentRepo.save(comment);

        CommentDto commentDto  =new CommentDto();

        commentDto.setId(savedComment.getId());
       commentDto.setName(savedComment.getName());
         commentDto.setEmail(savedComment.getEmail());
       commentDto.setBody(savedComment.getBody());




        return commentDto;
    }


    // to update comments

    @Override
    public CommentDto updateCommentById(CommentDto commentDto, long id)
    {
        Comment comment = commentRepo.findById(id).orElseThrow(
                () -> new ResourseNotFound("comments is not found with id :" + id)
        );

           comment.setName(commentDto.getName());
           comment.setEmail(commentDto.getEmail());
           comment.setBody(commentDto.getBody());

        Comment saved = commentRepo.save(comment);

       CommentDto dto = new CommentDto();
       dto.setId(saved.getId());
       dto.setName(saved.getName());
       dto.setEmail(saved.getEmail());
       dto.setBody(saved.getBody());



        return dto;
    }

    //to delete Comments

    @Override
    public void deleteCommentById(long id)
    {

        Comment comment = commentRepo.findById(id).orElseThrow(
                () -> new ResourseNotFound("comment is not found with id :" + id)
        );

        commentRepo.deleteById(id);

    }


public Comment mapToComment(CommentDto dto)
{

    Comment comment = modelMapper.map(dto, Comment.class);

    // Comment comment = new Comment();
  //  comment.setId(dto.getId());
    //comment.setName(dto.getName());
   // comment.setBody(dto.getBody());
    //comment.setEmail(dto.getEmail());
   return comment ;

}



}

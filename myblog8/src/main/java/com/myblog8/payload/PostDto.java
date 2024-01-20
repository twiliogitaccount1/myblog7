package com.myblog8.payload;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto
{

    private  long id ;


    @NotEmpty
    @Size(min = 2 , message =  "Post Title should be at least 2 characters ")
    private String title ;


    @NotEmpty
    @Size(min = 10 , message =  "Post description should be at least 10 characters ")
    private String description ;


    @NotEmpty
    private  String content ;


}

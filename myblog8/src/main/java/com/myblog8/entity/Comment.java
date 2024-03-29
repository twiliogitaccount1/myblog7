package com.myblog8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Comment
{

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id ;

    private String name ;

    private String email ;

    private String body ;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "post_id" , nullable = false)
private  Post post ;

}


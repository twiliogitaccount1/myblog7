package com.myblog8.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name =  "roles")
public class Role
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private  String  name ;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();




}


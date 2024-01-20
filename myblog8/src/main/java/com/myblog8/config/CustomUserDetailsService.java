package com.myblog8.config;

import com.myblog8.entity.Role;
import com.myblog8.entity.User;
import com.myblog8.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService
{

    @Autowired
    private UserRepository userRepo ;


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException
    {

        User user = userRepo.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).
                orElseThrow(() -> new UsernameNotFoundException("User is not found with !" + usernameOrEmail));


        return new org.springframework.security.core.userdetails.User(user.getEmail(),
             user.getPassword() ,mapToRolesAuthorities(user.getRoles()));

    }

    private Collection< ? extends GrantedAuthority> mapToRolesAuthorities(Set<Role> roles)
    {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }





}

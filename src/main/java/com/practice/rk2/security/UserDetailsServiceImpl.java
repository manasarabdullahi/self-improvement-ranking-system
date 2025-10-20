package com.practice.rk2.security;

import com.practice.rk2.model.User;
import com.practice.rk2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;


    @Override
    public UserDetails loadUserByUsername(String username){
        User user = userRepo.findByUsername(username);
        com.practice.rk2.security.UserDetailsImpl userDetailsImpl = new UserDetailsImpl(user);
        if(user == null){
            throw new UsernameNotFoundException(username);
        }
        return userDetailsImpl;
    }
}


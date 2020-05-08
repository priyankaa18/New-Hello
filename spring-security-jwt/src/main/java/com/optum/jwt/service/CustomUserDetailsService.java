package com.optum.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.optum.jwt.entity.UserInfo;
import com.optum.jwt.repository.UserInfoRepository;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
   
	@Autowired
    private UserInfoRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = repository.findByUserName(username);
        return new User(userInfo.getUserName(), userInfo.getPassword(), new ArrayList<>());
    }
}

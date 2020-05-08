package com.optum.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.optum.jwt.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo,Integer> {
    UserInfo findByUserName(String username);
}

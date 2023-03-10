package com.alex123.springframework.log.repository;

import com.alex123.springframework.log.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {


    Optional<UserInfo> findByName(String username);


    List<UserInfo> findAllById(String email);


}

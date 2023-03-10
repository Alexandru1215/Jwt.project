package com.alex123.springframework.log.service;

import com.alex123.springframework.log.dto.UserInfoUserDetails;
import com.alex123.springframework.log.entity.UserInfo;
import com.alex123.springframework.log.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    private UserInfoRepository userRepository;

//    PasswordEncoder passwordEncoder ;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = userRepository.findByName(username);

        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found! Boom"));
    }

//    public String addUser(UserInfo userInfo) {
//
//        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
//        userRepository.save(userInfo);
//        return "User added to system";
//    }

}

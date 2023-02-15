package com.example.jachiplus_back.config;

import com.example.jachiplus_back.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
@Slf4j
public class SessionUser extends UserEntity implements Serializable {
    private UserEntity userEntity;

    public SessionUser(UserEntity userEntity){
        super(userEntity.getId(),userEntity.getEmail(),userEntity.getPassword(),userEntity.getNickname());

        log.info("SecurityUser user.username = {}", userEntity.getId());
        log.info("SecurityUser user.password = {}", userEntity.getPassword());
        log.info("SecurityUser user.email = {}", userEntity.getEmail());
        this.userEntity = userEntity;
    }

    public static boolean isLogin(HttpSession session) {
        return session.getAttribute("loginUser") != null;
    }
}

package com.example.jachiplus_back.repository;

import com.example.jachiplus_back.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    UserEntity findByEmail(String email);
    UserEntity findByNickname(String nickname);
    boolean existsByEmail(String email);


}

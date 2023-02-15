package com.example.jachiplus_back.repository;

import com.example.jachiplus_back.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity,Long> {

    List<CommentEntity> findByUserId(Long userId);
}

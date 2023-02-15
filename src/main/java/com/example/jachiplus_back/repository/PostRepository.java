package com.example.jachiplus_back.repository;

import com.example.jachiplus_back.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity,Long> {

    List<PostEntity> findByTitle(String title);

}

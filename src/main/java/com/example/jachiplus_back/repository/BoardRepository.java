package com.example.jachiplus_back.repository;

import com.example.jachiplus_back.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<PostEntity,Long> {

}

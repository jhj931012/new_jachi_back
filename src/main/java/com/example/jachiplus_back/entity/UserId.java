package com.example.jachiplus_back.entity;


import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class UserId implements Serializable {
    @Id
    @Column(name = "comment_id")
    Long id;
    @Id
    @Column(name = "post_id")
    private Long postId;
    @Id
    @Column(name = "user_id")
    private Long userId;
}
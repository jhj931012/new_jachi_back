package com.example.jachiplus_back.entity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Getter
@Setter
@Entity
@ToString
@Table(name="post")

public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private Long id;

    @Column(length = 500, nullable = false,name="title")
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    @Column(nullable = false,length = 30)
    private String author;
    @Column(name="modified_user",length = 30)
    private String modifiedUser;

    @Column(columnDefinition = "integer default 0")
    private Integer hit;

    private Boolean isActive;
    private Boolean isDelete;

    @CreationTimestamp
    @Column
    private LocalDateTime createDate = LocalDateTime.now();
    @UpdateTimestamp
    @Column
    private LocalDateTime modifiedDate = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public static PostEntity createPost(String title, String content,LocalDateTime createDate,UserEntity userEntity){
        PostEntity postEntity = new PostEntity();
        postEntity.title = title;
        postEntity.content = content;
        postEntity.createDate = createDate;
        postEntity.userEntity = userEntity;
        return postEntity;
    }

    public static PostEntity modifyPost (PostEntity postEntity, String title, String content, LocalDateTime modifiedDate){
        postEntity.title = title;
        postEntity.content = content;
        postEntity.modifiedDate = modifiedDate;
        return postEntity;
    }

    @OneToMany(mappedBy = "post",fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<CommentEntity> comments;

}

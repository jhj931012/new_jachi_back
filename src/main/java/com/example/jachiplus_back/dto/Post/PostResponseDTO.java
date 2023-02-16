package com.example.jachiplus_back.dto.Post;

import com.example.jachiplus_back.entity.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponseDTO {
    private Long id;
    private String nickname;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private boolean writtenBy;

    public static PostResponseDTO of(PostEntity post, boolean bol){
        return PostResponseDTO.builder()
                .id(post.getId())
//                .nickname(post.getUserEntity().getNickname())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreateDate())
                .modifiedAt(post.getModifiedDate())
                .writtenBy(bol)
                .build();
    }
}

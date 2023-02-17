package com.example.jachiplus_back.dto.Post;

import com.example.jachiplus_back.entity.PostEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class PostDetailResponseDTO {

    private Long id;
    private String title;
    private String content;
    private String author;
    @JsonFormat(pattern = "yyyy.MM.dd/hh:mm:ss")
    private LocalDateTime createDate;
    @JsonFormat(pattern = "yyyy.MM.dd/hh:mm:ss")
    private LocalDateTime modifiedDate;

    public PostDetailResponseDTO(PostEntity entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.createDate = entity.getCreateDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}
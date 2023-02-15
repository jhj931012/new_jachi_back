package com.example.jachiplus_back.dto.comment;

import com.example.jachiplus_back.entity.CommentEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class CommentResponseDTO {
    @NotBlank
    private String CommentAuthor;
    @NotBlank
    private String CommentTitle;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;
    public CommentResponseDTO(CommentEntity comment){
        this.CommentAuthor = comment.getCommentAuthor();
        this.CommentTitle = comment.getCommentTitle();
        this.createdDate = comment.getCreatedDate();
    }

}

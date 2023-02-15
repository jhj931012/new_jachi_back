package com.example.jachiplus_back.dto.comment;

import com.example.jachiplus_back.entity.CommentEntity;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentModifyDTO {
    @NotBlank
    private String modifiedAuthor;
    @NotBlank
    private String modifiedContent;
    @NotBlank
    private String modifiedTitle;
    @NotBlank
    private LocalDateTime modifiedDate;

    public CommentModifyDTO(CommentEntity comment){
        this.modifiedAuthor = comment.getCommentAuthor();
        this.modifiedContent = comment.getCommentContent();
        this.modifiedTitle = comment.getCommentTitle();
        this.modifiedDate = comment.getModifiedDate();
    }
}

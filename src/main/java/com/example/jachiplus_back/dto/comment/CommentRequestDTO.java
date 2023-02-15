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
public class CommentRequestDTO {
    @NotBlank
    private String commentTitle;
    @NotBlank
    private String commentContent;
    @NotBlank
    private String commentAuthor;
    @NotBlank
    private LocalDateTime createdDate;

    public CommentEntity toEntity(){
        CommentEntity comment = new CommentEntity();
        comment.setCommentAuthor(this.commentAuthor);
        comment.setCommentTitle(this.commentTitle);
        comment.setCommentContent(this.commentContent);
        comment.setCreatedDate(this.createdDate);
        return comment;
    }
}

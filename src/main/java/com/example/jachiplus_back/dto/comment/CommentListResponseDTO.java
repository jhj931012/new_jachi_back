package com.example.jachiplus_back.dto.comment;

import lombok.*;

import java.util.List;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentListResponseDTO {
    private String error;
    private List<CommentResponseDTO> comments;
}

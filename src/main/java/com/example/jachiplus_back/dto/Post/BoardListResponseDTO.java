package com.example.jachiplus_back.dto.Post;

import com.example.jachiplus_back.entity.PostEntity;
import lombok.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardListResponseDTO {

    private List<PostDetailResponseDTO> posts;

//    public static BoardListResponseDTO of(PostEntity postEntity){
//        return BoardListResponseDTO.builder()
//                .id(postEntity.getId())
//                .title(postEntity.getTitle())
//                .nickname(postEntity.getUserEntity().getNickname())
//                .createAt(postEntity.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd/HH:mm:ss")))
//                .build();
//    }
}

package com.example.jachiplus_back.dto.Post;

import com.example.jachiplus_back.entity.PostEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class PostWriteRequestDTO {
    @NotBlank
    @Size(min = 2, max = 20)
    private String title;

    @NotBlank
    @Size(min = 2, max = 20)
    private String content;

//    @NotBlank
    private String author;

//    @JsonFormat(pattern = "yyyy.MM.dd/hh.mm.ss")
//    private LocalDateTime reDate;

    public PostEntity toEntity(){
        return PostEntity.builder()
                .title(this.title)
                .content(this.content)
                .author(this.author)
                .build();
    }

}
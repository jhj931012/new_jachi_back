package com.example.jachiplus_back.dto.user;

import com.example.jachiplus_back.entity.UserEntity;
import lombok.*;

import java.time.LocalDateTime;


@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "email")
public class UserSignUpResponseDTO {

    private String email;
    private String nickname;
    private LocalDateTime regDate;

    public UserSignUpResponseDTO(UserEntity userEntity){
        this.email = userEntity.getEmail();
        this.nickname = userEntity.getNickname();
        this.regDate = userEntity.getRegDate();
    }
}

package com.example.jachiplus_back.dto.user;

import com.example.jachiplus_back.entity.UserEntity;
import lombok.*;
import net.bytebuddy.asm.Advice;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter @Getter @ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class LoginResponseDTO {

    private String email;
    private String nickname;
    private LocalDate regDate;
    private String token;
    private String message;

    public LoginResponseDTO(UserEntity userEntity, String token){
        this.email = userEntity.getEmail();
        this.nickname = userEntity.getNickname();
        this.regDate = LocalDate.from(userEntity.getRegDate());
        this.token = token;

    }
}

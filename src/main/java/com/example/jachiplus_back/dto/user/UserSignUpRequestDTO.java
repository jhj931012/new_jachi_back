package com.example.jachiplus_back.dto.user;

import com.example.jachiplus_back.entity.UserEntity;
import lombok.*;
import org.apache.catalina.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter @Getter @ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "email")
@Builder
public class UserSignUpRequestDTO {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 8)
    private String password;
    @NotBlank
    @Size(min = 2, max = 15)
    private String nickname;

    public UserEntity toEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(this.email);
        userEntity.setPassword(this.password);
        userEntity.setNickname(this.nickname);
        return userEntity;
    }
}

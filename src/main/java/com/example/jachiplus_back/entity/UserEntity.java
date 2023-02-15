package com.example.jachiplus_back.entity;

import lombok.Builder;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@ToString
@Data
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Long id;
    @Column(nullable = false, length = 100, unique = true)
    private String email;
    @Column(nullable = false, length = 100, unique = true)
    private String password;
    @Column(nullable = false, length = 30)
    private String nickname;
//    @Column(nullable = false, length = 50)
//    private String phone;
    @Column
    private String birthday;
    @Column(length = 1000)
    private String refreshToken;

//    @Column(nullable = false ,length = 32)
//    private String name;

    @Column(length = 512)
    private String picture;

    @Column
    @CreationTimestamp
    private LocalDateTime regDate;

    public UserEntity(Long id, String email, String password, String nickname) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void destroyToken() {
        this.refreshToken = null;
    }
    @Builder
    public UserEntity(Long id,String email,String password,String nickname,
//                      String phone,
                      String refreshToken
//                      String name,
//                      String picture
                     ){

        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
//        this.phone = phone;
        this.regDate = regDate;
//        this.name = name;
//        this.picture = picture;
    }

}

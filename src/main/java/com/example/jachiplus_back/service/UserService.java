package com.example.jachiplus_back.service;

import com.example.jachiplus_back.dto.Post.PostDetailResponseDTO;
import com.example.jachiplus_back.dto.user.LoginResponseDTO;
import com.example.jachiplus_back.dto.user.UserSignUpRequestDTO;
import com.example.jachiplus_back.dto.user.UserSignUpResponseDTO;
import com.example.jachiplus_back.entity.UserEntity;
import com.example.jachiplus_back.exception.DuplicatedEmailExeption;
import com.example.jachiplus_back.exception.NoRegisteredArgumentsException;
import com.example.jachiplus_back.repository.UserRepository;
import com.example.jachiplus_back.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;


    // 회원가입 처리
    public UserSignUpResponseDTO create(final UserSignUpRequestDTO userSignUpRequestDTO) throws DuplicatedEmailExeption {
        if(userSignUpRequestDTO == null){
            throw new NoRegisteredArgumentsException("가입정보가 없습니다.");
        }
        final String email = userSignUpRequestDTO.getEmail();
        if(userRepository.existsByEmail(email)){
            log.warn("Email already exists - {}", email);
            throw new DuplicatedEmailExeption("중복된 이메일입니다.");
        }

        String rawPassword = userSignUpRequestDTO.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        userSignUpRequestDTO.setPassword(encodedPassword);

        UserEntity savedUser = userRepository.save(userSignUpRequestDTO.toEntity());

        return new UserSignUpResponseDTO(savedUser);
    }

    public boolean isDuplicate(String email){
        if(email == null){
            throw new RuntimeException("이메일 값이 없습니다.");
        }
        return userRepository.existsByEmail(email);
    }

    public LoginResponseDTO getByCredentials(
            final String email,
            final String rawPassword) {

        // 입력한 이메일을 통해 회원정보 조회
        UserEntity originalUser = userRepository.findByEmail(email);

        if (originalUser == null) {
            throw new RuntimeException("가입된 회원이 아닙니다.");
        }
        // 패스워드 검증 (입력 비번, DB에 저장된 비번)
        if (!passwordEncoder.matches(rawPassword, originalUser.getPassword())) {
            throw new RuntimeException("비밀번호가 틀렸습니다.");
        }

//        log.info("{}님 로그인 성공!", originalUser.getNickname());


        // 토큰 발급
        String token = tokenProvider.createToken(originalUser);
        LoginResponseDTO responseDTO = new LoginResponseDTO(originalUser, token);



//        LoginResponseDTO loginUser
//                = (LoginResponseDTO) session.getAttribute("loginUser");


        return responseDTO;
    }
}

package com.example.jachiplus_back.service;

import com.example.jachiplus_back.config.LoginUser;
import com.example.jachiplus_back.dto.Post.*;
import com.example.jachiplus_back.dto.user.UserSignUpResponseDTO;
import com.example.jachiplus_back.entity.PostEntity;
import com.example.jachiplus_back.entity.UserEntity;
import com.example.jachiplus_back.repository.BoardRepository;
import com.example.jachiplus_back.repository.PostRepository;
import com.example.jachiplus_back.repository.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Builder
@Service
public class PostService {
    private final UserRepository userRepository;


    private final PostRepository postRepository;
    private final BoardRepository boardRepository;

    //게시글 목록 보여주기
    public List<BoardListResponseDTO> allPost() {
        List<PostEntity> postEntities = boardRepository.findAll();
        return postEntities
                .stream()
                .map(BoardListResponseDTO::of)
                .collect(Collectors.toList());
    }

    //게시글 등록 서비스

    public PostDetailResponseDTO write(final PostWriteRequestDTO writeDTO, String nickname) {


        PostEntity savedPost = postRepository.save(writeDTO.toEntity());
        log.info("<===========등록 : {} ================>", savedPost);
        return new PostDetailResponseDTO(savedPost);
    }


    //삭제
    public BoardListResponseDTO delete(final Long bno) {

        try {
            postRepository.deleteById(bno);
        } catch (Exception e) {
            log.error("해당 게시물이 없음 - bno : {} ==== {}", bno, e.getMessage());
            throw new RuntimeException("게시물이 없어 삭제 실패");
        }
        return (BoardListResponseDTO) allPost();
    }

    public PostResponseDTO onePost(Long id, final UserRepository userRepository) {
        PostEntity post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("글이 없습니다."));
        String email = post.getUserEntity().getEmail();
        boolean bol = post.getUserEntity().getNickname().equals(userRepository.findByEmail(email).getNickname());
        try {
            if (bol == true) return PostResponseDTO.of(post, bol);
        } catch (Exception e) {
            throw new RuntimeException("게시글 작성자가 아닙니다");
        }
        return PostResponseDTO.of(post, bol);
    }

    public BoardListResponseDTO ByTitle(final String title) {

        List<PostEntity> originalDetailPost = postRepository.findByTitle(title);
        log.info("/api/posts/{} GET request", title); // null
        log.info("================= {}개의 게시물이 존재합니다. =================", originalDetailPost.stream().count());

        // 엔터티를 DTO로 변환
        List<PostDetailResponseDTO> dtoDetailList = originalDetailPost.stream()
                .map(PostDetailResponseDTO::new)
                .collect(Collectors.toList());

        return BoardListResponseDTO.builder()
                .posts(dtoDetailList)
                .build();
    }

    public BoardListResponseDTO ByAuthor(String author) {
        List<PostEntity> originalDetailPost = postRepository.findByTitle(author);
        log.info("/api/posts/{} GET request", author); // null
        log.info("================= {}개의 게시물이 존재합니다. =================", originalDetailPost.stream().count());

        // 엔터티를 DTO로 변환
        List<PostDetailResponseDTO> dtoDetailList = originalDetailPost.stream()
                .map(PostDetailResponseDTO::new)
                .collect(Collectors.toList());

        return BoardListResponseDTO.builder()
                .posts(dtoDetailList)
                .build();
    }

    public PostDetailResponseDTO update(final Long bno, final PostModifyRequestDTO modifyDTO)
            throws RuntimeException {
        // 수정 전 데이터 조회하기
        final PostEntity entity = postRepository
                .findById(bno)
                .orElseThrow(
                        () -> new RuntimeException("글이 없어요")
                );

        // 수정 진행
        String modTitle = modifyDTO.getTitle();
        String modContent = modifyDTO.getContent();

        if (modTitle != null) entity.setTitle(modTitle);
        if (modContent != null) entity.setContent(modContent);
//        entity.setModifyDate(LocalDateTime.now());

        PostEntity modifiedPost = postRepository.save(entity);
        return new PostDetailResponseDTO(modifiedPost);
    }
}
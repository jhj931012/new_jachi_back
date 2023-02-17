package com.example.jachiplus_back.service;

import com.example.jachiplus_back.dto.Post.*;
import com.example.jachiplus_back.entity.PostEntity;
import com.example.jachiplus_back.repository.BoardRepository;
import com.example.jachiplus_back.repository.PostRepository;
import com.example.jachiplus_back.repository.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public BoardListResponseDTO allPost() {
        List<PostEntity> entityList = boardRepository.findAll();
        log.info("entityList===>{}",entityList);
        List<PostDetailResponseDTO> dtoList = entityList.stream()
                .map(PostDetailResponseDTO::new)
                .collect(Collectors.toList());
        return BoardListResponseDTO.builder().posts(dtoList).build();
    }

    //게시글 등록 서비스

    public PostDetailResponseDTO write(final PostWriteRequestDTO writeDTO, final String nickname) {


        PostEntity savedPost = postRepository.save(writeDTO.toEntity());
        savedPost.setAuthor(nickname);
        log.info("<===========등록 : {} ================>", savedPost);
        return new PostDetailResponseDTO(savedPost);
    }


    //삭제
    public BoardListResponseDTO delete(final Long id) {
        log.info("삭제실행==============================");
        postRepository.deleteById(id);
//        try {
//            postRepository.deleteById(id);
//        } catch (Exception e) {
//            log.error("해당 게시물이 없음 - id : {} ==== {}", id, e.getMessage());
//            throw new RuntimeException("게시물이 없어 삭제 실패");
//        }
        return allPost();
    }

    public BoardListResponseDTO onePost(Long id) {
        Optional<PostEntity> entityList;
        entityList = boardRepository.findById(id);
        log.info("entityList===>{}",entityList);
        List<PostDetailResponseDTO> dtoList = entityList.stream()
                .map(PostDetailResponseDTO::new)
                .collect(Collectors.toList());
        return BoardListResponseDTO.builder().posts(dtoList).build();
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
package com.example.jachiplus_back.controller;

import com.example.jachiplus_back.config.LoginUser;
import com.example.jachiplus_back.config.SessionUser;
import com.example.jachiplus_back.dto.Post.*;
import com.example.jachiplus_back.entity.PostEntity;
import com.example.jachiplus_back.entity.UserEntity;
import com.example.jachiplus_back.repository.PostRepository;
import com.example.jachiplus_back.repository.UserRepository;
import com.example.jachiplus_back.service.PostService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/jachi/board")
@Builder
public class PostController {
    private final UserRepository userRepository;

    private final PostService postService;

    @PostMapping("/post/write")
    public ResponseEntity<?> create(@LoginUser SessionUser sessionUser ,@Validated @RequestBody PostWriteRequestDTO writeDTO) {
//        PostDetailResponseDTO detailResponseDTO = postService.write(writeDTO);
        return  ResponseEntity.ok(postService.write(writeDTO,sessionUser.getNickname()));
//                ResponseEntity
//                .ok()
//                .body(detailResponseDTO,sessionUser);
    }

    //    @GetMapping
//    public ResponseEntity<?> read(long bno){
//        BoardListResponseDTO listResponseDTO = postService.retrieve(bno);
//        log.info("===============Posts Get=============");
//        return ResponseEntity
//                .ok()
//                .body(listResponseDTO);
//    }
    @GetMapping
    public List<BoardListResponseDTO> board() {
        return postService.allPost();
    }

    @DeleteMapping("/post/delete/{bno}")
    public ResponseEntity<?> remove(@PathVariable Long bno, UserRepository email) {
        log.info("/delete/{} Delete request", bno);
        try {
            postService.onePost(bno, email);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }
        try {
            postService.delete(bno);
            return ResponseEntity.ok().body("삭제");
        } catch (RuntimeException e) {
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<?> searchByTitle(String title) {
        try {
            BoardListResponseDTO listDetailResponseDTO = postService.ByTitle(title);
            log.info("/api/posts/{} GET request!", title); // null
            log.info("/api/posts GET! - {}", listDetailResponseDTO);

            return ResponseEntity
                    .ok()
                    .body(listDetailResponseDTO);

        } catch (Exception e) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @GetMapping("/search/{author}")
    public ResponseEntity<?> searchByWriter(String author) {
        try {
            BoardListResponseDTO listDetailResponseDTO = postService.ByAuthor(author);
            log.info("/api/posts/{} GET request!", author); // null
            log.info("/api/posts GET! - {}", listDetailResponseDTO);

            return ResponseEntity
                    .ok()
                    .body(listDetailResponseDTO);

        } catch (Exception e) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @PatchMapping("/post/{bno}")
    public ResponseEntity<?> modify(
            @PathVariable Long bno
            , @RequestBody PostModifyRequestDTO modifyDTO,
            UserRepository email
            ) {
        log.info("/board/post/{} PUT request!", bno);
        log.info("수정정보: {}", modifyDTO);

        try {

            postService.onePost(bno, email);

        }catch (RuntimeException e){
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }
        try {
            PostDetailResponseDTO responseDTO = postService.update(bno, modifyDTO);
            return ResponseEntity
                    .ok()
                    .body(responseDTO);
        } catch (RuntimeException e) {
            log.error("수정 실패:  - {}", e.getMessage());
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }
    }

}


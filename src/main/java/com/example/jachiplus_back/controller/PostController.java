package com.example.jachiplus_back.controller;

import com.example.jachiplus_back.dto.Post.*;
import com.example.jachiplus_back.repository.UserRepository;
import com.example.jachiplus_back.service.PostService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/jachi/board")
@Builder
public class PostController {
    private final UserRepository userRepository;

    private final PostService postService;

    @PostMapping("/post/write")
    public ResponseEntity<?> create(@AuthenticationPrincipal String userId
            , @Validated @RequestBody PostWriteRequestDTO writeDTO
            , BindingResult result
    ) {
        PostDetailResponseDTO responseDTO = postService.write(writeDTO, userId);
        return ResponseEntity
                .ok()
                .body(responseDTO);

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
    public ResponseEntity<BoardListResponseDTO> board() {
        BoardListResponseDTO responseDTO = postService.allPost();
        log.info("/jachi/board GET - {}", responseDTO);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<BoardListResponseDTO> veiw(@PathVariable Long id) {
        BoardListResponseDTO responseDTO = postService.onePost(id);
        log.info("/jachi/board/post GET - {}", responseDTO);
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping("/post/delete/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id, UserRepository email) {
        log.info("/delete/{} Delete request", id);
        try {
            postService.delete(id);
            return ResponseEntity.ok().body("삭제");
        } catch (RuntimeException e) {
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }
    }

//    @GetMapping("/search/{title}")
//    public ResponseEntity<?> searchByTitle(String title) {
//        try {
//            BoardListResponseDTO listDetailResponseDTO = postService.ByTitle(title);
//            log.info("/api/posts/{} GET request!", title); // null
//            log.info("/api/posts GET! - {}", listDetailResponseDTO);
//
//            return ResponseEntity
//                    .ok()
//                    .body(listDetailResponseDTO);
//
//        } catch (Exception e) {
//            return ResponseEntity
//                    .notFound()
//                    .build();
//        }
//    }
//
//    @GetMapping("/search/{author}")
//    public ResponseEntity<?> searchByWriter(String author) {
//        try {
//            BoardListResponseDTO listDetailResponseDTO = postService.ByAuthor(author);
//            log.info("/api/posts/{} GET request!", author); // null
//            log.info("/api/posts GET! - {}", listDetailResponseDTO);
//
//            return ResponseEntity
//                    .ok()
//                    .body(listDetailResponseDTO);
//
//        } catch (Exception e) {
//            return ResponseEntity
//                    .notFound()
//                    .build();
//        }
//    }

//    @PatchMapping("/post/{bno}")
//    public ResponseEntity<?> modify(
//            @PathVariable Long bno
//            , @RequestBody PostModifyRequestDTO modifyDTO,
//            UserRepository email
//    ) {
//        log.info("/board/post/{} PUT request!", bno);
//        log.info("수정정보: {}", modifyDTO);
//
//        try {
//
//            postService.onePost(bno, email);
//
//        } catch (RuntimeException e) {
//            return ResponseEntity
//                    .internalServerError()
//                    .body(e.getMessage());
//        }
//        try {
//            PostDetailResponseDTO responseDTO = postService.update(bno, modifyDTO);
//            return ResponseEntity
//                    .ok()
//                    .body(responseDTO);
//        } catch (RuntimeException e) {
//            log.error("수정 실패:  - {}", e.getMessage());
//            return ResponseEntity
//                    .internalServerError()
//                    .body(e.getMessage());
//        }
    }



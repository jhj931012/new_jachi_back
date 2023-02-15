package com.example.jachiplus_back.controller;

import com.example.jachiplus_back.config.LoginUser;
import com.example.jachiplus_back.config.SessionUser;
import com.example.jachiplus_back.dto.comment.CommentListResponseDTO;
import com.example.jachiplus_back.dto.comment.CommentModifyDTO;
import com.example.jachiplus_back.dto.comment.CommentRequestDTO;
import com.example.jachiplus_back.dto.comment.CommentResponseDTO;
import com.example.jachiplus_back.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/jachi")
public class CommentController {
    private final CommentService commentService;
    //Comment 조회
    @GetMapping("/comments")
    public ResponseEntity<?> comments(@PathVariable Long PostId){
        List<CommentResponseDTO> commentResponseDTOList = commentService.comments(PostId);
        return
                ResponseEntity.ok().body(commentResponseDTOList);
    }

    // Comment 생성
    @PostMapping("/post/{id}/comment")
    public ResponseEntity<?> commentCreate(@PathVariable Long id, @RequestBody CommentRequestDTO commentRequestDTO,
                                           @LoginUser SessionUser sessionUser){
        return  ResponseEntity.ok(commentService.createComment(commentRequestDTO,id,sessionUser.getNickname()));
    }

    // Comment 삭제
    @DeleteMapping("/post/{id}/comment")
    public ResponseEntity<?> commentDelete(@PathVariable Long id){
        commentService.deleteComment(id);
        return ResponseEntity.ok(id);
    }
    // Comment 업데이트
    @PutMapping("/post/{id}/comment")
    public ResponseEntity<?> commentUpdate(@PathVariable Long id, @RequestBody CommentModifyDTO commentModifyDTO){
        commentService.updateComment(commentModifyDTO,id);
        return ResponseEntity.ok(id);
    }
}

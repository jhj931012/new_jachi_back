package com.example.jachiplus_back.service;

import com.example.jachiplus_back.dto.comment.CommentModifyDTO;
import com.example.jachiplus_back.dto.comment.CommentRequestDTO;
import com.example.jachiplus_back.dto.comment.CommentResponseDTO;
import com.example.jachiplus_back.entity.CommentEntity;
import com.example.jachiplus_back.entity.PostEntity;
import com.example.jachiplus_back.entity.UserEntity;
import com.example.jachiplus_back.repository.CommentRepository;
import com.example.jachiplus_back.repository.PostRepository;
import com.example.jachiplus_back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    // 댓글 조회
    @Transactional
    public List<CommentResponseDTO> comments(Long PostId){
        List<CommentEntity> commentList = commentRepository.findByUserId(PostId);
        List<CommentResponseDTO> dtoList = commentList.stream()
                .map(CommentResponseDTO::new)
                .collect(Collectors.toList());
        return dtoList;
    }
    /*CREATE*/
    public CommentResponseDTO createComment(final CommentRequestDTO commentRequestDTO, final Long postId,final String nickName)
        throws RuntimeException{
        CommentEntity comment = commentRequestDTO.toEntity();
        UserEntity user = userRepository.findByNickname(nickName);
        PostEntity post = postRepository.findById(postId).orElseThrow(()-> new IllegalArgumentException("게시글이 존재하지 않습니다"));
        comment.setUser(user);
        comment.setPost(post);
        commentRepository.save(comment);

        return new CommentResponseDTO(comment);
    }

    /*UPDATE*/

    @Transactional
    public CommentModifyDTO updateComment(final CommentModifyDTO commentModifyDTO,final Long commentId){
        CommentEntity comment = commentRepository.findById(commentId).orElseThrow(()-> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
        comment.setCommentContent(commentModifyDTO.getModifiedContent());
        comment.setModifiedDate(commentModifyDTO.getModifiedDate());
        return new CommentModifyDTO(comment);
    }
    /*DELETE*/
    @Transactional
    public void deleteComment(final Long commentId){
        CommentEntity comment = commentRepository.findById(commentId).orElseThrow(()->
                new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
        commentRepository.delete(comment);
    }
}

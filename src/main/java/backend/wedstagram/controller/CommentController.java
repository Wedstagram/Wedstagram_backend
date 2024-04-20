package backend.wedstagram.controller;

import backend.wedstagram.Converter.CommentConverter;
import backend.wedstagram.domain.Comment;
import backend.wedstagram.domain.Feed;
import backend.wedstagram.domain.Member;
import backend.wedstagram.dto.CommentDTO.CommentRequestDTO;
import backend.wedstagram.service.CommentService.CommentService;
import backend.wedstagram.service.FeedService.FeedService;
import backend.wedstagram.service.MemberService.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final FeedService feedService;
    private final MemberService memberService;
    private final CommentService commentService;

    @PostMapping("/feed/{postId}/comment")
    @Operation(summary = "댓글 쓰기 API", description = "댓글 쓰기 API")
    public ResponseEntity<String> writeComment(@PathVariable (value = "postId")Long postId, @RequestBody CommentRequestDTO.CommentWriteRequestDto commentWriteRequestDto){

        Feed feed = feedService.getFeedById(postId);
        Member member = memberService.getMemberById(commentWriteRequestDto.getMemberId());
        Comment comment = CommentConverter.toComment(commentWriteRequestDto, feed, member);
        commentService.insertComment(comment);
        return ResponseEntity.status(HttpStatus.OK).body("댓글 쓰기 완료");
    }
}

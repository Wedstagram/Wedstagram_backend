package backend.wedstagram.controller;

import backend.wedstagram.apiPayload.ApiResponse;
import backend.wedstagram.dto.CustomUserDetails;
import backend.wedstagram.domain.Feed;
import backend.wedstagram.dto.FeedDto.FeedRequestDto;
import backend.wedstagram.service.FeedService.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FeedControlller {

    private final FeedService feedService;

    //게시글 쓰기
    @PostMapping("/feed")
    public ApiResponse<String> createFeed(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody FeedRequestDto feedRequestDto) {

        String username = customUserDetails.getUsername();
        feedService.createFeed(username,feedRequestDto);
        return ApiResponse.onSuccess("게시글 작성 완료");
    }

    //게시글 수정
    @PatchMapping("/feed")
    public ApiResponse<String> updateFeed(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody FeedRequestDto feedRequestDto) {

        String username = customUserDetails.getUsername();
        feedService.updateFeed(username,feedRequestDto);
        return ApiResponse.onSuccess("게시글 수정 완료");
    }

    //게시글 삭제
    @DeleteMapping("/feed")
    public ApiResponse<String> deleteFeed(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestParam Long id) {

        String username = customUserDetails.getUsername();
        feedService.deleteFeed(username,id);
        return ApiResponse.onSuccess("게시글 삭제 완료");
    }

    //게시글 좋아요
    @PatchMapping("/feed/{id}/like")
    public ApiResponse<String> feedLike(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable(name="id") Long feedId,Long memberId) {

        String username = customUserDetails.getUsername();
        feedService.feedLike(username,feedId,memberId);
        return ApiResponse.onSuccess("게시글 좋아요");
    }


    //게시글 좋아요 목록 보기

    //게시글 저장

    //게시글 저장 취소

}
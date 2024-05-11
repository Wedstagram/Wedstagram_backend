package backend.wedstagram.controller;

import backend.wedstagram.dto.FeedDto.FeedRequestDto;
import backend.wedstagram.service.FeedLikeService.FeedLikeService;
import backend.wedstagram.service.FeedService.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FeedControlller {

    private final FeedService feedService;
    private final FeedLikeService feedLikeService;

    //게시글 쓰기
    @PostMapping("/feed")
    public ResponseEntity<Boolean> createFeed(@RequestBody FeedRequestDto feedRequestDto) {
        try{
            feedService.saveFeed(feedRequestDto);
        }catch (Exception e){
            return ResponseEntity.ok(false);
        }
        return ResponseEntity.ok(true);
    }

    //게시글 수정
    @PutMapping("/feed")
    public ResponseEntity<Boolean> updateFeed(@RequestBody FeedRequestDto feedRequestDto) {
        try{
            feedService.saveFeed(feedRequestDto);
        }catch (Exception e){
            return ResponseEntity.ok(false);
        }
        return ResponseEntity.ok(true);
    }

    //게시글 삭제
    @DeleteMapping("/feed")
    public ResponseEntity<Boolean> deleteFeed(@RequestParam Long id) {
        return ResponseEntity.ok(feedService.deleteFeed(id));
    }

    //게시글 좋아요

    //게시글 좋아요 취소

    //게시글 좋아요 목록 보기

    //게시글 저장

    //게시글 저장 취소

}
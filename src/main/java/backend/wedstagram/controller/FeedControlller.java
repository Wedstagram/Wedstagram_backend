package backend.wedstagram.controller;

import backend.wedstagram.dto.FeedDto.FeedRequestDto;
import backend.wedstagram.service.FeedService.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FeedControlller {

    private final FeedService feedService;

    //게시글 쓰기
    @PostMapping("/feed")
    public ResponseEntity<Boolean> createFeed(@RequestBody FeedRequestDto feedRequestDto) {
        try{
            feedService.createFeed(feedRequestDto);
        }catch (Exception e){
            return ResponseEntity.ok(false);
        }
        return ResponseEntity.ok(true);
    }

    //게시글 수정
    @PatchMapping("/feed")
    public ResponseEntity<Boolean> updateFeed(@RequestBody FeedRequestDto feedRequestDto) {
        try{
            feedService.updateFeed(feedRequestDto);
        }catch (Exception e){
            return ResponseEntity.ok(false);
        }
        return ResponseEntity.ok(true);
    }

    //게시글 삭제
    @DeleteMapping("/feed")
    public ResponseEntity<String> deleteFeed(@RequestParam Long id) {
        feedService.deleteFeed(id);
        return ResponseEntity.status(HttpStatus.OK).body("게시글 삭제");
    }

    //게시글 좋아요
    @PatchMapping("/feed/{id}/like")
    public ResponseEntity<String> feedLike(@PathVariable(name="id") Long feedId,Long memberId) {
        feedService.feedLike(feedId,memberId);
        return ResponseEntity.status(HttpStatus.OK).body("좋아요");
    }


    //게시글 좋아요 목록 보기

    //게시글 저장

    //게시글 저장 취소

}}
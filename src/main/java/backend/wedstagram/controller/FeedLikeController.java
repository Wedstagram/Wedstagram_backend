package backend.wedstagram.controller;

import backend.wedstagram.dto.FeedDto.FeedLikeRequestDto;
import backend.wedstagram.service.FeedLikeService.FeedLikeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feed/like")
public class FeedLikeController {

    //private final FeedLikeService feedLikeService;

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody @Valid FeedLikeRequestDto feedLikeRequestDto) {
        //feedLikeService.insert(feedLikeRequestDto);
        return ResponseEntity.ok(true);
    }


    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody @Valid FeedLikeRequestDto feedLikeRequestDto) {
        //feedLikeService.delete(feedLikeRequestDto);
        return ResponseEntity.ok(true);
    }

}

package backend.wedstagram.controller;

import backend.wedstagram.Converter.FollowConverter;
import backend.wedstagram.domain.Follow;
import backend.wedstagram.domain.Member;
import backend.wedstagram.dto.FollowDto.FollowDto;
import backend.wedstagram.dto.FollowDto.FollowResponseDto;
import backend.wedstagram.service.FollowService.FollowService;
import backend.wedstagram.service.MemberService.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;
    private final MemberService memberService;

    // 팔로우하기
    @PostMapping("{memberId}/follow")
    public ResponseEntity<String> followUser(@PathVariable(value = "memberId") Long memberId, @RequestBody Long recieveMemberId) {

        Member follower = memberService.getMemberById(memberId);
        Member Following = memberService.getMemberById(recieveMemberId);

        Follow follow = FollowConverter.toFollow(follower, Following);

        followService.saveFollow(follow);
        return ResponseEntity.status(HttpStatus.OK).body("팔로우 완료");
    }

    // 언팔로우하기
    @DeleteMapping("{memberId}/unfollow")
    public ResponseEntity<String> unfollowUser(@PathVariable(value = "memberId") Long memberId, @RequestBody Long recieveMemberId) {

        followService.deleteFollow(memberId, recieveMemberId);
        return ResponseEntity.status(HttpStatus.OK).body("언팔로우 완료");
    }

    // 내 팔로워 보기
    @GetMapping("{memberId}/followers")
    public ResponseEntity<FollowResponseDto> getFollowers(@PathVariable(value = "memberId") Long memberId) {

        List<FollowDto> followers = followService.getFollowers(memberId);

        FollowResponseDto followResponseDto = FollowResponseDto.builder()
                .followList(followers)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(followResponseDto);
    }

    // 내 팔로잉 보기
    @GetMapping("{memberId}/followings")
    public ResponseEntity<FollowResponseDto> getFollowings(@PathVariable(value = "memberId") Long memberId) {

        List<FollowDto> followings = followService.getFollowings(memberId);

        FollowResponseDto followResponseDto = FollowResponseDto.builder()
                .followList(followings)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(followResponseDto);
    }

    // 팔로워 삭제
    @DeleteMapping("{memberId}/followers")
    public ResponseEntity<String> deleteFollowers(@PathVariable(value = "memberId") Long memberId, @RequestBody Long recieveMemberId) {

        followService.deleteFollow(recieveMemberId, memberId);
        return ResponseEntity.status(HttpStatus.OK).body("팔로워 삭제 완료");
    }
}

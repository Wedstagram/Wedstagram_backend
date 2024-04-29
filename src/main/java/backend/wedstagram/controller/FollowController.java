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
    @PostMapping("{userName}/follow")
    public ResponseEntity<String> followUser(@PathVariable(value = "userName") String userName, @RequestBody String recieveUserName) {

        Member follower = memberService.getMemberByUsername(userName);
        Member Following = memberService.getMemberByUsername(recieveUserName);

        Follow follow = FollowConverter.toFollow(follower, Following);

        followService.saveFollow(follow);
        return ResponseEntity.status(HttpStatus.OK).body("팔로우 완료");
    }

    // 언팔로우하기
    @DeleteMapping("{userName}/unfollow")
    public ResponseEntity<String> unfollowUser(@PathVariable(value = "userName") String userName, @RequestBody String recieveUserName) {

        followService.deleteFollow(userName, recieveUserName);
        return ResponseEntity.status(HttpStatus.OK).body("언팔로우 완료");
    }

    // 내 팔로워 보기
    @GetMapping("{userName}/followers")
    public ResponseEntity<FollowResponseDto> getFollowers(@PathVariable(value = "userName") String userName) {

        Member member = memberService.getMemberByUsername(userName);
        List<FollowDto> followers = followService.getFollowers(member.getUserName());

        FollowResponseDto followResponseDto = FollowResponseDto.builder()
                .followList(followers)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(followResponseDto);
    }

    // 내 팔로잉 보기
    @GetMapping("{userName}/followings")
    public ResponseEntity<FollowResponseDto> getFollowings(@PathVariable(value = "userName") String userName) {

        Member member = memberService.getMemberByUsername(userName);
        List<FollowDto> followings = followService.getFollowings(member.getUserName());

        FollowResponseDto followResponseDto = FollowResponseDto.builder()
                .followList(followings)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(followResponseDto);
    }

    // 팔로워 삭제
    @DeleteMapping("{userName}/followers")
    public ResponseEntity<String> deleteFollowers(@PathVariable(value = "userName") String userName, @RequestBody String recieveUserName) {

        followService.deleteFollow(recieveUserName, userName);
        return ResponseEntity.status(HttpStatus.OK).body("팔로워 삭제 완료");
    }
}

package backend.wedstagram.controller;

import backend.wedstagram.Converter.FollowConverter;
import backend.wedstagram.apiPayload.ApiResponse;
import backend.wedstagram.domain.Follow;
import backend.wedstagram.domain.Member;
import backend.wedstagram.dto.FollowDto.FollowMemberDto;
import backend.wedstagram.dto.FollowDto.FollowMemberListResponseDto;
import backend.wedstagram.service.FollowService.FollowService;
import backend.wedstagram.service.MemberService.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class FollowController {

    private final FollowService followService;

    // 팔로우하기
    @PostMapping("/{memberId}/follow")
    public ApiResponse<String> followUser(@PathVariable(value = "memberId") Long memberId, @RequestBody Long recieveMemberId) {

        Long newFollow = followService.saveFollow(memberId, recieveMemberId);
        return ApiResponse.onSuccess("팔로우 완료");
    }

    // 언팔로우하기
    @DeleteMapping("/{memberId}/unfollow")
    public ApiResponse<String> unfollowUser(@PathVariable(value = "memberId") Long memberId, @RequestBody Long recieveMemberId) {

        followService.deleteFollow(memberId, recieveMemberId);
        return ApiResponse.onSuccess("언팔로우 완료");
    }

    // 팔로워 보기, memberId가 searchMemberId의 팔로워 보기
    @GetMapping("/{memberId}/followers/{searchMemberId}")
    public ApiResponse<FollowMemberListResponseDto> getFollowers(@PathVariable Long memberId, @PathVariable Long searchMemberId) {

        FollowMemberListResponseDto followers = followService.getFollowers(memberId, searchMemberId);


        return ApiResponse.onSuccess(followers);
    }

    // 팔로잉 보기, memberId가 searchMemberId의 팔로잉 보기
    @GetMapping("/{memberId}/followings/{searchMemberId}")
    public ApiResponse<FollowMemberListResponseDto> getFollowings(@PathVariable Long memberId, @PathVariable Long searchMemberId) {

        FollowMemberListResponseDto followings = followService.getFollowings(memberId, searchMemberId);

        return ApiResponse.onSuccess(followings);
    }

    // 팔로워 삭제
    @DeleteMapping("/{memberId}/followers")
    public ApiResponse<String> deleteFollowers(@PathVariable(value = "memberId") Long memberId, @RequestBody Long recieveMemberId) {

        followService.deleteFollow(recieveMemberId, memberId);
        return ApiResponse.onSuccess("팔로워 삭제 완료");
    }
}

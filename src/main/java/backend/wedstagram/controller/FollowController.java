package backend.wedstagram.controller;

import backend.wedstagram.apiPayload.ApiResponse;
import backend.wedstagram.dto.CustomUserDetails;
import backend.wedstagram.dto.FollowDto.FollowMemberListResponseDto;
import backend.wedstagram.service.FollowService.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    // 팔로우하기
    @PostMapping("/follow/{recieveUsername}")
    public ApiResponse<String> followUser(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable String recieveUsername) {
        String username = customUserDetails.getUsername();
        Long newFollow = followService.saveFollow(username, recieveUsername);
        return ApiResponse.onSuccess("팔로우 완료");
    }

    // 언팔로우하기
    @DeleteMapping("/unfollow/{recieveMemberUsername}")
    public ApiResponse<String> unfollowUser(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable String recieveUsername) {

        String username = customUserDetails.getUsername();
        followService.deleteFollow(username, recieveUsername);
        return ApiResponse.onSuccess("언팔로우 완료");
    }

    // 팔로워 보기, member가 searchUsername의 팔로워 보기
    @GetMapping("/followers/{searchUsername}")
    public ApiResponse<FollowMemberListResponseDto> getFollowers(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable String searchUsername) {

        String username = customUserDetails.getUsername();
        FollowMemberListResponseDto followers = followService.getFollowers(username, searchUsername);

        return ApiResponse.onSuccess(followers);
    }

    // 팔로잉 보기, member가 searchUsername의 팔로잉 보기
    @GetMapping("/{memberId}/followings/{searchUsername}")
    public ApiResponse<FollowMemberListResponseDto> getFollowings(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable String searchUsername) {

        String username = customUserDetails.getUsername();
        FollowMemberListResponseDto followings = followService.getFollowings(username, searchUsername);

        return ApiResponse.onSuccess(followings);
    }

    // 팔로워 삭제
    @DeleteMapping("/followers/{recieveUsername}")
    public ApiResponse<String> deleteFollowers(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable String recieveUsername) {

        String username = customUserDetails.getUsername();
        followService.deleteFollow(username, recieveUsername);
        return ApiResponse.onSuccess("팔로워 삭제 완료");
    }
}

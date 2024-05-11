package backend.wedstagram.service.FollowService;

import backend.wedstagram.domain.Follow;
import backend.wedstagram.dto.FollowDto.FollowMemberDto;
import backend.wedstagram.dto.FollowDto.FollowMemberListResponseDto;

import java.util.List;

public interface FollowService {

    public Long saveFollow(Long memberId, Long receiverId);
    public void deleteFollow(Long followerMemberId, Long followingMemberId);
    public void findFollowByFollowerAndFollowing(Long followerMemberId, Long followingMemberId);
    public FollowMemberListResponseDto getFollowers(Long memberId, Long searchMemberId);
    public FollowMemberListResponseDto getFollowings(Long memberId, Long searchMemberId);

}

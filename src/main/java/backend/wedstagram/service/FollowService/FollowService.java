package backend.wedstagram.service.FollowService;

import backend.wedstagram.domain.Follow;
import backend.wedstagram.dto.FollowDto.FollowDto;

import java.util.List;

public interface FollowService {

    public void saveFollow(Follow follow);
    public void deleteFollow(Long followerMemberId, Long followingMemberId);
    public void findFollowByFollowerAndFollowing(Long followerMemberId, Long followingMemberId);
    public List<FollowDto> getFollowers(Long memberId);
    public List<FollowDto> getFollowings(Long memberId);

}

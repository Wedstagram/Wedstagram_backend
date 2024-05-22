package backend.wedstagram.service.FollowService;

import backend.wedstagram.domain.Follow;
import backend.wedstagram.dto.FollowDto.FollowMemberDto;
import backend.wedstagram.dto.FollowDto.FollowMemberListResponseDto;

import java.util.List;

public interface FollowService {

    public Long saveFollow(String username, String recieveMemberUsername);
    public void deleteFollow(String followerUsername, String recieveUsername);
    public void findFollowByFollowerAndFollowing(String followerUsername, String recieveUsername);
    public FollowMemberListResponseDto getFollowers(String username, String searchUsername);
    public FollowMemberListResponseDto getFollowings(String username, String searchUsername);

}

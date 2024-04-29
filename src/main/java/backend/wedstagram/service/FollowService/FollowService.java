package backend.wedstagram.service.FollowService;

import backend.wedstagram.domain.Follow;
import backend.wedstagram.dto.FollowDto.FollowDto;

import java.util.List;

public interface FollowService {

    public void saveFollow(Follow follow);
    public void deleteFollow(String followerUserName, String followingUserName);

    public List<FollowDto> getFollowers(String userName);
    public List<FollowDto> getFollowings(String userName);

}

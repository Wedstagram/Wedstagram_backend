package backend.wedstagram.service.FollowService;

import backend.wedstagram.domain.Follow;
import backend.wedstagram.domain.Member;
import backend.wedstagram.dto.FollowDto.FollowDto;
import backend.wedstagram.repository.FollowRepository;
import backend.wedstagram.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;

    @Override
    public void saveFollow(Follow follow) {
        followRepository.save(follow);
    }

    @Override
    public void deleteFollow(String followerUserName, String followingUserName) {
        followRepository.deleteFollowByFollowerAndFollowing(followerUserName, followingUserName);
    }

    @Override
    public void findFollowByFollowerAndFollowing(String followerUserName, String followingUserName) {
        followRepository.findByFollowerAndFollowing(followerUserName, followingUserName);
    }

    @Override
    public List<FollowDto> getFollowers(String userName) {
        Member member = memberRepository.findByUserName(userName).get();
        List<Follow> followers = member.getFollowers();

        List<FollowDto> collectFollowDtos = followers.stream().map(Follow::getFollower).map(follower -> FollowDto.builder()
                .id(follower.getId())
                .userName(follower.getUserName())
                .name(follower.getName())
                .profileImage(follower.getImageUrl())
                .build()).collect(Collectors.toList());

        return collectFollowDtos;
    }

    @Override
    public List<FollowDto> getFollowings(String userName) {
        Member member = memberRepository.findByUserName(userName).get();
        List<Follow> followings = member.getFollowings();

        List<FollowDto> collectFollowDtos = followings.stream().map(Follow::getFollowing).map(following -> FollowDto.builder()
                .id(following.getId())
                .userName(following.getUserName())
                .name(following.getName())
                .profileImage(following.getImageUrl())
                .build()).collect(Collectors.toList());

        return collectFollowDtos;
    }
}

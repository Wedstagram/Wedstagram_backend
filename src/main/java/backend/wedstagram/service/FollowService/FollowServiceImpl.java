package backend.wedstagram.service.FollowService;

import backend.wedstagram.Converter.FollowConverter;
import backend.wedstagram.apiPayload.code.status.ErrorStatus;
import backend.wedstagram.apiPayload.exception.GeneralException;
import backend.wedstagram.domain.Follow;
import backend.wedstagram.domain.Member;
import backend.wedstagram.dto.FollowDto.FollowMemberDto;
import backend.wedstagram.dto.FollowDto.FollowMemberListResponseDto;
import backend.wedstagram.repository.FollowRepository;
import backend.wedstagram.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;

    @Override
    public Long saveFollow(Long memberId, Long receiverId) {
        //자기 자신을 팔로우한 경우
        if(memberId.equals(receiverId)){
            throw new GeneralException(ErrorStatus.FOLLOW_SELF);
        }

        //이미 팔로우한 경우
        Boolean exists = followRepository.existsByFollowerIdAndFollowingId(memberId, receiverId);
        if(exists){
            throw new GeneralException(ErrorStatus.FOLLOW_ALREADY_EXIST);
        }

        //회원이 존재하지 않는 경우
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));
        Member receiver = memberRepository.findById(receiverId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        Follow follow = FollowConverter.toFollow(member, receiver);
        return followRepository.save(follow).getId();
    }

    @Override
    public void deleteFollow(Long followerId, Long followingId) {
        //자기 자신을 팔로우 삭제할 수 없다
        if (followerId.equals(followingId)) {
            throw new GeneralException(ErrorStatus.FOLLOW_SELF);
        }


        Boolean exists = followRepository.existsByFollowerIdAndFollowingId(followerId, followingId);
        if(exists) {
            Follow follow = followRepository.findByFollowerIdAndFollowingId(followerId, followingId).orElseThrow();
            followRepository.delete(follow);
        }
        //팔로우 중이 아닌 경우
        else {
            throw new GeneralException(ErrorStatus.FOLLOW_NOT_FOUND);
        }
    }

    @Override
    public void findFollowByFollowerAndFollowing(Long followerId, Long followingId) {
        followRepository.findByFollowerIdAndFollowingId(followerId, followingId);
    }

    @Override
    public FollowMemberListResponseDto getFollowers(Long memberId, Long searchMemberId) {
        Member member = memberRepository.findById(searchMemberId).orElseThrow();
        List<Follow> followers = member.getFollowers();

        List<FollowMemberDto> collectFollowMemberDto = followers.stream()
                .map(Follow::getFollower)
                .map((follower) -> {
                    FollowMemberDto followMemberDto = FollowConverter.toFollowMemberDto(follower);
                    Boolean isFollowing = followRepository.existsByFollowerIdAndFollowingId(memberId, followMemberDto.getId());
                    followMemberDto.updateIsFollowing(isFollowing);

                    return followMemberDto;
                })
                .collect(Collectors.toList());

        FollowMemberListResponseDto followMemberListResponseDto = FollowConverter.toFollowMemberListResponseDto(collectFollowMemberDto);

        return followMemberListResponseDto;
    }

    @Override
    public FollowMemberListResponseDto getFollowings(Long memberId, Long searchMemberId) {
        Member member = memberRepository.findById(searchMemberId).orElseThrow();
        List<Follow> followings = member.getFollowings();

        List<FollowMemberDto> collectFollowMemberDto = followings.stream()
                .map(Follow::getFollowing)
                .map((following) -> {
                    FollowMemberDto followMemberDto = FollowConverter.toFollowMemberDto(following);
                    Boolean isFollowing = followRepository.existsByFollowerIdAndFollowingId(memberId, followMemberDto.getId());
                    followMemberDto.updateIsFollowing(isFollowing);

                    return followMemberDto;
                })
                .collect(Collectors.toList());

        FollowMemberListResponseDto followMemberListResponseDto = FollowConverter.toFollowMemberListResponseDto(collectFollowMemberDto);

        return followMemberListResponseDto;
    }

    //.map(followMemberDto -> followMemberDto.updateIsFollowing(followRepository.findByFollowerIdAndFollowingId(memberId, followMemberDto.getId()) != null))
}

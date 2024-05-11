package backend.wedstagram.followTest;

import backend.wedstagram.Converter.FollowConverter;
import backend.wedstagram.domain.Follow;
import backend.wedstagram.domain.Member;
import backend.wedstagram.dto.FollowDto.FollowMemberDto;
import backend.wedstagram.dto.FollowDto.FollowMemberListResponseDto;
import backend.wedstagram.repository.FollowRepository;
import backend.wedstagram.repository.MemberRepository;
import backend.wedstagram.service.FollowService.FollowService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@Transactional
public class FollowTest {

    private final MemberRepository memberRepository;
    private final FollowService followService;
    private final FollowRepository followRepository;

    @Autowired
    public FollowTest(MemberRepository memberRepository, FollowService followService, FollowRepository followRepository) {
        this.memberRepository = memberRepository;
        this.followService = followService;
        this.followRepository = followRepository;
    }

    @Test
    public void 팔로우하기() throws Exception {
        //given
        Member member1 = Member.builder()
                .name("정연")
                .userName("282.28_")
                .password("1234")
                .build();

        Member member2 = Member.builder()
                .name("유진")
                .userName("yuddin")
                .password("1234")
                .build();

        memberRepository.save(member1);
        memberRepository.save(member2);

        Member findMember1 = memberRepository.findById(member1.getId()).orElseThrow();
        Member findMember2 = memberRepository.findById(member2.getId()).orElseThrow();

        //when

        Long followId = followService.saveFollow(findMember1.getId(), findMember2.getId());

        Follow findFollow = followRepository.findByFollowerIdAndFollowingId(member1.getId(), member2.getId()).orElseThrow();

        //then
        Assertions.assertThat(followId).isEqualTo(findFollow.getId());
    }

//    @Test
//    public void 팔로워보기() throws Exception {
//        //given
//        Member member1 = Member.builder()
//                .name("정연")
//                .userName("282.28_")
//                .password("1234")
//                .build();
//
//        Member member2 = Member.builder()
//                .name("유진")
//                .userName("yuddin")
//                .password("1234")
//                .build();
//
//        Member member3 = Member.builder()
//                .name("석환")
//                .userName("tjrghks")
//                .password("1234")
//                .build();
//
//        memberRepository.save(member1);
//        memberRepository.save(member2);
//        memberRepository.save(member3);
//
//        //1이 2를 팔로우
//        Long followId1 = followService.saveFollow(member1.getId(), member2.getId());
//        //2가 1을 팔로우
//        Long followId2 = followService.saveFollow(member2.getId(), member1.getId());
//
//        Member findMember3 = memberRepository.findById(member3.getId()).orElseThrow();
//
//
//        //when
//        Member findMember1 = memberRepository.findById(member1.getId()).orElseThrow();
//
//        List<Follow> followers = findMember1.getFollowers();
//        List<Follow> followings = findMember1.getFollowings();
//
//        System.out.println("followers = " + followers);
//        System.out.println("followings = " + followings);
//
//        List<FollowMemberDto> findFollowers = followings.stream()
//                .map(Follow::getFollower)
//                .map(follower -> {
//                    FollowMemberDto followMemberDto = FollowConverter.toFollowMemberDto(follower);
//                    Boolean isFollowing = followRepository.existsByFollowerIdAndFollowingId(findMember3.getId(), followMemberDto.getId());
//                    followMemberDto.updateIsFollowing(isFollowing);
//                    return followMemberDto;
//                })
//                .collect(Collectors.toList());
//
//        System.out.println("findFollowers = " + findFollowers);
//
//        //3이 1의 팔로워 목록 조회
//        FollowMemberListResponseDto followersResponse = followService.getFollowers(member3.getId(), member1.getId());
//
//        System.out.println("followersResponse = " + followersResponse);
//
//
//        //then
//        Assertions.assertThat(followersResponse).isEqualTo(FollowConverter.toFollowMemberListResponseDto(findFollowers));
//    }


}

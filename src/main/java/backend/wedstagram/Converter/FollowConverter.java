package backend.wedstagram.Converter;

import backend.wedstagram.domain.Follow;
import backend.wedstagram.domain.Member;
import backend.wedstagram.dto.FollowDto.FollowMemberDto;
import backend.wedstagram.dto.FollowDto.FollowMemberListResponseDto;

import java.util.List;

public class FollowConverter {

    public static Follow toFollow(Member follower, Member following){
        return Follow.builder()
                .follower(follower)
                .following(following)
                .build();
    }

    public static FollowMemberDto toFollowMemberDto(Member member){
        return FollowMemberDto.builder()
                .id(member.getId())
                .name(member.getName())
                .userName(member.getUserName())
                .profileImage(member.getImageUrl())
                .isFollowing(false)
                .build();
    }

    public static FollowMemberListResponseDto toFollowMemberListResponseDto(List<FollowMemberDto> followers){
        return FollowMemberListResponseDto.builder()
                .followList(followers)
                .build();
    }
}

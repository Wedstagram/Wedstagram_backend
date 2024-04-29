package backend.wedstagram.Converter;

import backend.wedstagram.domain.Follow;
import backend.wedstagram.domain.Member;

public class FollowConverter {

    public static Follow toFollow(Member follower, Member following){
        return Follow.builder()
                .follower(follower)
                .following(following)
                .build();
    }
}

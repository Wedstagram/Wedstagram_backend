package backend.wedstagram.repository;

import backend.wedstagram.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow,Long> {
    Follow findByFollowerAndFollowing(String followerUserName, String followingUserName);

    List<Follow> findAllByFollowingId(Long followingId);

    Follow deleteFollowByFollowerAndFollowing(String followerUserName, String followingUserName);
}

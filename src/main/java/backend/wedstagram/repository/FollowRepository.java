package backend.wedstagram.repository;

import backend.wedstagram.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow,Long> {
    Optional<Follow> findByFollowerUsernameAndFollowingUsername(String followerUsername, String followingUsername);

    Boolean existsByFollowerUsernameAndFollowingUsername(String followerUsername, String followingUsername);

    List<Follow> findAllByFollowingId(Long followingId);

    List<Follow> findAllByFollowerId(Long followerId);

    Follow deleteFollowByFollowerIdAndFollowingId(Long followerId, Long followingId);
}

package backend.wedstagram.repository;

import backend.wedstagram.domain.Feed;
import backend.wedstagram.domain.FeedLike;
import backend.wedstagram.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedLikeRepository extends JpaRepository<FeedLike,Long> {



    Optional<FeedLike> findByFeedAndMember(Feed feed, Member member);
}

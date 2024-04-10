package backend.wedstagram.repository;

import backend.wedstagram.domain.FeedLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedLikeRepository extends JpaRepository<FeedLike,Long> {
}

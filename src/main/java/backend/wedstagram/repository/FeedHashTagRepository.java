package backend.wedstagram.repository;

import backend.wedstagram.domain.FeedHashTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedHashTagRepository extends JpaRepository<FeedHashTag,Long> {
}

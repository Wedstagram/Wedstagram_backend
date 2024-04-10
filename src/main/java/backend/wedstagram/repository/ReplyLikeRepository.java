package backend.wedstagram.repository;

import backend.wedstagram.domain.ReplyLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyLikeRepository extends JpaRepository<ReplyLike,Long> {
}

package backend.wedstagram.repository;

import backend.wedstagram.domain.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashTagRepository extends JpaRepository<HashTag,Long> {
}

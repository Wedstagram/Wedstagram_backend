package backend.wedstagram.repository;

import backend.wedstagram.domain.Saved;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavedRepository extends JpaRepository<Saved,Long> {
}

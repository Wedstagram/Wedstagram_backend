package backend.wedstagram.repository;

import backend.wedstagram.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findById(Long memberId);

    Optional<Member> findByUsername(String username);

    Boolean existsByUsername(String username);

}

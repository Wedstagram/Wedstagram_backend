package backend.wedstagram.service.MemberService;

import backend.wedstagram.domain.Member;

public interface MemberService {

    Member getMemberById(Long memberId);

    Member getMemberByUsername(String username);
}

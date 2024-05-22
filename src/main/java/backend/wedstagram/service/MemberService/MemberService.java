package backend.wedstagram.service.MemberService;

import backend.wedstagram.domain.Member;
import backend.wedstagram.dto.JoinDto;

public interface MemberService {

    public Member getMemberById(Long memberId);

    public void join(JoinDto joinDto);
}

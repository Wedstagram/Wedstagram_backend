package backend.wedstagram.service.MemberService;

import backend.wedstagram.domain.Member;
import backend.wedstagram.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public Member getMemberById(Long memberId){
        return memberRepository.findById(memberId).orElseThrow();
    }

    @Override
    public Member getMemberByUsername(String username) {
        return memberRepository.findByUserName(username).orElseThrow();
    }

}

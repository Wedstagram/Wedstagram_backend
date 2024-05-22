package backend.wedstagram.service.MemberService;

import backend.wedstagram.domain.Member;
import backend.wedstagram.dto.JoinDto;
import backend.wedstagram.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Member getMemberById(Long memberId){
        return memberRepository.findById(memberId).orElseThrow();
    }

    @Override
    public void join(JoinDto joinDto) {
        Boolean exists = memberRepository.existsByUsername(joinDto.getUsername());
        if(exists) {
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }

        Member member = Member.builder()
                .username(joinDto.getUsername())
                .password(bCryptPasswordEncoder.encode(joinDto.getPassword()))
                .role("ROLE_USER")
                .build();
        memberRepository.save(member);
    }

}

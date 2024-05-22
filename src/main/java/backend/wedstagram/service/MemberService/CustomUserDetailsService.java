package backend.wedstagram.service.MemberService;


import backend.wedstagram.domain.Member;
import backend.wedstagram.dto.CustomUserDetails;
import backend.wedstagram.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username).orElseThrow();
        if(member != null) {
            return new CustomUserDetails(member);
        }
        else {
            return null;
//            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
    }
}

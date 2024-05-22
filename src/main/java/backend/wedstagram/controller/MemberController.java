package backend.wedstagram.controller;


import backend.wedstagram.dto.JoinDto;
import backend.wedstagram.service.MemberService.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public String joinProcess(@RequestBody JoinDto joinDto) {
        memberService.join(joinDto);
        return "join Success!";
    }
}

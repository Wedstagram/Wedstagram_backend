package backend.wedstagram.service.ChatService;

import backend.wedstagram.domain.ChatRoom;
import backend.wedstagram.domain.Member;
import backend.wedstagram.repository.ChatRoomRepository;
import backend.wedstagram.service.MemberService.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{

    private final MemberService memberService;
    private final ChatRoomRepository chatRoomRepository;

    @Override
    public List<ChatRoom> findAllRoom() {
        return chatRoomRepository.findAll();
    }

    /*public ChatResponseDto.ChatPreviewDtoList findAllRoom() {
        return
    }*/
    @Override
    public ChatRoom findRoomById(Long roomId) {
        return chatRoomRepository.findById(roomId).orElseThrow();
    }
    @Override
    public ChatRoom createRoom(Long user1Id, Long user2Id) {
        Member memberOne = memberService.getMemberById(user1Id);
        Member memberTwo = memberService.getMemberById(user2Id);

        ChatRoom chatRoom = ChatRoom.builder()
                .memberOne(memberOne)
                .memberTwo(memberTwo)
                .build();

        return chatRoomRepository.save(chatRoom);
    }
}

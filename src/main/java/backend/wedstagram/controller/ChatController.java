package backend.wedstagram.controller;

import backend.wedstagram.domain.ChatRoom;
import backend.wedstagram.service.ChatService.ChatServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    private final ChatServiceImpl chatService;

    @PostMapping
    public ChatRoom createRoom(@RequestParam Long user1Id, @RequestParam Long user2Id){
        return chatService.createRoom(user1Id,user2Id);
    }

    @GetMapping
    public List<ChatRoom> findAllRoom(){
        return chatService.findAllRoom();
    }
}

package backend.wedstagram.service.ChatService;

import backend.wedstagram.domain.ChatRoom;

import java.util.List;

public interface ChatService {
    List<ChatRoom> findAllRoom();

    ChatRoom findRoomById(Long roomId);

    ChatRoom createRoom(Long user1Id, Long user2Id);
}

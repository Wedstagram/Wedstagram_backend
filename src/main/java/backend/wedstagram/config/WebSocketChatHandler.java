package backend.wedstagram.config;

import backend.wedstagram.domain.ChatRoom;
import backend.wedstagram.domain.Message;
import backend.wedstagram.repository.MessageRepository;
import backend.wedstagram.service.ChatService.ChatServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler {
    private final Map<String, WebSocketSession> sessions = new HashMap<>();
    private final MessageRepository messageRepository;
    private final ObjectMapper objectMapper;
    private final ChatServiceImpl chatService;

    //최초 연결 시
    @Override
        public void afterConnectionEstablished(WebSocketSession session) throws Exception {
            final String sessionId = session.getId();
            final String enteredMessage = sessionId + "님이 입장하셨습니다.";

        sessions.put(sessionId, session);

        sendMessage(sessionId, new TextMessage(enteredMessage));

    }

    //양방향 데이터 통신할 떄 해당 메서드가 call 된다.
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        // 전달받은 메세지
        String msg = message.getPayload();

        // Json객체 → Java객체
        Message chatMessage = objectMapper.readValue(msg,Message.class);

        // 받은 메세지에 담긴 roomId로 해당 채팅방을 찾아온다.
        ChatRoom chatRoom = chatMessage.getChatRoom();

        // 채팅 세션 목록에 채팅방이 존재하지 않고, 처음 들어왔고, DB에서의 채팅방이 있을 때
        // 채팅방 생성
        if(chatRoom.getId()==null) {
            chatService.createRoom(chatRoom.getMemberOne().getId(),chatRoom.getMemberTwo().getId());
        }

        final String sessionId = session.getId();
        TextMessage textMessage = new TextMessage(chatMessage.getMessage());
        sendMessage(sessionId, textMessage);

        // DB에 저장한다.
        messageRepository.save(chatMessage);
    }

    //웹소켓 종료
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        final String sessionId = session.getId();
        final String leaveMessage = sessionId + "님이 떠났습니다.";
        sessions.remove(sessionId); // 삭제

        sendMessage(sessionId, new TextMessage(leaveMessage));

    }

    //통신 에러 발생 시
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {}

    private void sendMessage(String sessionId, WebSocketMessage<?> message) {
        sessions.values().forEach(s -> {
            if(!s.getId().equals(sessionId) && s.isOpen()) {
                try {
                    s.sendMessage(message);
                } catch (IOException e) {}
            }
        });
    }
}

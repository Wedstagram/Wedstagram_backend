package backend.wedstagram.dto.ChatDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class ChatResponseDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChatPreviewDto{
        String receiver_username;
        String receiver_image;
        String Last_chat_message;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChatPreviewDtoList{
        List<ChatPreviewDto> chatPreviewDtoList;
    }
}

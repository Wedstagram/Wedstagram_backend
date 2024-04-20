package backend.wedstagram.Converter;

import backend.wedstagram.domain.Comment;
import backend.wedstagram.domain.Feed;
import backend.wedstagram.domain.Member;
import backend.wedstagram.dto.CommentDTO.CommentRequestDTO;

public class CommentConverter {

    public static Comment toComment(CommentRequestDTO.CommentWriteRequestDto commentWriteRequestDto, Feed feed, Member member){
        return Comment.builder()
                .feed(feed)
                .member(member)
                .text(commentWriteRequestDto.getText())
                .build();
    }
}

package backend.wedstagram.dto.FeedDto;

import backend.wedstagram.domain.Feed;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedRequestDto {

    private Long id;
    private String imageUrl;
    private String content;
//    private List<HashTagDto> hashTag;

    public static Feed toFeed(FeedRequestDto feedRequestDto) {
        return Feed.builder()
                .id(feedRequestDto.getId())
                .content(feedRequestDto.getContent())
                .imageUrl(feedRequestDto.getImageUrl())
                //  .feedHashTagList(feedRequestDto.getHashTag())
                .build();
    }

}
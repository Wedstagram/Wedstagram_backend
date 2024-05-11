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
public class FeedResponseDto {
    private List<FeedRequestDto> Name;
    private List<FeedRequestDto> username;
    private List<FeedRequestDto> profile;

    private Long feedId;
    private String imageUrl;
    private String content;
    //private List<HashTagDto> hashTag;

    public static Feed toFeed(FeedResponseDto feedResponseDto) {
        return Feed.builder()
                .id(feedResponseDto.getFeedId())
                .content(feedResponseDto.getContent())
                .imageUrl(feedResponseDto.getImageUrl())
                // .feedHashTagList(feedResponseDto.getHashTag())
                .build();
    }

}

package backend.wedstagram.dto.FeedDto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedLikeRequestDto {
    private Long feedId;
    private Long memberId;

    public FeedLikeRequestDto(Long feedId, Long memberId) {
        this.feedId = feedId;
        this.memberId = memberId;
    }
}

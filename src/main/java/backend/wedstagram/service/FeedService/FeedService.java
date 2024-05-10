package backend.wedstagram.service.FeedService;

import backend.wedstagram.domain.Feed;
import backend.wedstagram.dto.FeedDto.FeedRequestDto;

public interface FeedService {

    Feed getFeedById(Long feedId);
    public void createFeed(FeedRequestDto feedRequestDto);
    public void deleteFeed(Long id);
    public FeedRequestDto updateFeed(FeedRequestDto feedRequestDto);
    public void feedLike(Long feedId,Long memberId);
}

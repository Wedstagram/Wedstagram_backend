package backend.wedstagram.service.FeedService;

import backend.wedstagram.domain.Feed;
import backend.wedstagram.dto.FeedDto.FeedRequestDto;

public interface FeedService {

    Feed getFeedById(Long feedId);
    public void createFeed(String username, FeedRequestDto feedRequestDto);
    public void deleteFeed(String username, Long id);
    public void updateFeed(String username, FeedRequestDto feedRequestDto);
    public void feedLike(String username, Long feedId,Long memberId);
}

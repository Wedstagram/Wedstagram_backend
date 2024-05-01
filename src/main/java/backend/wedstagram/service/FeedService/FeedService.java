package backend.wedstagram.service.FeedService;

import backend.wedstagram.domain.Feed;
import backend.wedstagram.dto.FeedDto.FeedRequestDto;

public interface FeedService {

    Feed getFeedById(Long feedId);
    public boolean saveFeed(FeedRequestDto feedRequestDto);
    public boolean deleteFeed(Long id);
    public boolean updateFeed(FeedRequestDto feedRequestDto);
}

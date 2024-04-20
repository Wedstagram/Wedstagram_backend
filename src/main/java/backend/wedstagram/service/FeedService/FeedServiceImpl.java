package backend.wedstagram.service.FeedService;

import backend.wedstagram.domain.Feed;
import backend.wedstagram.repository.FeedRepository;
import backend.wedstagram.service.FeedService.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

    private final FeedRepository feedRepository;

    @Override
    public Feed getFeedById(Long feedId){
        return feedRepository.findById(feedId).orElseThrow();
    }
}

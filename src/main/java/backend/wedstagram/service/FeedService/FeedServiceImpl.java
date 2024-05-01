package backend.wedstagram.service.FeedService;

import backend.wedstagram.domain.Feed;
import backend.wedstagram.dto.FeedDto.FeedRequestDto;
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
    @Override
    public boolean saveFeed(FeedRequestDto feedRequestDto){
        feedRepository.save(FeedRequestDto.toFeed(feedRequestDto));
        return false;
    }

    public boolean deleteFeed(Long id){
        feedRepository.deleteById(id);
        return true;
    }

    public boolean updateFeed(FeedRequestDto feedRequestDto){

        Feed feed = feedRepository.findById(feedRequestDto.getFeedId()).orElseThrow(RuntimeException::new);
        feed.update(feedRequestDto);
                //    feedRequestDto.getHashTag()
        try{
            feedRepository.save(feed);
        }catch (Exception e){
            return false;
        }
        return true;
    };
}

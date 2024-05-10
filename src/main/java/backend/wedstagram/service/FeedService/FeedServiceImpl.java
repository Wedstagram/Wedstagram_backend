package backend.wedstagram.service.FeedService;

import backend.wedstagram.domain.Feed;
import backend.wedstagram.domain.FeedLike;
import backend.wedstagram.domain.Member;
import backend.wedstagram.dto.FeedDto.FeedRequestDto;
import backend.wedstagram.repository.FeedLikeRepository;
import backend.wedstagram.repository.FeedRepository;
import backend.wedstagram.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

    private final FeedRepository feedRepository;
    private final FeedLikeRepository feedLikeRepository;
    private final MemberRepository memberRepository;

    @Override
    public Feed getFeedById(Long feedId){
        return feedRepository.findById(feedId).orElseThrow();
    }

    @Override
    public void createFeed(FeedRequestDto feedRequestDto){
        feedRepository.save(FeedRequestDto.toFeed(feedRequestDto));
        //cnt++;
    }

    public void deleteFeed(Long id){
        feedRepository.deleteById(id);
    }

    public FeedRequestDto updateFeed(FeedRequestDto feedRequestDto){

        Feed feed = feedRepository.findById(feedRequestDto.getId()).orElseThrow(RuntimeException::new);
        feed.update(feedRequestDto);

        return feedRequestDto;
    };

    public void feedLike(Long feedId,Long memberId){
        Feed feed = feedRepository.findById(feedId).orElseThrow(RuntimeException::new);
        Member member=memberRepository.findById(memberId).orElseThrow(RuntimeException::new);

        Optional<FeedLike> byFeedAndMember=feedLikeRepository.findByFeedAndMember(feed,member);

        byFeedAndMember.ifPresentOrElse(
                //좋아요 이미 존재하면 좋아요 삭제
                feedLike -> {
                    feedLikeRepository.delete(feedLike);
                    feed.discountLike(feedLike);
                },
                // 좋아요가 없을 경우 좋아요 추가
                () -> {
                    FeedLike feedLike = FeedLike.builder().build();

                    feedLike.mappingFeed(feed);
                    feedLike.mappingMember(member);
                    feed.updateLikeCount();

                    feedLikeRepository.save(feedLike);
                }

        );
    }
}

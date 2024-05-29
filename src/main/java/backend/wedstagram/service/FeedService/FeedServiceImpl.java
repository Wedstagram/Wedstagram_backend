package backend.wedstagram.service.FeedService;

import backend.wedstagram.apiPayload.code.status.ErrorStatus;
import backend.wedstagram.apiPayload.exception.GeneralException;
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
    public void createFeed(String username,FeedRequestDto feedRequestDto){

        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));
        Feed feed = FeedRequestDto.toFeed(feedRequestDto);
        feed.setMember(member);
        feedRepository.save(feed);
        //cnt++;
    }

    public void deleteFeed(String username,Long id){

        Feed feed = feedRepository.findById(id)
                .orElseThrow(() -> new GeneralException(ErrorStatus.FEED_NOT_FOUND));
        if (!feed.getMember().getUsername().equals(username)) {
            throw new GeneralException(ErrorStatus.FEED_UNAUTHORIZED);
        }
        feedRepository.deleteById(id);
    }

    @Override
    public void updateFeed(String username, FeedRequestDto feedRequestDto) {
        Feed feed = feedRepository.findById(feedRequestDto.getId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.FEED_NOT_FOUND));
        if (!feed.getMember().getUsername().equals(username)) {
            throw new GeneralException(ErrorStatus.FEED_UNAUTHORIZED);
        }
        feed.update(feedRequestDto);
        feedRepository.save(feed);
    }


    public void feedLike(String username,Long feedId,Long memberId){
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.FEED_NOT_FOUND));
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

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

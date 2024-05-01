package backend.wedstagram.service.FeedLikeService;

import backend.wedstagram.domain.Feed;
import backend.wedstagram.domain.FeedLike;
import backend.wedstagram.domain.Member;
import backend.wedstagram.dto.FeedDto.FeedLikeRequestDto;
import backend.wedstagram.repository.FeedLikeRepository;
import backend.wedstagram.repository.FeedRepository;
import backend.wedstagram.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

public class FeedLikeServiceImpl {

    MemberRepository memberRepository;
    FeedRepository feedRepository;
    FeedLikeRepository feedLikeRepository;

    @Transactional
    public void insert(FeedLikeRequestDto feedLikeRequestDto) throws Exception {

        Member member = memberRepository.findById(feedLikeRequestDto.getMemberId())
                .orElseThrow(() -> new NotFoundException("Could not found member id : " + feedLikeRequestDto.getMemberId()));

        Feed feed = feedRepository.findById(feedLikeRequestDto.getFeedId())
                .orElseThrow(() -> new NotFoundException("Could not found feed id : " + feedLikeRequestDto.getFeedId()));

        // 이미 좋아요되어있으면 에러 반환
        if (FeedLikeRepository.findByMemberAndFeed(member, feed).isPresent()){
            //TODO 409에러로 변경
            throw new Exception();
        }

        FeedLike feedLike = FeedLike.builder()
                .feed(feed)
                .member(member)
                .build();

        feedLikeRepository.save(feedLike);
    }

    @Transactional
    public void delete(FeedLikeRequestDto feedLikeRequestDto) {

        Member member = memberRepository.findById(feedLikeRequestDto.getMemberId())
                .orElseThrow(() -> new NotFoundException("Could not found member id : " + feedLikeRequestDto.getMemberId()));

        Feed feed = feedRepository.findById(feedLikeRequestDto.getFeedId())
                .orElseThrow(() -> new NotFoundException("Could not found feed id : " + feedLikeRequestDto.getFeedId()));


        FeedLike feedLike = (FeedLike) FeedLikeRepository.findByMemberAndFeed(member, feed)
                .orElseThrow(() -> new NotFoundException("Could not found heart id"));

        feedLikeRepository.delete(feedLike);
    }

//    @Override
//    public void updateCount(Board board1, boolean b) {
//        if (b) {
//            queryFactory.update(board)
//                    .set(board.likeCount, board.likeCount.add(1))
//                    .where(board.eq(board1))
//                    .execute();
//        } else {
//            queryFactory.update(board)
//                    .set(board.likeCount, board.likeCount.subtract(1))
//                    .where(board.eq(board1))
//                    .execute();
//        }
//    }

}

package backend.wedstagram.service.FeedLikeService;

import backend.wedstagram.dto.FeedDto.FeedLikeRequestDto;

public interface FeedLikeService {

    public void insert(FeedLikeRequestDto feedLikeRequestDto);
    public void delete(FeedLikeRequestDto feedLikeRequestDto);
}

package backend.wedstagram.dto.FeedDto;

import backend.wedstagram.domain.FeedHashTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HashTagDto {
    private List<FeedHashTag> feedHashTagList; //??
}
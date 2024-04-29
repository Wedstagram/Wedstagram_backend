package backend.wedstagram.dto.FollowDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowResponseDto {
    private List<FollowDto> followList;
}

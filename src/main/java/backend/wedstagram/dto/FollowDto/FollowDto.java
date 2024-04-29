package backend.wedstagram.dto.FollowDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowDto {

    private Long id;
    private String userName;
    private String name;
    private String profileImage;

}

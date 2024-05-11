package backend.wedstagram.dto.FollowDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowMemberDto {

    private Long id;
    private String userName;
    private String name;
    private String profileImage;

    private Boolean isFollowing;

    public void updateIsFollowing(Boolean isFollowing) {
        this.isFollowing = isFollowing;
    }

}

package backend.wedstagram.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;

    private String userName;

    private String name;

    //S3 사용 예정
    private String imageUrl;

    @OneToMany(mappedBy = "feed",cascade = CascadeType.ALL)
    private List<FeedLike> likeList=new ArrayList<>();

    public void mappingFeedLike(FeedLike feedLike) {
        this.likeList.add(feedLike);
    }

}

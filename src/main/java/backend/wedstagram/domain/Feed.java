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
public class Feed extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private String imageUrl;

    @OneToMany(mappedBy = "feed",cascade = CascadeType.ALL)
    private List<FeedHashTag> feedHashTagList=new ArrayList<>();

    @OneToMany(mappedBy = "feed",cascade = CascadeType.ALL)
    private List<FeedLike> likeList=new ArrayList<>();
}
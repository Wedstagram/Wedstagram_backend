package backend.wedstagram.domain;
import backend.wedstagram.dto.FeedDto.FeedRequestDto;
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

    // 작성해도되나?!
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    private String content;

    private String imageUrl;

    @OneToMany(mappedBy = "feed",cascade = CascadeType.ALL)
    private List<FeedHashTag> feedHashTagList=new ArrayList<>();

    @OneToMany(mappedBy = "feed",cascade = CascadeType.ALL)
    private List<FeedLike> likeList=new ArrayList<>();

    private int likeCnt;

    public int getLikeCount(){
        return likeList.size();
    }

    public void update(FeedRequestDto feedRequestDto) {
        this.content= feedRequestDto.getContent();
        this.imageUrl= feedRequestDto.getImageUrl();
        //this.feedHashTagList=new ArrayList<>(); //문법이 맞는지?
    }
}
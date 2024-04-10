package backend.wedstagram.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="memberOne_id")
    private Member memberOne;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="memberTwo_id")
    private Member memberTwo;

    private String recentMessage;
}

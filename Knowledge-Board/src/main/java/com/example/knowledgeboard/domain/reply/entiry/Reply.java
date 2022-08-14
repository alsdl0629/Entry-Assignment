package com.example.knowledgeboard.domain.reply.entiry;

import com.example.knowledgeboard.domain.comment.entiry.Comment;
import com.example.knowledgeboard.domain.user.entity.User;
import com.example.knowledgeboard.global.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
public class Reply extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    public void updateReply(String content) {
        this.content = content;
    }

}

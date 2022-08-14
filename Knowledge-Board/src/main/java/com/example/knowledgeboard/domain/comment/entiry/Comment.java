package com.example.knowledgeboard.domain.comment.entiry;

import com.example.knowledgeboard.domain.board.entiry.Board;
import com.example.knowledgeboard.domain.reply.entiry.Reply;
import com.example.knowledgeboard.domain.user.entity.User;
import com.example.knowledgeboard.global.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @OrderBy("id desc")
    @OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE)
    private List<Reply> replies = new ArrayList<>();

    public void updateComment(String content) {
        this.content = content;
    }

}

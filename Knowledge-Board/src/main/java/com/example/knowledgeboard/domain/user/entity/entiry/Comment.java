package com.example.knowledgeboard.domain.user.entity.entiry;

import com.example.knowledgeboard.domain.board.entiry.Board;
import com.example.knowledgeboard.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDate createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    public void updateComment(String content) {
        this.content = content;
        this.createdAt = LocalDate.now();
    }

}

package com.example.knowledgeboard.domain.board.entiry;

import com.example.knowledgeboard.domain.comment.entiry.Comment;
import com.example.knowledgeboard.domain.like.entiry.Like;
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
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Integer views;

    @Column(nullable = false)
    private Integer likeCounts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Like> likes = new ArrayList<>();

    @OrderBy("id desc")
    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    public void updateFeed(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void addViews() {
        this.views += 1;
    }

    public void addLikeCount() {
        this.likeCounts += 1;
    }

    public void minusLikeCount() {
        this.likeCounts -= 1;
    }

}

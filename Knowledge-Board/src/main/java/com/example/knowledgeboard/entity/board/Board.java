package com.example.knowledgeboard.entity.board;

import com.example.knowledgeboard.entity.BaseTimeEntity;
import com.example.knowledgeboard.entity.like.Like;
import com.example.knowledgeboard.entity.user.User;
import lombok.*;

import javax.persistence.*;
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

    private Integer views;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    public void updateFeed(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void addViews() {
        this.views += 1;
    }

}

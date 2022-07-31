package com.example.knowledgeboard.entity.member;

import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String accountId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String password;

}

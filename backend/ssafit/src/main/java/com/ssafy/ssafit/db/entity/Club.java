package com.ssafy.ssafit.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Club extends BaseEntity{
//    private int clubId;

    private String clubName;

    private String clubImg;

    private boolean mon;
    private boolean tues;
    private boolean wedn;
    private boolean thur;
    private boolean fri;
    private boolean sat;
    private boolean sun;

    private int manager;

    /* 이거 설정 어캐해줘야하지?*/
    @CreatedDate
    private LocalDateTime createdAt;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private LocalDateTime fixTime; // mysql에서 time 형으로 써야되는데 어캐하지
    /* 여기까지 고민*/

    @Column(columnDefinition = "integer default 1")
    private int count;

    @Column(columnDefinition = "boolean default false")
    private boolean finish;
}
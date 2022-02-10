package com.ssafy.ssafit.db.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class ClubLog extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Exercise_ID")
    private Exercise exerciseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Club_ID")
    private Club clubId;

    private int exCount;

    // 실수형으로 하는게 맞겠지?
    private double exTime;

//    @Builder
//    public ClubLog(Exercise exerciseId, Club clubId, int exCount, double exTime) {
//        this.exerciseId = exerciseId;
//        this.clubId = clubId;
//        this.exCount = exCount;
//        this.exTime = exTime;
//    }
}

package com.ssafy.ssafit.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

/**
 * ExerciseLog 테이블도 PK 존재하게 만들었음.
 */

@Getter
@Setter
@Entity
public class ExerciseLog extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_ID")
    @JsonIgnore
    private User userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Exercise_ID")
    //@JsonIgnore
    private Exercise exId;

    private int exCount;   // 해당 운동 총 갯수

    private int exDuration; // 해당 운동 총 시간

    private int exStatus; //해당 운동 정확도

    private int exCal; //해당 운동 칼로리 소모량

    private Timestamp exTime; //해당 운동 언제 했는지
}

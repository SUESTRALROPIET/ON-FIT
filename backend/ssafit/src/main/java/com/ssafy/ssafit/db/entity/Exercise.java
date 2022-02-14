package com.ssafy.ssafit.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity(name="exercise")
public class Exercise extends BaseEntity{
    private String kind;

    private int count;  // 시간당 갯수?

    private int calorie;    // 시간당 칼로리
}

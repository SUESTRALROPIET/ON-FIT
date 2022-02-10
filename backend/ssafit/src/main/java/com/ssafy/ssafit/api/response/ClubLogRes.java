package com.ssafy.ssafit.api.response;

import com.ssafy.ssafit.db.entity.Exercise;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ClubLogRes {
    private int exId;
    private int exCount;
    private double exTime;

//    public ClubLogRes(int exId, int exCount, LocalDateTime exTime) {
//        this.exId = exId;
//        this.exCount = exCount;
//        this.exTime = exTime;
//    }
}

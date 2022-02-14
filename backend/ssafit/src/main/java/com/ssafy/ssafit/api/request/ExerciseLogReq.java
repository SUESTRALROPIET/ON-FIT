package com.ssafy.ssafit.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciseLogReq {
    private String userId;
    private int exId; //몇번 운동인지
    private int exStatus; //얼마나 동작을 맞게 했는지
    private int exDuration; //얼마나 오래 운동 했는지
    private int exSet; //현재 선택된 운동을 몇세트 했는지
}

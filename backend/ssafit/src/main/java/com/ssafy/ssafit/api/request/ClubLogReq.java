package com.ssafy.ssafit.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClubLogReq {
    private int exCount;
    private int exerciseId;
    private double exTime;
}

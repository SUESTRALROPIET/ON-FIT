package com.ssafy.ssafit.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClubJoinInfoReq {
    private int clubId;
    private int userId;
}

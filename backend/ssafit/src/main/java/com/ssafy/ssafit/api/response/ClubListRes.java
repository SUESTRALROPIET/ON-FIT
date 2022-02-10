package com.ssafy.ssafit.api.response;

import com.ssafy.ssafit.db.entity.Club;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClubListRes {
    private Club clubInfo;
    private List<Integer> clubMate;
    private List<ClubLogRes> clubLog;
}

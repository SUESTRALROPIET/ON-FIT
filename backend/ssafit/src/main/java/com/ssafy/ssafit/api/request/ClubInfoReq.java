package com.ssafy.ssafit.api.request;

import com.ssafy.ssafit.db.entity.Club;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClubInfoReq {
    private Club clubId;
    private List<ClubLogReq> clubLogs;
}

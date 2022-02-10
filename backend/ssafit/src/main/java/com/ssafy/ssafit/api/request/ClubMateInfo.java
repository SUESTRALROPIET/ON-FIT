package com.ssafy.ssafit.api.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClubMateInfo {
    @ApiModelProperty(name = "방금 생성한 클럽 id")
    Long clubId;
    @ApiModelProperty(name = "유저 PK")
    String userId;

    public ClubMateInfo(Long clubId, String userId) {
        this.clubId = clubId;
        this.userId = userId;
    }
}

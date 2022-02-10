package com.ssafy.ssafit.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class ClubMate extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_ID")
    private User userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Club_ID")
    private Club clubId;

}

package com.ssafy.ssafit.service;

import com.ssafy.ssafit.db.entity.Club;

import java.util.List;

public interface ClubService{
    Club createClub(Club clubInfo);
//    List<Club> userClubList(String user_id);
    List<Club> readyClubList();
//    List<Club> newClubList(String user_id);
//    void createClubMate
}

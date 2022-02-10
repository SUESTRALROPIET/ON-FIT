package com.ssafy.ssafit.api.service;

import com.ssafy.ssafit.api.request.ClubMateInfo;
import com.ssafy.ssafit.api.response.ClubLogRes;
import com.ssafy.ssafit.db.entity.Club;
import com.ssafy.ssafit.db.entity.ClubLog;
import com.ssafy.ssafit.db.entity.ClubMate;
import com.ssafy.ssafit.db.entity.Exercise;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClubService{
    Club createClub(Club clubInfo);
    Club getLatestClub();
    void createCLubMate(ClubMate clubMate);

    List<Club> getClub();
    List<Integer> getUserId(int clubId);
    List<ClubLogRes> getClubLog(int clubId);

    void joinUser(ClubMate clubJoinInfo);

    Club getClubById(int id);

    void plusClubCount(int clubId);

    void createClubLog(ClubLog newClubLog);

    Exercise getExerciseById(int exId);
}

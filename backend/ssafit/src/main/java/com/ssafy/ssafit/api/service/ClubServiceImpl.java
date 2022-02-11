package com.ssafy.ssafit.api.service;

import com.ssafy.ssafit.api.request.ClubMateInfo;
import com.ssafy.ssafit.api.response.ClubLogRes;
import com.ssafy.ssafit.db.entity.Club;
import com.ssafy.ssafit.db.entity.ClubLog;
import com.ssafy.ssafit.db.entity.ClubMate;
import com.ssafy.ssafit.db.entity.Exercise;
import com.ssafy.ssafit.db.repository.ClubLogRepository;
import com.ssafy.ssafit.db.repository.ClubMateRepository;
import com.ssafy.ssafit.db.repository.ClubRepository;
import com.ssafy.ssafit.db.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClubServiceImpl implements ClubService{
    @Autowired
    ClubRepository clubRepository;

    @Autowired
    ClubMateRepository clubMateRepository;

    @Autowired
    ClubLogRepository clubLogRepository;

    @Autowired
    ExerciseRepository exerciseRepository;

    @Override
    public Club createClub(Club clubInfo) {
        return clubRepository.save(clubInfo);
    }

    @Override
    public Club getLatestClub() {
//        Club latestClub =  clubRepository.findFirstByOrderByCreatedAtDESC();
        return clubRepository.findFirstByOrderByCreatedAtDesc();
    }

    @Override
    public void createCLubMate(ClubMate clubMate) {
        clubMateRepository.save(clubMate);
    }

    @Override
    public List<Club> getClub() {
        return clubRepository.findAll();
    }

    @Override
    public List<String> getUserId(int clubId) {
        return clubMateRepository.findUser(clubId);
    }

    @Override
    public List<ClubLogRes> getClubLog(int clubId) {

        List<ClubLog> clubLogs = clubLogRepository.findClubLog(clubId);
        List<ClubLogRes> result = new ArrayList<>();
        for (ClubLog cl : clubLogs){
            ClubLogRes newClubLogRes = new ClubLogRes();
            newClubLogRes.setExId(cl.getExerciseId().getId());
            newClubLogRes.setExCount(cl.getExCount());
            newClubLogRes.setExTime(cl.getExTime());
            result.add(newClubLogRes);
        }
        System.out.println("룰루루라라라");
        return result;
    }

    @Override
    public void joinUser(ClubMate clubJoinInfo) {
        clubMateRepository.save(clubJoinInfo);
    }

    @Override
    public Club getClubById(int id) {
        return clubRepository.findById(id).orElse(null);    // 못찾으면 null 리턴
    }

    @Override
    public void plusClubCount(int clubId) {
        clubRepository.plusClubCount(clubId);
    }

    @Override
    public void createClubLog(ClubLog newClubLog) {
        clubLogRepository.save(newClubLog);
    }

    @Override
    public Exercise getExerciseById(int exId) {
        return exerciseRepository.findById(exId).orElse(null);
    }

}

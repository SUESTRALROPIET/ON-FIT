package com.ssafy.ssafit.service;

import com.ssafy.ssafit.db.entity.Club;
import com.ssafy.ssafit.db.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubServiceImpl implements ClubService{
    @Autowired
    ClubRepository clubRepository;

    @Override
    public Club createClub(Club clubInfo) {
        return clubRepository.save(clubInfo);
    }

//    @Override
//    public List<Club> userClubList(String user_id) {
////        return clubRepository.findAllById(user_id);
//        return null;
//    }

    @Override
    public List<Club> readyClubList() {
        return clubRepository.findAll();
    }

//    @Override
//    public List<Club> newClubList(String user_id) {
//        return null;
//    }
}

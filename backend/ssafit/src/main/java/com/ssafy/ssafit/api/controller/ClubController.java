package com.ssafy.ssafit.api.controller;

import com.ssafy.ssafit.api.request.ClubInfoReq;
import com.ssafy.ssafit.api.request.ClubJoinInfoReq;
import com.ssafy.ssafit.api.request.ClubLogReq;
import com.ssafy.ssafit.api.request.ClubMateInfo;
import com.ssafy.ssafit.api.response.ClubListRes;
import com.ssafy.ssafit.api.response.ClubLogRes;
import com.ssafy.ssafit.api.service.ClubService;
import com.ssafy.ssafit.api.service.UserService;
import com.ssafy.ssafit.db.entity.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(value = "Club API",tags = {"Club"})
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/club")
public class ClubController {

    @Autowired
    ClubService clubService;

    @Autowired
    UserService userService;

    @PostMapping("/{user_id}")
    @ApiOperation(value = "클럽 생성", notes = "<strong>클럽</strong>을 생성한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공")
    })
    public ResponseEntity createClub(@RequestBody ClubInfoReq clubInfo, @PathVariable String user_id) {
        // club table insert
        clubService.createClub(clubInfo.getClubId());   // 여기부터 시작
        /* clubMate table insert */
        // 최근 생성한 클럽 id
        Club club = clubService.getLatestClub();
        User user = userService.findById(user_id);

        ClubMate clubMate = new ClubMate();
        clubMate.setClubId(club);
        clubMate.setUser(user);
        clubService.createCLubMate(clubMate);

        for(ClubLogReq cl : clubInfo.getClubLogs()) {
            Exercise ex = clubService.getExerciseById(cl.getExId());

//            ClubLog newClubLog = ClubLog.builder()
//                    .clubId(club)
//                    .exerciseId(ex)
//                    .exCount(cl.getExCount())
//                    .exTime(cl.getExTime())
//                    .build();
            ClubLog newClubLog = new ClubLog();
            newClubLog.setClubId(club);
            newClubLog.setExerciseId(ex);
            newClubLog.setExCount(cl.getExCount());
            newClubLog.setExTime(cl.getExTime());
            clubService.createClubLog(newClubLog);
        }


        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping()
    @ApiOperation(value = "클럽 리스트",notes = "<strong>클럽 리스트</strong>를 반환한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공")
    })
    public ResponseEntity<Map<String,Object>> clubList(){
        Map<String,Object> result = new HashMap<>();
        List<ClubListRes> clubList = new ArrayList<>();

        List<Club> CL = clubService.getClub();
        for(Club c : CL){
            ClubListRes club = new ClubListRes();
            int count = c.getCount();
            if(count >= 6){
                c.setFinish(true);
            }
            club.setClubInfo(c);

            int clubId = c.getId();
            List<String> userList = clubService.getUserId(clubId);
            club.setClubMate(userList);
            List<ClubLogRes> clubLogList = clubService.getClubLog(clubId);
            club.setClubLog(clubLogList);
            clubList.add(club);
        }

        result.put("ClubList",clubList);

        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/join")
    @ApiOperation(value = "클럽 가입 신청",notes = "<strong>클럽 가입 신청 API</strong>")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공")
    })
    public ResponseEntity joinClub(@RequestBody ClubJoinInfoReq clubJoinInfo){
        System.out.println(clubJoinInfo.getClubId());
        System.out.println(clubJoinInfo.getUserId());
        Club joinClub = clubService.getClubById(clubJoinInfo.getClubId());
        User joinUser = userService.findById(clubJoinInfo.getUserId());

        ClubMate clubMate = new ClubMate();
        clubMate.setClubId(joinClub);
        clubMate.setUser(joinUser);

        clubService.joinUser(clubMate);
        clubService.plusClubCount(clubJoinInfo.getClubId());

        return new ResponseEntity(HttpStatus.OK);
    }

}

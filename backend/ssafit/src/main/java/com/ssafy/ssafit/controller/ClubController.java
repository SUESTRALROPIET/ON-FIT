package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.db.entity.Club;
import com.ssafy.ssafit.service.ClubService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(value = "Club API",tags = {"Club"})
@RestController
@RequestMapping("/club")
public class ClubController {

    @Autowired
    ClubService clubService;

    @PostMapping()
    @ApiOperation(value = "클럽 생성", notes = "<strong>클럽</strong>을 생성한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공")
    })
    public ResponseEntity<Club> createClub(@RequestBody Club clubInfo) {
        return ResponseEntity.ok().body(clubService.createClub(clubInfo));
    }

    @GetMapping("/{user_id}")
    @ApiOperation(value = "클럽 리스트",notes = "<strong>클럽 리스트</strong>를 반환한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공")
    })
    public ResponseEntity<Map<String,Object>> clubList(@PathVariable String user_id){


        Map<String,Object> result = new HashMap<>();

//        result.put("UserClub",clubService.userClubList(user_id));
        result.put("ReadyClub",clubService.readyClubList());
//        result.put("NewClub",clubService.newClubList(user_id));

        return ResponseEntity.ok().body(result);
    }

}

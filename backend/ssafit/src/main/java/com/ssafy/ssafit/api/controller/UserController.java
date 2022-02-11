package com.ssafy.ssafit.api.controller;

import com.ssafy.ssafit.api.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(value = "User API",tags = {"User"})
@RestController
@RequestMapping("/mypage")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{user_id}")
    @ApiOperation(value = "운동기록 조회",notes = "사용자의 운동기록을 반환한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공")
    })
    public ResponseEntity<Map<String,Object>> getExerciseInfo(@PathVariable String user_id){
        Map<String,Object> result = new HashMap<>();
        result.put("MyExerciseLog", userService.getExerciseInfo(user_id));
        return ResponseEntity.ok().body(result);
    }

}

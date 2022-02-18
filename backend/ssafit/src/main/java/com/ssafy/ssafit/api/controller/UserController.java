package com.ssafy.ssafit.api.controller;

import com.ssafy.ssafit.api.request.GoogleLoginReq;
import com.ssafy.ssafit.api.service.UserService;
import com.ssafy.ssafit.common.model.response.BaseResponseBody;
import com.ssafy.ssafit.db.entity.Trainer;
import com.ssafy.ssafit.db.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(value = "User API",tags = {"User"})
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/mypage")
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

    @PostMapping()
    @ApiOperation(value = "구글 소셜로그인",notes = "DB에 회원정보가 없으면 추가한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공")
    })
    public ResponseEntity<BaseResponseBody> login(@RequestBody GoogleLoginReq googleLoginReq){

        User user = new User();
        user.setUserId(googleLoginReq.getUserId());
        user.setFullName(googleLoginReq.getUserName());
        String userEmail = googleLoginReq.getUserId()+"@gmail.com";
        user.setEmail(userEmail);

        Trainer trainer = new Trainer();
        trainer.setId(1);
        user.setTrainerId(trainer);

        userService.login(googleLoginReq);
        return ResponseEntity.ok().body(BaseResponseBody.of(200,"로그인 성공"));
    }
}

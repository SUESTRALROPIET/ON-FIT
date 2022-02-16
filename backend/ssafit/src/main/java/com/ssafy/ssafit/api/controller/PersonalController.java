package com.ssafy.ssafit.api.controller;

import com.ssafy.ssafit.api.request.ExerciseLogReq;
import com.ssafy.ssafit.api.service.PersonalService;
import com.ssafy.ssafit.api.service.UserService;
import com.ssafy.ssafit.common.model.response.BaseResponseBody;
import com.ssafy.ssafit.db.entity.Exercise;
import com.ssafy.ssafit.db.entity.ExerciseLog;
import com.ssafy.ssafit.db.entity.Trainer;
import com.ssafy.ssafit.db.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Api(value = "Personal API",tags = {"Personal"})
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/personal")
public class PersonalController {

    @Autowired
    PersonalService personalService;

    @Autowired
    UserService userService;

    @PostMapping()
    @ApiOperation(value = "개인운동 완료", notes = "<strong>개인운동 기록을</strong>을 저장한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공")
    })
    public ResponseEntity<ExerciseLog> saveExercise(@RequestBody ExerciseLogReq exerciseLogReq) {
        return ResponseEntity.ok().body(personalService.saveExercise(exerciseLogReq));
    }

    @GetMapping("/{userId}")
    @ApiOperation(value = "트레이너 정보 가져오기", notes = "<strong>트레이너 정보를</strong> 가져온다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공")
    })
    public ResponseEntity<Trainer> getTrainer(@PathVariable String userId) {
        return ResponseEntity.ok().body(personalService.getTrainer(userId));
    }

    @PatchMapping("/{userId}")
    @ApiOperation(value = "트레이너 변경하기", notes = "<strong>트레이너 정보를</strong> 변경한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공")
    })
    public ResponseEntity<? extends BaseResponseBody> updateTrainer(@PathVariable String userId, @RequestBody int trainerId) {
        System.out.println(trainerId);
        User user = new User();
        user.setUserId(userId);

        Trainer trainer = new Trainer();
        trainer.setId(trainerId);

        user.setTrainerId(trainer);
        userService.updateTrainer(user);
        return ResponseEntity.ok().body(BaseResponseBody.of(200,"트레이너 수정 성공"));
    }
}

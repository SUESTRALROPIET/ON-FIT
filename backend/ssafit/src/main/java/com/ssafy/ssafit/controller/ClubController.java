package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.db.entity.Club;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "Club API",tags = {"Club"})
@RestController
@RequestMapping("/club")
public class ClubController {
    @PostMapping()
    @ApiOperation(value = "클럽 생성", notes = "<strong>클럽</strong>을 생성한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Club> updateUserInfo(
            @RequestBody Club clubInfo) {
//        User user = new User();
//        user.setUserId(userId);
//        user.setDepartment(userInfo.getDepartment());
//        user.setPosition(userInfo.getPosition());
//        user.setName(userInfo.getName());
//
//        userService.updateUser(user);

//        return ResponseEntity.ok().body()
                return null;

//        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "수정 성공"));
    }
}

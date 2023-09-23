package com.example.demo.web.controller.v1;

import com.example.demo.domain.usecase.record.RecordService;
import com.example.demo.domain.usecase.user.UserService;
import com.example.demo.repository.entity.RecordEntity;
import com.example.demo.repository.entity.UserEntity;
import com.example.demo.support.ApiResponse;
import com.example.demo.support.ApiResponseGenerator;
import com.example.demo.web.dto.request.RecordRequestSave;
import com.example.demo.web.dto.request.RecordRequestUpdate;
import com.example.demo.web.dto.request.UserRequest;
import com.example.demo.web.dto.response.RecordResponse;
import com.example.demo.web.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CurdController {

    private final UserService userService;

    private final RecordService recordService;

    @PostMapping("/users")
    public ApiResponse<ApiResponse.SuccessBody<UserResponse>> users(@RequestBody UserRequest userRequest) {
        UserEntity user = userService.join(userRequest);
        UserResponse response = new UserResponse(user.getId());


        return ApiResponseGenerator.success(response, HttpStatus.OK);

    }

    @PostMapping("/record")
    public ApiResponse<ApiResponse.SuccessBodyWithNoData> record(@RequestBody RecordRequestSave recordRequestSave) {

        RecordEntity recordEntity = recordService.saveRecord(recordRequestSave);

        return ApiResponseGenerator.successWithNoData(HttpStatus.OK);

    }

    @GetMapping("/record")
    public ApiResponse<ApiResponse.SuccessBody<RecordResponse>> showRecord(@RequestParam Long id) {
        RecordEntity findRecord = recordService.getRecordById(id);
        RecordResponse recordResponse = new RecordResponse(findRecord.getRid(), findRecord.getTitle(), findRecord.getContent());

        return ApiResponseGenerator.success(recordResponse, HttpStatus.OK);

    }

    @PutMapping("/record")
    public ApiResponse<ApiResponse.SuccessBody<RecordResponse>> updateRecord(@RequestBody RecordRequestUpdate recordRequestUpdate) {

        RecordEntity updateRecord = recordService.update(recordRequestUpdate);

        RecordResponse recordResponse = new RecordResponse(updateRecord.getRid(), updateRecord.getTitle(), updateRecord.getContent());

        return ApiResponseGenerator.success(recordResponse, HttpStatus.OK);


    }

    @DeleteMapping("/record")
    public ApiResponse<ApiResponse.SuccessBody<Void>> deleteRecord(@RequestParam Long id) {
        recordService.delete(id);
        return ApiResponseGenerator.success( HttpStatus.OK);

    }



}

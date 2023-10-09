package com.example.demo.web.controller.v1;

import com.example.demo.domain.usecase.record.RecordService;
import com.example.demo.support.ApiResponse;
import com.example.demo.support.ApiResponseGenerator;
import com.example.demo.web.dto.request.RecordRequestSave;
import com.example.demo.web.dto.request.RecordRequestUpdate;
import com.example.demo.web.dto.response.RecordResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;


    @PostMapping("/record")
    public ApiResponse<ApiResponse.SuccessBodyWithNoData> record(@RequestBody RecordRequestSave recordRequestSave) {

        recordService.saveRecord(recordRequestSave);

        return ApiResponseGenerator.successWithNoData(HttpStatus.OK);

    }

    @GetMapping("/record")
    public ApiResponse<ApiResponse.SuccessBody<RecordResponse>> showRecord(@RequestParam Long id) {
        RecordResponse findRecord = recordService.getRecordResponseByRid(id);
        return ApiResponseGenerator.success(findRecord, HttpStatus.OK);

    }

    @PutMapping("/record")
    public ApiResponse<ApiResponse.SuccessBody<RecordResponse>> updateRecord(@RequestBody RecordRequestUpdate recordRequestUpdate) {

        RecordResponse updateRecord = recordService.update(recordRequestUpdate);



        return ApiResponseGenerator.success(updateRecord, HttpStatus.OK);


    }

    @DeleteMapping("/record")
    public ApiResponse<ApiResponse.SuccessBody<Void>> deleteRecord(@RequestParam Long id) {
        recordService.delete(id);
        return ApiResponseGenerator.success( HttpStatus.OK);

    }
}

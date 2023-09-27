package com.example.demo.domain.usecase.record;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.entity.RecordEntity;
import com.example.demo.repository.repository.RecordRepository;
import com.example.demo.web.dto.request.RecordRequestSave;
import com.example.demo.web.dto.request.RecordRequestUpdate;
import com.example.demo.web.dto.response.RecordResponse;
import com.example.demo.web.support.converter.RecordConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;
    private final RecordConverter recordConverter;

    public RecordResponse saveRecord(RecordRequestSave recordRequest) {
        RecordEntity recordEntity = new RecordEntity();
        recordEntity.setTitle(recordRequest.getTitle());
        recordEntity.setContent(recordRequest.getContent());
        recordRepository.save(recordEntity);

        return recordConverter.recordEntityToResponse(recordEntity);
    }

    public RecordEntity getRecordEntityByRid(Long rid) {
        Optional<RecordEntity> findRecordEntity = recordRepository.findById(rid);
        if (findRecordEntity.isPresent()) {
            return findRecordEntity.get();
        } else {
            throw new ResourceNotFoundException("없는 id 입니다.");
        }
    }

    public RecordResponse getRecordResponseByRid(Long id) {
        return recordConverter.recordEntityToResponse(getRecordEntityByRid(id));

    }




    public RecordResponse update( RecordRequestUpdate recordRequest) {

        RecordEntity recordEntity = getRecordEntityByRid(recordRequest.getRid());


        recordEntity.setTitle(recordRequest.getTitle());
        recordEntity.setContent(recordRequest.getContent());
        recordRepository.save(recordEntity);

        return recordConverter.recordEntityToResponse(recordEntity);
    }

    public void delete(Long id) {
        RecordEntity findRecord = getRecordEntityByRid(id);
        recordRepository.delete(findRecord);

    }




}

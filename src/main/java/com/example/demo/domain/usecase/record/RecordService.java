package com.example.demo.domain.usecase.record;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.entity.RecordEntity;
import com.example.demo.repository.repository.RecordRepository;
import com.example.demo.web.dto.request.RecordRequestSave;
import com.example.demo.web.dto.request.RecordRequestUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;

    public RecordEntity saveRecord(RecordRequestSave recordRequest) {
        RecordEntity recordEntity = new RecordEntity();
        recordEntity.setTitle(recordRequest.getTitle());
        recordEntity.setContent(recordRequest.getContent());
        recordRepository.save(recordEntity);
        return recordEntity;
    }

    public RecordEntity getRecordById(Long id) {
        Optional<RecordEntity> findRecordEntity = recordRepository.findById(id);
        if (findRecordEntity.isPresent()) {
            return findRecordEntity.get();
        } else {
            throw new ResourceNotFoundException("없는 id 입니다.");
        }
    }

    public RecordEntity update( RecordRequestUpdate recordRequest) {

        RecordEntity recordEntity = getRecordById(recordRequest.getId());

        recordEntity.setTitle(recordRequest.getTitle());
        recordEntity.setContent(recordRequest.getContent());

        return recordEntity;
    }

    public void delete(Long id) {
        RecordEntity findRecord = getRecordById(id);
        recordRepository.delete(findRecord);

    }




}

package com.example.demo.domain.usecase.record;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.entity.RecordEntity;
import com.example.demo.repository.repository.RecordRepository;
import com.example.demo.web.dto.request.RecordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;

    public RecordEntity saveRecord(RecordRequest recordRequest) {
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

    public RecordEntity update(Long id, RecordRequest recordRequest) {

        RecordEntity recordEntity = null;
        Optional<RecordEntity> findRecordEntity = recordRepository.findById(id);
        if (findRecordEntity.isPresent()) {
            recordEntity= findRecordEntity.get();
        } else {
            throw new ResourceNotFoundException("없는 id 입니다.");
        }
        recordEntity.setTitle(recordRequest.getTitle());
        recordEntity.setContent(recordRequest.getContent());

        return recordEntity;
    }




}

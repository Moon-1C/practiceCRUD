package com.example.demo.web.support.converter;

import com.example.demo.repository.entity.RecordEntity;
import com.example.demo.web.dto.response.RecordResponse;
import org.springframework.stereotype.Component;

@Component
public class RecordConverter {

    public RecordEntity recordResponseToEntity(RecordResponse recordResponse) {

        return new RecordEntity(recordResponse.getRid(), recordResponse.getTitle(), recordResponse.getContent());

    }

    public RecordResponse recordEntityToResponse(RecordEntity recordEntity) {

        return new RecordResponse(recordEntity.getRid(), recordEntity.getTitle(), recordEntity.getContent());

    }


}

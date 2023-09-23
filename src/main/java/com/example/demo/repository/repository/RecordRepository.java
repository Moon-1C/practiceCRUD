package com.example.demo.repository.repository;

import com.example.demo.repository.entity.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<RecordEntity,Long> {
}

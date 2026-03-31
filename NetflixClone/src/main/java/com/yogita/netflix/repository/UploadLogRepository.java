package com.yogita.netflix.repository;

import com.yogita.netflix.model.UploadLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UploadLogRepository extends JpaRepository<UploadLog, Integer> {

    // Change parameter type to Integer
    List<UploadLog> findByAdminId(Integer adminId);
}
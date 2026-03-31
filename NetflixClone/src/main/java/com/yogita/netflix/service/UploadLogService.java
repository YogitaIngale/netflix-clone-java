package com.yogita.netflix.service;

import com.yogita.netflix.model.UploadLog;
import com.yogita.netflix.repository.UploadLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UploadLogService {

    @Autowired
    private UploadLogRepository uploadLogRepository;

    // Log a movie uploaded by admin
    public UploadLog logUpload(Integer adminId, Integer movieId) {
        UploadLog log = new UploadLog(adminId, movieId);
        return uploadLogRepository.save(log);
    }

    // Get all uploads by a specific admin
    public List<UploadLog> getUploadsByAdmin(Integer adminId) {
        return uploadLogRepository.findByAdminId(adminId);
    }

    // Get all uploads
    public List<UploadLog> getAllUploads() {
        return uploadLogRepository.findAll();
    }
}
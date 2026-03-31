package com.yogita.netflix.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "upload_log")
public class UploadLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "admin_id", nullable = false)
    private Integer adminId;

    @Column(name = "movie_id", nullable = false)
    private Integer movieId;

    @Column(name = "upload_date")
    private LocalDateTime uploadDate = LocalDateTime.now();

    public UploadLog() {}

    public UploadLog(Integer adminId, Integer movieId) {
        this.adminId = adminId;
        this.movieId = movieId;
        this.uploadDate = LocalDateTime.now();
    }

    // getters & setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getAdminId() { return adminId; }
    public void setAdminId(Integer adminId) { this.adminId = adminId; }

    public Integer getMovieId() { return movieId; }
    public void setMovieId(Integer movieId) { this.movieId = movieId; }

    public LocalDateTime getUploadDate() { return uploadDate; }
    public void setUploadDate(LocalDateTime uploadDate) { this.uploadDate = uploadDate; }
}
package dev.ohhoonim.demo_mybatis.para;

import java.time.LocalDateTime;
import java.util.UUID;

public class Project {
    private UUID projectId;
    private String title;
    private String content;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status;

    
    public Project(UUID projectId, String title, String content, LocalDateTime startDate, LocalDateTime endDate,
            String status) {
        this.projectId = projectId;
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }
    public static Project of(UUID projectId, String title, String content, 
            LocalDateTime startDate, LocalDateTime endDate, String status) {
        return new Project(
            projectId,
            title,
            content,
            startDate,
            endDate,
            status
        );
    }

    public UUID getProjectId() {
        return projectId;
    }

    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}

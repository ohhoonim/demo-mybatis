package dev.ohhoonim.demo_mybatis.para;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Project {
    private UUID projectId;
    private String title;
    private String content;
    private Period period;
    private String status;

    private List<Note> notes;

    public Project() {
        // default 생성자 넣어주기
    }

    public Project(UUID projectId, String title, String content, Period period,
            String status) {
        this.projectId = projectId;
        this.title = title;
        this.content = content;
        this.period = period;
        this.status = status;
    }
    public static Project of(UUID projectId, String title, String content, 
            LocalDateTime startDate, LocalDateTime endDate, String status) {
        return new Project(
            projectId,
            title,
            content,
            new Period(startDate, endDate),
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public List<Note> getNotes() {
        return notes;
    }
    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
    public Period getPeriod() {
        return period;
    }
    public void setPeriod(Period period) {
        this.period = period;
    }
}

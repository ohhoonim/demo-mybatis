package dev.ohhoonim.demo_mybatis.para;

import java.util.UUID;

public class Note {
    private UUID id;
    private String title;
    private String content;
    
    public Note(UUID id, String title, String cotnent) {
        this.id = id;
        this.title = title;
        this.content = cotnent;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
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
    public void setContent(String cotnent) {
        this.content = cotnent;
    }


}

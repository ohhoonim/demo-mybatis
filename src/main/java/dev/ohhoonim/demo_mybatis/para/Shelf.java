package dev.ohhoonim.demo_mybatis.para;

import java.util.UUID;

public class Shelf {
    private UUID shelfId;
    private String shape;
    private String title;
    private String content;

    public UUID getShelfId() {
        return shelfId;
    }
    public void setShelfId(UUID shelfId) {
        this.shelfId = shelfId;
    }
    public String getShape() {
        return shape;
    }
    public void setShape(String shape) {
        this.shape = shape;
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
    
}

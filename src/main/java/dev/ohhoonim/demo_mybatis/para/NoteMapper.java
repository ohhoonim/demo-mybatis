package dev.ohhoonim.demo_mybatis.para;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface NoteMapper {
    List<Note> findByTitle(@Param("title") String title);
    
    void insert(@Param("note") Note note);
}

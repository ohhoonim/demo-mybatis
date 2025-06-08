package dev.ohhoonim.demo_mybatis.para.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import dev.ohhoonim.demo_mybatis.para.Note;

@Mapper
public interface NoteMapper {
    List<Note> findByTitle(@Param("title") String title);
    
    void insert(@Param("note") Note note);

    Optional<Note> findById(@Param("id") String id);
}

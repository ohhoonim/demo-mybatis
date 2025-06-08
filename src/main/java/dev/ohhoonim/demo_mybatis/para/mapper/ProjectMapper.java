package dev.ohhoonim.demo_mybatis.para.mapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import dev.ohhoonim.demo_mybatis.para.Note;
import dev.ohhoonim.demo_mybatis.para.Project;

@Mapper
public interface ProjectMapper {
    List<Project> findByTitle(@Param("title") String title);
    
    void insert(@Param("project") Project project);

    void addNote(@Param("note") Note note, @Param("project") Project project);

    List<Note> notes(@Param("project") Project project);

    Optional<Project> findById(@Param("projectId") String projectId);

    Project projectContainsNote(@Param("project") Project project);
}

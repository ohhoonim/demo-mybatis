package dev.ohhoonim.demo_mybatis.para.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import dev.ohhoonim.demo_mybatis.para.Project;

@Mapper
public interface ProjectMapper {
    List<Project> findByTitle(@Param("title") String title);
    
    void insert(@Param("project") Project note);
}

package dev.ohhoonim.demo_mybatis.para.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import dev.ohhoonim.demo_mybatis.para.Shelf;

@Mapper
public interface ShelfMapper {

    // for if test
    List<Shelf> findShelfByCondition(@Param("title") String title);

    // for choose(when, otherwise) test
    List<Shelf> findShelfContents(@Param("shape") String shape,
            @Param("content") String content);

    // for trim(where, set)
    List<Shelf> findShelfByShape(@Param("shape") String shape,
            @Param("content") String content);

    void update(@Param("shelf") Shelf shelf);

    void insert(@Param("shelf") Shelf shelf);
    // for foreach test
    List<Shelf> searchShapeNote(@Param("shapes") List<String> shapes); 

    // for script test
    @Update("""
        {<script>
        
        </script>}
            """)
    void updateShelf(@Param("shelf") Shelf shelf);


    // fo bind test (생략)


}

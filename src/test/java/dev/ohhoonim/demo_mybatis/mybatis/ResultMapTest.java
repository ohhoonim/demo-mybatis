package dev.ohhoonim.demo_mybatis.mybatis;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import dev.ohhoonim.demo_mybatis.para.Note;
import dev.ohhoonim.demo_mybatis.para.Project;
import dev.ohhoonim.demo_mybatis.para.mapper.NoteMapper;
import dev.ohhoonim.demo_mybatis.para.mapper.ProjectMapper;

@Testcontainers
@RunWith(SpringRunner.class)
@MybatisTest
public class ResultMapTest {
    
    @Container
    @ServiceConnection
    private static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            DockerImageName.parse("postgres:17.2-alpine"));

    @Autowired
    ProjectMapper projectMapper;

    @Autowired 
    NoteMapper noteMapper;


    private UUID noteOne = UUID.randomUUID();
    private UUID noteTwo = UUID.randomUUID();
    private UUID projectId = UUID.randomUUID();

    private Note todayNote;
    private Note tomorrowNote;

    private Project project;

    private LocalDateTime now;

    @BeforeEach
    public void setup() {
        now = LocalDateTime.now();
        project = Project.of(
            projectId,
            "mybatis config 구성",
            "작성중",
            now,
            now.plusDays(7),
            null 
        );
        projectMapper.insert(project);

        todayNote = new Note(noteOne, "today", "todo");
        tomorrowNote = new Note(noteTwo, "tomorrow", "todo");

        noteMapper.insert(todayNote);
        noteMapper.insert(tomorrowNote);
    }

    @Test
    public void noteMapperTest() {
        assertThat(todayNote.getTitle()).isEqualTo("today");
        assertThat(tomorrowNote.getTitle()).isEqualTo("tomorrow");

    }

    private Project setProjectNotes() {
        var targetproject = projectMapper.findById(projectId.toString()).get();
        var targetNoteOne = noteMapper.findById(noteOne.toString()).get();
        var targetNoteTwo = noteMapper.findById(noteTwo.toString()).get();

        projectMapper.addNote(targetNoteOne, targetproject);
        projectMapper.addNote(targetNoteTwo, targetproject);

        return targetproject;
    }

    @Test
    public void addNoteInProjectTest() {
        var targetProject = setProjectNotes();

        var notes = projectMapper.notes(targetProject);
        assertThat(notes).hasSize(2);
    } 

    @Test
    public void collectionAssociationTest() {
        var targetProject = setProjectNotes();
        var project = projectMapper.projectContainsNote(targetProject);

        assertThat(project.getNotes()).hasSize(2);
        assertThat(project.getPeriod().getStartDate()).isEqualTo(now);
    }


}

package dev.ohhoonim.demo_mybatis.mybatis;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.UUID;

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
import dev.ohhoonim.demo_mybatis.para.NoteMapper;

@Testcontainers
@RunWith(SpringRunner.class)
@MybatisTest
public class NoteMapperTest {

    @Container
    @ServiceConnection
    private static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            DockerImageName.parse("postgres:17.2-alpine"));

    @Autowired
    NoteMapper noteMapper;

    @Test
    public void mybatisTest() {
        var newNote = new Note(UUID.randomUUID(), "today", "todo");
        noteMapper.insert(newNote);
        List<Note> notes = noteMapper.findByTitle("today");

        assertThat(notes.get(0).getTitle()).isEqualTo("today");
    }
}

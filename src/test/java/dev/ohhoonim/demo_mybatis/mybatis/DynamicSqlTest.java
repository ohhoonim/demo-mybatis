package dev.ohhoonim.demo_mybatis.mybatis;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
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

import dev.ohhoonim.demo_mybatis.para.Shelf;
import dev.ohhoonim.demo_mybatis.para.mapper.ShelfMapper;

@Testcontainers
@RunWith(SpringRunner.class)
@MybatisTest
public class DynamicSqlTest {

    @Container
    @ServiceConnection
    private static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            DockerImageName.parse("postgres:17.2-alpine"));

    @Autowired
    ShelfMapper shelfMapper;

    private UUID shelfIdOne;
    private UUID shelfIdTwo;

    @BeforeEach
    public void setup() {
        shelfIdOne = UUID.randomUUID();
        Shelf shelf = new Shelf();
        shelf.setShelfId(shelfIdOne);
        shelf.setTitle("spring starter");
        shelf.setShape("area");
        shelf.setContent("spring quickstart");

        shelfMapper.insert(shelf);

        shelfIdTwo = UUID.randomUUID();
        Shelf shelfTwo = new Shelf();
        shelfTwo.setShelfId(shelfIdTwo);
        shelfTwo.setTitle("mybatix starter");
        shelfTwo.setShape("resource");
        shelfTwo.setContent("mybatis quickstart");

        shelfMapper.insert(shelfTwo);
    }

    @Test
    public void ifTest() {
        var shelves = shelfMapper.findShelfByCondition(null);
        assertThat(shelves).hasSize(2);

        var shelvesContainsTitle = shelfMapper.findShelfByCondition("choo");
        assertThat(shelvesContainsTitle).hasSize(0);

        var shelvesContainsBlankTitle = shelfMapper.findShelfByCondition("");
        assertThat(shelvesContainsBlankTitle).hasSize(2);
    }

    @Test
    public void chooseTest() {
        var shelves = shelfMapper.findShelfContents(null, "quickstart");
        assertThat(shelves).hasSize(2);

        var shelvesShape = shelfMapper.findShelfContents("resource", "quickstart");
        assertThat(shelvesShape).hasSize(1);
    }

    @Test
    public void trimTest() {
        var shelves = shelfMapper.findShelfByShape("area", "quickstart");
        assertThat(shelves).hasSize(1);
    }

    @Test
    public void foreachTest() {
        var shelves = shelfMapper.searchShapeNote(List.of("area", "resource"));
        assertThat(shelves).hasSize(2);
    }

    @Test
    public void scriptTest() {
        // 생략
        // script
        /**
        @Update({"<script>",
        "update Author",
        "  <set>",
        "    <if test='username != null'>username=#{username},</if>",
        "    <if test='password != null'>password=#{password},</if>",
        "    <if test='email != null'>email=#{email},</if>",
        "    <if test='bio != null'>bio=#{bio}</if>",
        "  </set>",
        "where id=#{id}",
        "</script>"})
        void updateAuthorValues(Author author);
        */
    }
}

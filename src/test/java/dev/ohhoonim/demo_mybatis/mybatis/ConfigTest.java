package dev.ohhoonim.demo_mybatis.mybatis;

import static org.assertj.core.api.Assertions.assertThat;

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

import dev.ohhoonim.demo_mybatis.para.Project;
import dev.ohhoonim.demo_mybatis.para.mapper.ProjectMapper;

@Testcontainers
@RunWith(SpringRunner.class)
@MybatisTest
public class ConfigTest {
    
    @Container
    @ServiceConnection
    private static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            DockerImageName.parse("postgres:17.2-alpine"));

    @Autowired
    ProjectMapper projectMapper;

    @Test
    public void applicationYmlAndDefaultConfig() {
        // # application.yml
        // mybatis:
        // mapper-locations: mapper/**/*.xml
        // type-aliases-package: dev.ohhoonim.demo_mybatis.para
        // type-handlers-package: dev.ohhoonim.demo_mybatis.typehandler
        // configuration:
        //     jdbc-type-for-null: "NULL"
        //     default-fetch-size: 1000
        //     map-underscore-to-camel-case: true
    }

    private Project project;

    @BeforeEach
    public void setup() {
        project = Project.of(
            UUID.randomUUID(),
            "mybatis config 구성",
            "작성중",
            LocalDateTime.now(),
            LocalDateTime.now().plusDays(7),
            null 
        );
        projectMapper.insert(project);
    }

    @Test
    public void underscoreToCamelcaseTest() {
        var result = projectMapper.findByTitle("config");
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isInstanceOf(Project.class);
        assertThat(result.get(0).getContent()).isEqualTo("작성중");
    } 

    @Test
    public void jdbcTypeForNullTest() {
        // null 에대한 jdbcType을 지정한다 
        // 일부 드라이버는 지정을 해주어야한다. 
        //     jdbc-type-for-null: "NULL"
        // default는 'OTHER' 이다 'VARCHAR' 도 있다. 
        var result = projectMapper.findByTitle("config");
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getStatus())
            .isNull();
        // 뭔가 null처리와 관련된 옴션인것 같지만 쿼리 결과하고는 상관없는 옵션이다. 
        // 신경안써도 될 듯 
    }

    @Test
    public void typeAliasTest() {
        // type-aliases-package:
        // 를 지정했다면 mapper.xml에서 resultType은 
        // '소문자'로 타입명을 적어주시면 됩니다.
        // 예) Project , project 둘 다 됨
        // mybatis에서 java type은 소문자로
        //         jdbcType은 대문자로 표기하고 있음
    }

    @Test
    public void typeHandlerUuidTest() {
        // type-handlers-package: 
        // 커스텀 타입핸들러들의 위치 지정
        // uuid와 같이 커스텀이 필요한 경우 

    }
}

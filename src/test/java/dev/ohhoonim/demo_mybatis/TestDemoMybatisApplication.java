package dev.ohhoonim.demo_mybatis;

import org.springframework.boot.SpringApplication;

public class TestDemoMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.from(DemoMybatisApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}

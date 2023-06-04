package com.delta.poc;

import org.apache.ibatis.type.MappedTypes;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.delta.poc.vo.Empl;
@SpringBootApplication
@MapperScan("com.delta.poc.dao")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

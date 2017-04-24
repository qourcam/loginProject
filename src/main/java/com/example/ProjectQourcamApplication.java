package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

// alttaki kod springe entitylerin nerede tutulduğunu gösterir. bu olmazsa tablolar otomatik olarak oluşturulmaz

@SpringBootApplication
public class ProjectQourcamApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectQourcamApplication.class, args);
	}
}

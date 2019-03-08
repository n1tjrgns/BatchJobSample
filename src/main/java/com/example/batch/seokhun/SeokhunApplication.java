package com.example.batch.seokhun;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableBatchProcessing //배치기능 활성화 어노테이션 - 스프링 배치 기능을 사용하기 위한 필수 선언
@SpringBootApplication
public class SeokhunApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeokhunApplication.class, args);
    }

}

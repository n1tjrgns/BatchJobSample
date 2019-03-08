package com.example.batch.seokhun.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j //log 사용을 위한 lombok 어노테이션
@RequiredArgsConstructor //생성자 DI를 위한 lombok 어노테이션
@Configuration //Spring Batch의 모든 Job은 configuration으로 등록해서 사용.
public class SimpleJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory; //생성자 DI 받음
    private final StepBuilderFactory stepBuilderFactory; //생성자 DI 받음

    @Bean
    public Job simpleJob(){

        //simpleJob 이란 이름의 Batch Job 생성
        //Builder를 통해 job의 이름을 지정
        //job = 하나의 배치 작업 단위
        return jobBuilderFactory.get("simpleJob")
                .start(simpleStep1())
                .build();
    }

    @Bean
    public Step simpleStep1() {

        //simpleStep1이라는 Batch Step 생성
        return stepBuilderFactory.get("simpleStep1")
                /*/.tasklet : Step 안에서 단일로 수행될 커스텀한 기능을 선언할 때 사용
                step 안에서 수행될 기능을 명시
                여기서는 Batch 수행시 step1 이라는 log.info가 수행됨됨
                */
               .tasklet(new Tasklet() {
                   //tasklet 은 execute 메소드를 구현, 비즈니스 로직을 실행하는 코드를 작성, 리턴값으로 RepeatStatus.Finished를 사용
                   @Override
                   public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                       log.info(">>>>This is Step1");
                       return RepeatStatus.FINISHED;
                   }
               })
               .build();
    }
}

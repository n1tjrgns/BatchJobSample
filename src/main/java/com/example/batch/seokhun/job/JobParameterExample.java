package com.example.batch.seokhun.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
@RequiredArgsConstructor
@Configuration
public class JobParameterExample {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job simpleParameter(){
        log.info(">>>>> definition simpleparameter");
        return jobBuilderFactory.get("simpleParameter")
                .start(simpleStep1())
                .next(simpleStep2())
                .build();
    }

    private final SimpleJobTasklet tasklet1;

    //@Bean
    //@JobScope
    public Step simpleStep1() {
        log.info(">>>>>> simpleStep11111111");
        return stepBuilderFactory.get("simpleStep1")
                .tasklet(tasklet1)
                .build();
    }

    public Step simpleStep2() {
        return stepBuilderFactory.get("simpleStep2")
            .tasklet(scopeStep2Tasklet(null))
            .build();
    }

    @Bean
    @StepScope
    public Tasklet scopeStep2Tasklet(@Value("#{jobParameters[requestDate]}") String requestDate) {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                log.info(">>>>>> this is step2");
                log.info(">>>>> requestDate = {}", requestDate);
                return RepeatStatus.FINISHED;
            }
        };
    }

}

package com.example.demo.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SampleBatch {
	@Bean
	public Job sampleJob(JobRepository jobRepository, Step sampleStep) {
	    return new JobBuilder("sampleJob", jobRepository)
	            .start(sampleStep)
	            .build();
	}
	
	@Bean
	public Step sampleStep(JobRepository jobRepository, PlatformTransactionManager transactionManager, SampleTasklet sampleTasklet) {
		return new StepBuilder("step1", jobRepository)
				.tasklet(sampleTasklet, transactionManager)
				.build();
	}
	
}
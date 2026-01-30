package com.example.demo.batch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import com.example.demo.util.ExcelTest;

@Component
public class SampleTasklet implements Tasklet{
	
	private final ExcelTest excelTest;
	
	public SampleTasklet(ExcelTest excelTest) {
        this.excelTest = excelTest;
    }
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		excelTest.exportOrderDetail();
		return RepeatStatus.FINISHED;
	}
}
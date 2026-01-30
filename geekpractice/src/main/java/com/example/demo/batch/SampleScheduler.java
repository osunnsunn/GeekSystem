package com.example.demo.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SampleScheduler {
	
	@Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job sampleJob;
	
	@Scheduled(cron = "0 24 17 * * ?")
    public void run() throws Exception {
		System.out.println("===== バッチ起動 =====");
        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(sampleJob, params);
    }

}

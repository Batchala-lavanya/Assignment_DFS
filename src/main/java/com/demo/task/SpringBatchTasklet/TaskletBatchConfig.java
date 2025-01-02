package com.demo.task.SpringBatchTasklet;



import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;

import com.demo.task.Dao.taskRepository;

import jakarta.persistence.EntityManagerFactory;

@Configuration

public class TaskletBatchConfig {

  private final JobRepository jobRepository;
  private final PlatformTransactionManager transactionManager;
  private final taskRepository taskRepo;
  private final EntityManagerFactory entityManagerFactory;
  
	
	public TaskletBatchConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager,
			taskRepository taskRepo,EntityManagerFactory entityManagerFactory) {
		super();
		this.jobRepository = jobRepository;
		this.transactionManager = transactionManager;
		this.taskRepo = taskRepo;
		this.entityManagerFactory = entityManagerFactory;
	}

	
    @Autowired
    private UserTasklet userTasklet;
    
   

    @Bean
    @Primary
    public Step step2() {
        return new StepBuilder("step2",jobRepository)
                .tasklet(userTasklet)
                .transactionManager(transactionManager) 
                .build();
    }

    @Bean
    @Primary
    public Job job(JobRepository jobRepository,Step step2) {
        return new JobBuilder("userJob",jobRepository)
                .start(step2())
                .build();
    }
}

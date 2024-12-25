//package com.demo.task.Batch.application;
//
//import java.util.Collections;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.item.data.RepositoryItemReader;
//import org.springframework.batch.item.file.FlatFileItemWriter;
//import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
//import org.springframework.batch.item.support.PassThroughItemProcessor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.data.domain.Sort;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import com.demo.task.Dao.taskRepository;
//import com.demo.task.EntityVO.User;
//
//@Configuration
//
//public class BatchConfiguration {
//	
//	private final JobRepository jobRepository;
//    private final PlatformTransactionManager transactionManager;
//    private final taskRepository taskRepo;
//    
//	
//	public BatchConfiguration(JobRepository jobRepository, PlatformTransactionManager transactionManager,
//			taskRepository taskRepo) {
//		super();
//		this.jobRepository = jobRepository;
//		this.transactionManager = transactionManager;
//		this.taskRepo = taskRepo;
//	}
//	
//	
//	
//	
//	@Bean
//	//@Primary
//    public RepositoryItemReader<User> reader() {
//        RepositoryItemReader<User> reader = new RepositoryItemReader<>();
//        reader.setRepository(taskRepo);
//        reader.setMethodName("findAll"); // Calls UserRepository's findAll()
//        reader.setPageSize(10);
//        reader.setSort(Collections.singletonMap("userId", Sort.Direction.ASC));
//        return reader;
//    }
// 
//	
//	@Bean
//	//@Primary
//    public PassThroughItemProcessor<User> processor() {
//        return new PassThroughItemProcessor<>();
//    }
//	
//	@Bean
//	//@Primary
//	public FlatFileItemWriter<User> writer(){
//		return new FlatFileItemWriterBuilder<User>()
//                .name("csvWriter")
//                .resource(new FileSystemResource("users-output.csv"))
//                .delimited()
//                .delimiter(",")
//                .names("userId", "name", "email","password")
//                .headerCallback(writer -> writer.write("userId, name, email, password")) // Add CSV header
//                .build();
//	}
//	
//	
//	@Bean
//	//@Primary
//    public Step step() {
//        return new StepBuilder("step", jobRepository)
//                .<User, User>chunk(10, transactionManager)
//                .reader(reader())
//                .processor(processor())
//                .writer(writer())
//                .build();
//    }
//	 
////	 
//	
//	@Bean
//	//@Primary
//    public Job myJob(JobRepository jobRepository, Step step) {
//      return new JobBuilder("myJob",jobRepository)
//                .start(step)   
//                .build();
//    }
//	
//	
//
//}

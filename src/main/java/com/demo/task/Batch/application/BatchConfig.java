//package com.demo.task.Batch.application;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.database.JpaItemWriter;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
//import org.springframework.batch.item.file.mapping.DefaultLineMapper;
//import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
//import org.springframework.batch.item.support.PassThroughItemProcessor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import com.demo.task.Dao.taskRepository;
//import com.demo.task.EntityVO.User;
//
//import jakarta.persistence.EntityManagerFactory;
//
//@Configuration
//public class BatchConfig {
//	
//	private final JobRepository jobRepository;
//    private final PlatformTransactionManager transactionManager;
//    private final taskRepository taskRepo;
//    private final EntityManagerFactory entityManagerFactory;
//    
//	
//	public BatchConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager,
//			taskRepository taskRepo, EntityManagerFactory entityManagerFactory) {
//		super();
//		this.jobRepository = jobRepository;
//		this.transactionManager = transactionManager;
//		this.taskRepo = taskRepo;
//		this.entityManagerFactory = entityManagerFactory;
//	}
//	
//	//second csv to h2
//		@Bean(name="reader1")
//		
//		public FlatFileItemReader<User> reader1() {
//		    FlatFileItemReader<User> reader = new FlatFileItemReader<>();
//		    reader.setResource(new FileSystemResource("CsvToH2.csv"));// Path to the CSV file
//		    reader.setLinesToSkip(1);
//		    reader.setLineMapper(new DefaultLineMapper<User>() {{
//		        setLineTokenizer(new DelimitedLineTokenizer() {{
//		            setNames("name", "email", "password"); // Assuming CSV has these columns
//		        }});
//		        setFieldSetMapper(new BeanWrapperFieldSetMapper<User>() {{
//		            setTargetType(User.class);
//		        }});
//		    }});
//		    return reader;
//		}
//		
//		@Bean
//		
//	    public PassThroughItemProcessor<User> processor1() {
//	        return new PassThroughItemProcessor<>();
//	    }
//		
//		@Bean(name="writer1")
//		
//		public JpaItemWriter<User> writer1() {
//	        JpaItemWriter<User> writer = new JpaItemWriter<>();
//	        writer.setEntityManagerFactory(entityManagerFactory); // Set EntityManagerFactory for database operations
//	        return writer;
//		}
////		@Bean
////		public JdbcBatchItemWriter<User> writer1(DataSource dataSource) {
////		    JpaItemWriter<User> writer = new JpaBatchItemWriter<>();
////		    writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
////		    writer.setSql("INSERT INTO users (name, email, password) VALUES (:name, :email, :password)");
////		    writer.setDataSource((DataSource) dataSource);
////		    return writer;
////		}
//
//		
//		@Bean(name="step1")
//		
//		public Step step1(ItemReader<User> reader1,
//		                  ItemWriter<User> writer1,
//		                  ItemProcessor<User, User> processor1,
//		                  PlatformTransactionManager txManager) {
//		    return new StepBuilder("step1", jobRepository)
//		            .<User, User>chunk(5, txManager)  // Processing in chunks
//		            .reader(reader1)
//		            .processor(processor1)
//		            .writer(writer1)
//		            .build();
//		}
////		 
//		
//		@Bean(name="CsvToH2")
////		
//	    public Job CsvToH2(JobRepository jobRepository, Step step1) {
//	        return new JobBuilder("CsvToH2", jobRepository)
//	                .start(step1)
//	                .build();
//	    }
//}
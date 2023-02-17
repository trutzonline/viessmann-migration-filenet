package trutz.io.migstatus;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
class MigStatusBatchConfiguration {

	@Bean
	@Autowired
	Job migStatusJob(JobRepository jobRepository, Step step) {
		return new JobBuilder("migstatusJob", jobRepository) //
				.incrementer(new RunIdIncrementer()) //
				.flow(step) //
				.end() //
				.build();
	}

	@Bean
	@Autowired
	Step migStatusStep(JobRepository jobRepository, PlatformTransactionManager transactionManager,
			ItemReader<MigStatus> migrationReader, //
			ItemProcessor<MigStatus, FileNetDocument> migrationProcessor, //
			ItemWriter<FileNetDocument> migrationWriter) {
		StepBuilder stepBuilder = new StepBuilder("migstatusStep", jobRepository);
		return stepBuilder.<MigStatus, FileNetDocument>chunk(10, transactionManager) //
				.reader(migrationReader) //
				.processor(migrationProcessor)
				.writer(migrationWriter) //
				.build();
	}

	@Bean
	@Autowired
	public ItemReader<MigStatus> migrationReader(DataSource dataSource) {
		JdbcCursorItemReaderBuilder<MigStatus> builder = new JdbcCursorItemReaderBuilder<>();
		builder.dataSource(dataSource) //
				.name("migrationReader")
				.sql("SELECT p8id from migstatus WHERE status is null") //
				.rowMapper(new BeanPropertyRowMapper<>(MigStatus.class));
		return builder.build();
	}

}

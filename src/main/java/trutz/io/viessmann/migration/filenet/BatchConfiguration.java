package trutz.io.viessmann.migration.filenet;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	PlatformTransactionManager transactionManager;

	@Bean
	@Autowired
	Job job(JobRepository jobRepository, Step step) {
		return new JobBuilder("job", jobRepository) //
				.incrementer(new RunIdIncrementer()) //
				.flow(step) //
				.end() //
				.build();
	}

	@Bean
	@Autowired
	Step step(JobRepository jobRepository, ItemReader<FileNetDocument> reader, ItemWriter<FileNetDocument> writer) {
		StepBuilder stepBuilder = new StepBuilder("step", jobRepository);
		return stepBuilder.<FileNetDocument, FileNetDocument>chunk(10, transactionManager) //
				.reader(reader) //
				.writer(writer) //
				.build();
	}

}

package trutz.io.viessmann.migration.filenet;

import java.util.List;

import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ViessmannMigrationFilenetApplication implements ApplicationRunner {

	@Autowired
	JobRepository jobRepository;

	@Autowired
	MigStatusRepository migStatusRepository;

	public static void main(String[] args) {
		SpringApplication.run(ViessmannMigrationFilenetApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		/* 
		try {
			JobParameters jobParameters = new JobParameters();
			jobRepository.createJobExecution("job", jobParameters);
		} catch (JobRestartException jobRestartException) {
			jobRestartException.printStackTrace();
		}
		*/
		List<MigStatus> migStatuses = migStatusRepository.findAll();
		for(MigStatus migStatus : migStatuses) {
			System.out.println(migStatus.p8id);
		}

	}

}

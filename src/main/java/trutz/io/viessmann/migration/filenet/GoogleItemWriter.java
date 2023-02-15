package trutz.io.viessmann.migration.filenet;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class GoogleItemWriter implements ItemWriter<FileNetDocument> {

	@Override
	public void write(Chunk<? extends FileNetDocument> chunk) throws Exception {
		for (FileNetDocument item : chunk.getItems()) {
			System.out.print(item);
		}

	}
}

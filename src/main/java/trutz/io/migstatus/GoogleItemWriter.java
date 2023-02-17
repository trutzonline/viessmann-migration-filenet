package trutz.io.migstatus;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class GoogleItemWriter implements ItemWriter<FileNetDocument> {

	@Override
	public void write(@NonNull Chunk<? extends FileNetDocument> chunk) throws Exception {
		for (FileNetDocument item : chunk.getItems()) {
			System.out.println(item);
		}

	}
}

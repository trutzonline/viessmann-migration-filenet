package trutz.io.viessmann.migration.filenet;

import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

@Component
public class FileNetItemReader implements ItemReader<FileNetDocument> {

    int counter = 0;

    @Override
    public FileNetDocument read() throws Exception {
        if (counter < 100) {
            counter++;
            return new FileNetDocument();
        }
        return null;
    }

}

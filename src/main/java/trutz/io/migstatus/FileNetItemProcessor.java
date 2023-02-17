package trutz.io.migstatus;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class FileNetItemProcessor implements ItemProcessor<MigStatus, FileNetDocument> {

    public FileNetDocument process(@NonNull MigStatus item) {
        return new FileNetDocument();
    }

}

package trutz.io.viessmann.migration.filenet;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "migstatus")
public class MigStatus {

    @Id
    public String p8id;

    public String status;

    public Timestamp migtime;

    public String exception;

    
}

package trutz.io.viessmann.migration.filenet;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MigStatusRepository extends JpaRepository<MigStatus, String> {
    
}

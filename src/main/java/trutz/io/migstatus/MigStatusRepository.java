package trutz.io.migstatus;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MigStatusRepository extends JpaRepository<MigStatus, String> {
    
}

package in.ajayit.repo;
//Purpose - used to insert the record into table  
import org.springframework.data.jpa.repository.JpaRepository;

import in.ajayit.entity.StudentEnqEntity;

public interface StudentEnqRepo extends JpaRepository<StudentEnqEntity, Integer> {

}

package in.ajayit.repo;
//Purpose of repository - used to communicate with our database table.

import org.springframework.data.jpa.repository.JpaRepository;

import in.ajayit.entity.CourseEntity;

public interface CourseRepo extends JpaRepository<CourseEntity, Integer> {

}

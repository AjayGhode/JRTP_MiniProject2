package in.ajayit.entity;
//entity class are used to mapped our java class with database table

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="AIT_COURSES")
@Data
public class CourseEntity {

	@Id
	@GeneratedValue
	private Integer courseId;
	private String courseName;
	
}

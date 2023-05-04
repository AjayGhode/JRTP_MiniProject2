package in.ajayit.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="AIT_STUDENT_ENQURIES")
@Data
public class StudentEnqEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer enqId;
	private String studentName;
	private Long studentPhno;
	private String classMode;
	private String courseName;
	private String enqStatus;
	
	@CreationTimestamp //auto-populated
	private LocalDate dateCreated;
	
	@UpdateTimestamp  //auto-populated
	private LocalDate lastUpdated;
	
	@ManyToOne
	@JoinColumn(name="user_id")  //foreign key. which enquire is added by which user
	private UserDtlsEntity user;
	
}

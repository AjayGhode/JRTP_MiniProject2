package in.ajayit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ajayit.entity.UserDtlsEntity;

public interface UserDtlsRepo extends JpaRepository<UserDtlsEntity, Integer>{

	public UserDtlsEntity findByEmail(String email); // to avoid registration with same email address 
	public UserDtlsEntity findByEmailAndPwd(String email, String pwd); //to fetch the record from the database table


}

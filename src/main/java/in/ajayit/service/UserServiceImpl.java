package in.ajayit.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ajayit.binding.LoginForm;
import in.ajayit.binding.SignUpForm;
import in.ajayit.binding.UnlockForm;
import in.ajayit.entity.UserDtlsEntity;
import in.ajayit.repo.UserDtlsRepo;
import in.ajayit.util.EmailUtils;
import in.ajayit.util.PwdUtils;
import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDtlsRepo userDtlsRepo;

	@Autowired
	private EmailUtils emailUtils;
	
	//in order to create session, inject HttpSession, it is predefined obj 
	@Autowired
	private HttpSession session;
	
	@Override
	public String login(LoginForm form) {
		
		UserDtlsEntity entity = 
				userDtlsRepo.findByEmailAndPwd(form.getEmail(), form.getPwd());
		if(entity == null) {
			return "Invalid Credential";
		}
		
		if(entity.getAccStatus().equals("LOCKED")) {
			return "Your Account Locked";
		}
		
		//create session and store user data in session 
		session.setAttribute("userId", entity.getUserId());
		                      //key     value
		return "Success";
	}

	
	
	
	@Override
	public boolean unlockAccount(UnlockForm form) {  // if new & confirmed pwd same, come to this method
		
		UserDtlsEntity entity = userDtlsRepo.findByEmail(form.getEmail());
		
		if(entity.getPwd().equals(form.getTempPwd())) { //to verify email tempwd match with entered pwd same
			
			entity.setPwd(form.getNewPwd());
			entity.setAccStatus("UNLOCKED");
			userDtlsRepo.save(entity);  //record will be updated 
			return true;
			
		}else {
			
			return false;
			
		}

	}

	@Override
	public boolean signup(SignUpForm form) {

		UserDtlsEntity user = userDtlsRepo.findByEmail(form.getEmail());

		if (user != null) {
			return false;
		}

		// TODO: Copy data from binding obj to entity obj
		UserDtlsEntity entity = new UserDtlsEntity();
		BeanUtils.copyProperties(form, entity); // data coming in the form obj to entity obj

		// TODO: generate random pwd and set to obj
		String tempPwd = PwdUtils.generateRandomPwd();
		entity.setPwd(tempPwd); // setting the pwd

		// TODO: Set account status as LOCKED.
		entity.setAccStatus("LOCKED");

		// TODO: Insert record
		userDtlsRepo.save(entity);

		// TODO: Send email to user to unlock the account
		String to = form.getEmail();
		String subject = "Unlock Your Account | ASHOK IT";
		StringBuffer body = new StringBuffer(); // String is Immutable, now made mutable

		body.append("<h1> Use blow temporary password to unlock your account <h1>");
		body.append("Temporary pwd :" + tempPwd);
		body.append("<br/>"); // after the temp pwd go to next line
		body.append("<br/>");
		body.append(
				"<a href =\"http://localhost:8080/unlock?email=" + to + "\"> Click Here To Unlock Your Account</a>");

		emailUtils.sendMail(to, subject, body.toString());
		return true;
	}
	
	
	
	
	@Override
	public boolean forgotPwd(String email) {
	
		//check record presence in DB with given email
		UserDtlsEntity entity = userDtlsRepo.findByEmail(email);
		
		//Record not available, return false
		if(entity==null) {
			return false;  // no email is valid with given id.
		}
		
		//If record available, send pwd to email and return true.
		String Subject = "Recover Password";
		String body = "Your Pwd:: "+ entity.getPwd();
		
		emailUtils.sendMail(email, Subject, body);
		
		return true;
	}

}

package in.ajayit.service;
// UI -> controller(binding class) ->service(abstract method)

import in.ajayit.binding.LoginForm;
import in.ajayit.binding.SignUpForm;
import in.ajayit.binding.UnlockForm;



public interface UserService {

	public boolean signup(SignUpForm form);  //abstract methods(body not available.) Here, send success/fail msg to UI
	public boolean unlockAccount(UnlockForm form);
	public String login(LoginForm form);
	public boolean forgotPwd(String email);
	


}

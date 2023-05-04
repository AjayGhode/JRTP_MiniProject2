package in.ajayit.controllers;
//used for login controller

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ajayit.binding.LoginForm;
import in.ajayit.binding.SignUpForm;
import in.ajayit.binding.UnlockForm;
import in.ajayit.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;// call the serviceImpl method.

	@GetMapping("/signup") // load the Sign page
	public String signUpPage(Model model) { // this method is responsible to signup page
		model.addAttribute("user", new SignUpForm());
		return "signup";
	}

	@PostMapping("/signup")
	public String handleSingUp(@ModelAttribute("user") SignUpForm form, Model model) { // to submit the form
		// *spell mistake
		boolean status = userService.signup(form);

		if (status) {

			model.addAttribute("succMsg", "Account Created, Check Your Email");

		} else {
			model.addAttribute("errMsg", "Choose Unique Email");

		}
		return "signup"; // same return type of signUpPage method
	}

	@GetMapping("/unlock") // load the login page // when user click on link(email) this method will called
	public String unlockPage(@RequestParam String email, Model model) { // @RequestParam - to capture the data from
																		// query parameter

		UnlockForm unlockFormObj = new UnlockForm(); // created obj for binding class.
		unlockFormObj.setEmail(email); // whatever the email we are getting in the query parameter that email setting
										// to binding class obj

		model.addAttribute("unlock", unlockFormObj); // sending binding obj to UI.

		return "unlock"; // page will be loaded.
	}

	// Post request method
	@PostMapping("/unlock") // binded to post request
	public String unlockUserAccount(@ModelAttribute("unlock") UnlockForm unlock, Model model) { // when user submit the
												// btn request will come to this method which is binded to post request.
		//  @ModelAttribute - it is used to bind the data.
	
		// System.out.println(unlock);

		if (unlock.getNewPwd().equals(unlock.getConfirmPwd())) {
			boolean status = userService.unlockAccount(unlock);

			if (status) {
				model.addAttribute("succMsg", "Your account unlocked successfully");
			} else {
				model.addAttribute("errMsg", "Given Temporary Pwd is incorrect, check your email");
			}

		} else {
			model.addAttribute("errMsg", "New Pwd and Confirm pwd should be same");
		}

		return "unlock";
	}

	@GetMapping("/login") // load the login page
	public String loginPage(Model model) {
		
		model.addAttribute("loginForm", new LoginForm());
					    	//key          //value
		return "login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {
		//System.out.println(loginForm);
			
		String status = userService.login(loginForm);
		
		if(status.contains("Success")) {
			//redirect req  to dashboard method
			return "redirect:/dashboard";
		}
		
		model.addAttribute("errMsg", status);
		
		return "login";
	}

	
	
	@GetMapping("/forgot") // load the login page
	public String forgotPwdPage() {
		return "forgotPwd";
	}
	
	@PostMapping("/forgotPwd") //to recovered the pwd
	public String forgotPwd(@RequestParam("email") String email, Model model) {
							//@RequestParam("email") - capturing the emailID from form
		System.out.println(email);
		
		boolean status = userService.forgotPwd(email);
		
		if(status) {
			//send success msg
			model.addAttribute("succMsg","Pwd sent to your email");
			
		}else {
			//send error msg
		model.addAttribute("errMsg","Invalid Email");
		
		}
		return "forgotPwd";
	}

}

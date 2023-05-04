package in.ajayit.controllers;
//used to display index page
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping("/")
	public String indexpage() {
		return "index";
	}
}

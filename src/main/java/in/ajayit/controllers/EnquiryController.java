package in.ajayit.controllers;
//Purpose of controller - used to handle the request & response 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.ajayit.binding.DashboardResponse;
import in.ajayit.binding.EnquiryForm;
import in.ajayit.service.EnquiryService;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {
	
	@Autowired
	private EnquiryService enqService;
	
	@Autowired
	private HttpSession session;
	
	
	
	@GetMapping("/logout")
	public String logout() { //when user logout from the application
	
		session.invalidate();  // to end the session of same user & go to home page.
		return "index";
	}

	@GetMapping("/dashboard")
	public String dashboardPage(Model model) {
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		
		DashboardResponse dashboardData = enqService.getDashboarData(userId);
		
		model.addAttribute("dashboardData", dashboardData); // sending dashboardData obj to UI.
		
		return "dashboard";
	}
	
	//after submitting data in the add enquiry form, data should update to DB table.
	@PostMapping("/addEnq")
	public String addEnquiry(@ModelAttribute("formObj") EnquiryForm formObj, Model model) {
		
		//System.out.println(formObj);
		
		// save the data
		boolean status = enqService.saveEnquriry(formObj);
		if(status) {
			
			model.addAttribute("succMsg", "Enquiry Added");
			
		}else {
			
			model.addAttribute("errMsg","Problem Occured");
			
		}
		return "add-enquiry";
	}
	
	@GetMapping("/enquiry")
	public String addEnquiryPage(Model model) {
		
		//get courses drop down
		List<String> courses = enqService.getCourses();
		
		//get enq status for drop down
		List<String> enqStatuses = enqService.getEnqStatuses();
		
		//create binding class obj
		
		EnquiryForm formObj = new EnquiryForm();
		
		//set data in model obj
		
		model.addAttribute("courseNames", courses);
		model.addAttribute("statusNames", enqStatuses);
		model.addAttribute("formObj", formObj);
		
		return "add-enquiry";
	}
	
	@GetMapping("/enquires")
	public String viewEnquiriesPage() {
		return "view-enquiries";
	}
}

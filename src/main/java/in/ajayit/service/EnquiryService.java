package in.ajayit.service;
//Service class is used to write our business logic.


import java.util.List;

import in.ajayit.binding.DashboardResponse;
import in.ajayit.binding.EnquiryForm;
import in.ajayit.binding.EnquirySearchCriteria;

public interface EnquiryService {

	public DashboardResponse getDashboarData(Integer userId); //// to get the dashboard data which is required to display in the dashboard
	public List<String> getCourses();
	public List<String> getEnqStatuses();
	public boolean saveEnquriry(EnquiryForm form);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public List<String> getCourseNames();
//	public List<String> getEnqStatus();  // dropdown after login, in Add Enquiry and view Enquiry
//	public DashboardResponse getDashboarData(Integer userId);  // to get the dashboard data which is required to display in the dashboard
//	public String upsertEnquiry(EnquiryForm form);
//	public List<EnquiryForm> getEnquries(Integer userId,
//										EnquirySearchCriteria criteria);
//	public EnquiryForm getEnquiry(Integer enqId);
//	
}

package in.ajayit.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ajayit.binding.DashboardResponse;
import in.ajayit.binding.EnquiryForm;
import in.ajayit.entity.CourseEntity;
import in.ajayit.entity.EnqStatusEntity;
import in.ajayit.entity.StudentEnqEntity;
import in.ajayit.entity.UserDtlsEntity;
import in.ajayit.repo.CourseRepo;
import in.ajayit.repo.EnqStatusRepo;
import in.ajayit.repo.StudentEnqRepo;
import in.ajayit.repo.UserDtlsRepo;
import jakarta.servlet.http.HttpSession;

@Service    //spring bean	
public class EnquiryServiceImpl implements EnquiryService {

	@Autowired
	private UserDtlsRepo userDtlsRepo;
	
	@Autowired
	private StudentEnqRepo enqRepo;
	
	@Autowired
	private CourseRepo coursesRepo;
	
	@Autowired
	private EnqStatusRepo statusRepo;
	
	@Autowired
	private HttpSession session;
	
	@Override
	public DashboardResponse getDashboarData(Integer userId) {
		
		DashboardResponse response =new DashboardResponse();
		
		Optional<UserDtlsEntity> findById = 
				userDtlsRepo.findById(userId); // How to get the data based on primary key. Based on UserId and get User Record.
		if(findById.isPresent()) { //if data is present then get the data
			UserDtlsEntity userEntity = findById.get();
			
			List<StudentEnqEntity> enquiries = userEntity.getEnquiries();  // Based on user record and get the all the user enquiries
			
			Integer totalCnt = enquiries.size();
			
			Integer enrolledCnt  =  enquiries.stream()  //Java 8 feature. filter the data(enrolled, lost..). Here, enrolled. To get data
					 .filter(e -> e.getEnqStatus().equals("Enrolled"))
					 .collect(Collectors.toList()).size();
			
			Integer lostCnt = enquiries.stream()  //Here, Lost. To get data.
					 .filter(e -> e.getEnqStatus().equals("Lost"))
					 .collect(Collectors.toList()).size();
		
			response.setTotalEnquriesCnt(totalCnt);
			response.setEnrolledCnt(enrolledCnt);
			response.setLostCnt(lostCnt);
		}
		
		return response;
	}
	
	@Override
	public List<String> getCourses() {   //this method will give course Name dropdown data
		List<CourseEntity> findAll = coursesRepo.findAll();
		
		List<String> names = new ArrayList<>();
		
		for(CourseEntity entity : findAll) { //for each used, we can use map also.
			
			names.add(entity.getCourseName());
		
		}
		return names;
	}
	
	@Override
	public List<String> getEnqStatuses() {  // this method will give Enquiry status dropdown data
		List<EnqStatusEntity> findAll = statusRepo.findAll();
		
		List<String> statusList= new  ArrayList<>();
		
		for(EnqStatusEntity entity : findAll) {
			
			statusList.add(entity.getStatusName());
			
		}
		return statusList;
	}
	
	 @Override
	public boolean saveEnquriry(EnquiryForm form) {
		
		 StudentEnqEntity enqEntity = new StudentEnqEntity();
		 BeanUtils.copyProperties(form, enqEntity);
		 
		 Integer userId = (Integer) session.getAttribute("userId");
		 
		UserDtlsEntity userEntity = userDtlsRepo.findById(userId).get();
		enqEntity.setUser(userEntity);
		 
		 
		enqRepo.save(enqEntity);
		return true;
	}
}

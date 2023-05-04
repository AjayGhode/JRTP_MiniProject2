package in.ajayit.binding;
//purpose of binding class - take the data from UI and send the data to the UI.
//and responsible to capture the data from UI & send the data to UI.
//used to capture the data from the request & send the data to the response.
import lombok.Data;

@Data
public class DashboardResponse {

	private Integer totalEnquriesCnt;
	private Integer enrolledCnt;
	private Integer lostCnt;
	
}

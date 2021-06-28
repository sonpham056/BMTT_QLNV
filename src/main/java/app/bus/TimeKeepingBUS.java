package app.bus;

import java.util.List;

import app.dao.TimeKeepingDAO;
import app.dto.TimeKeeping;

public class TimeKeepingBUS {
	public static List<TimeKeeping> getById(int id) {
		return TimeKeepingDAO.getById(id);
	}
	
	public static int addStartTime(TimeKeeping time)  {
		return TimeKeepingDAO.addStartTime(time);
	}
	
	public static void updateEndTime(TimeKeeping time)  {
		TimeKeepingDAO.updateEndTime(time);
	}
	
	public static TimeKeeping getLatestTimeKeeping(String email)  {
		return TimeKeepingDAO.getLatestTimeKeeping(email);
	}
}

package app.bus;

import java.util.Date;
import java.util.List;

import app.dao.AuditHistoryDAO;
import app.dto.AuditHistory;

public class AuditHistoryBUS {
	public static int add(AuditHistory audit) {
		return AuditHistoryDAO.add(audit);
	}
	
	public static List<AuditHistory> getAuditHistory(String userType) {
		return AuditHistoryDAO.getAuditHistory(userType);
	}
	
	public static List<AuditHistory> getAuditHistoryWithDates(Date from, Date to, String userType) {
		return AuditHistoryDAO.getAuditHistoryWithDates(from, to, userType);
	}
}

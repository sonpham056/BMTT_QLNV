package app.bus;

import app.dao.AuditDAO;
import app.dto.Audit;

public class AuditBUS {
	public static Audit getById(int id) {
		return AuditDAO.getById(id);
	}
}

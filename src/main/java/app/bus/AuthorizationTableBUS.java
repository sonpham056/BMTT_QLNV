package app.bus;

import app.dao.AuthorizationTableDAO;
import app.dto.AuthorizationTable;

public class AuthorizationTableBUS {
	public static int add(AuthorizationTable author) {
		return AuthorizationTableDAO.add(author);
	}
}	

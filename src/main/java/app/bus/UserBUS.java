package app.bus;

import app.dao.UserDAO;
import app.dto.User;

public class UserBUS {
	public static User getById(int id) {
		return UserDAO.getById(id);
	}
	
	public static User getByName(String name) throws Exception {
		if (name.length() >= 50) {
			throw new Exception("Name too long!");
		}
		return UserDAO.getByName(name);
	}
	
	public static User getByEmail(String email) throws Exception {
		if (email.length() >= 50) {
			throw new Exception("email too long!");
		}
		return UserDAO.getByEmail(email);
	}
	
	public static User getLoginUser(String email, String password) throws Exception {
		if (email.length() >= 50) {
			throw new Exception("email too long!");
		}
		return UserDAO.getLoginUser(email, password);
	}
	
	public static int add(User user) {
		return UserDAO.add(user);
	}
}

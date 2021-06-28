package app.bus;

import java.util.List;

import app.dao.UserDAO;
import app.dto.Role;
import app.dto.User;

public class UserBUS {
	public static User getById(int id)  {
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
	
	public static int add(User user)  {
		//người dùng được tạo sẽ là user
		user.setRole(new Role(2));
		user.setFollowedByAdmin(true);
		return UserDAO.add(user);
	}
	
	public static void update(User user)  {
		UserDAO.update(user);
	}
	
	public static void delete(User user)  {
		UserDAO.delete(user);
	}
	
	public static List<User> getAll()  {
		return UserDAO.getAll();
	}
}

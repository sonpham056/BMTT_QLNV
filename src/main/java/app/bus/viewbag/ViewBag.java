package app.bus.viewbag;

import javax.swing.JTable;

import app.bus.UserBUS;
import app.dto.User;

public class ViewBag {
	public static JTable tableFromPnFollowUser;
	public static User currentUser;
	public static Boolean isAudit;
	public static User getUser() {
		return currentUser = UserBUS.getById(currentUser.getUserId());
	}
}

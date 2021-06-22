package app.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class AuthorizationTable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int authorizationId;
	private boolean report;
	private boolean userInfo;
	@OneToOne(mappedBy = "authorizationTable")
	private User user;
	public int getAuthorizationId() {
		return authorizationId;
	}
	public void setAuthorizationId(int authorizationId) {
		this.authorizationId = authorizationId;
	}
	public boolean isReport() {
		return report;
	}
	public void setReport(boolean report) {
		this.report = report;
	}
	public boolean isUserInfo() {
		return userInfo;
	}
	public void setUserInfo(boolean userInfo) {
		this.userInfo = userInfo;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}

package app.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class User {
	public User() {
		
	}
	
	public User(String email, String pass, String name, String lastName, Date dateOfBirth, AuthorizationTable autorizationTable) {
		this.email = email;
		this.password = pass;
		this.name = name;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.authorizationTable = autorizationTable;
	}
	
	public User(String email, String name, String lastName, Date dateOfBirth, AuthorizationTable autorizationTable) {
		this.email = email;
		this.name = name;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.authorizationTable = autorizationTable;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String email;
	private String password;
	private String name;
	private String lastName;
	private Date dateOfBirth;
	private boolean isFollowedByAdmin;
	
	@OneToOne
	@JoinColumn(name = "authorizationId")
	private AuthorizationTable authorizationTable;
	
	@ManyToOne
	@JoinColumn(name = "roleId")
	private Role role;
	
	@OneToMany(mappedBy = "user")
	private List<AuditHistory> auditHistories;
	
	@OneToMany(mappedBy = "sender")
	private List<Report> sentReports;
	
	@OneToMany(mappedBy = "receiver")
	private List<Report> receivedReports;
	
	@OneToMany(mappedBy = "user")
	private List<TimeKeeping> timeKeepings;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public AuthorizationTable getAuthorizationTable() {
		return authorizationTable;
	}
	public void setAuthorizationTable(AuthorizationTable authorizationTable) {
		this.authorizationTable = authorizationTable;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public List<AuditHistory> getAuditHistories() {
		return auditHistories;
	}
	public void setAuditHistories(List<AuditHistory> auditHistories) {
		this.auditHistories = auditHistories;
	}
	public List<Report> getSentReports() {
		return sentReports;
	}
	public void setSentReports(List<Report> sentReports) {
		this.sentReports = sentReports;
	}
	public List<Report> getReceivedReports() {
		return receivedReports;
	}
	public void setReceivedReports(List<Report> receivedReports) {
		this.receivedReports = receivedReports;
	}
	public List<TimeKeeping> getTimeKeepings() {
		return timeKeepings;
	}
	public void setTimeKeepings(List<TimeKeeping> timeKeepings) {
		this.timeKeepings = timeKeepings;
	}

	public boolean isFollowedByAdmin() {
		return isFollowedByAdmin;
	}

	public void setFollowedByAdmin(boolean isFollowedByAdmin) {
		this.isFollowedByAdmin = isFollowedByAdmin;
	}
	
}

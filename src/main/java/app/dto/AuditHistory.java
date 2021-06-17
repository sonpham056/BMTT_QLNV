package app.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AuditHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int auditHistoryId;
	private Date auditTime;
	
	@ManyToOne
	@JoinColumn(name = "auditId")
	private Audit audit;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	public int getAuditHistoryId() {
		return auditHistoryId;
	}
	public void setAuditHistoryId(int auditHistoryId) {
		this.auditHistoryId = auditHistoryId;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public Audit getAudit() {
		return audit;
	}
	public void setAudit(Audit audit) {
		this.audit = audit;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}	
}

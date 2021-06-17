package app.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Audit {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int auditId;
	private String type;
	@OneToMany(mappedBy = "audit")
	private List<AuditHistory> auditHistories;
	public int getAuditId() {
		return auditId;
	}
	public void setAuditId(int auditId) {
		this.auditId = auditId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<AuditHistory> getAuditHistories() {
		return auditHistories;
	}
	public void setAuditHistories(List<AuditHistory> auditHistories) {
		this.auditHistories = auditHistories;
	}
}

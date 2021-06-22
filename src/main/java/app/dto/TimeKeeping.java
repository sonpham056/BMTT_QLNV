package app.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TimeKeeping {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int timeKeepingId;
	private Date startTime;
	private Date endTime;
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	public int getTimeKeepingId() {
		return timeKeepingId;
	}
	public void setTimeKeepingId(int timeKeepingId) {
		this.timeKeepingId = timeKeepingId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}

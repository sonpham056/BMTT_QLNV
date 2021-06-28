package app.bus.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import app.bus.AuditBUS;
import app.bus.AuditHistoryBUS;
import app.dto.Audit;
import app.dto.AuditHistory;
import app.dto.User;

public class SystemServices {
	private static ResourceBundle bundle = ResourceBundle.getBundle("audit");
	
	public static void turnOnOrOffAuditing(boolean onOrOff) {
		FileInputStream file = null;
		FileOutputStream outputFile = null;
		try {
			Properties prop = new Properties();
			file = new FileInputStream("src/main/resources/audit.properties");
			outputFile = new FileOutputStream("src/main/resources/audit.properties");
			prop.load(file);
			if (onOrOff) {
				prop.setProperty("audit_on_off", "on");
			} else {
				prop.setProperty("audit_on_off", "off");
			}
			
			prop.store(outputFile, null);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				file.close();
				outputFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void reloadBundle() {
	    ResourceBundle.clearCache(Thread.currentThread().getContextClassLoader());
	    ResourceBundle.clearCache();
	    bundle = ResourceBundle.getBundle("audit");
	}
	
	public static boolean checkSystemAudit() {
		if (bundle.getString("audit_on_off").equals("on")) {
			return true;
		}
		return false;
	}
	
	/**
	* add audit history with the specifed type and user <br>
	* audit type<br>
	* 1: login<br>
	* 2: logout<br>
	* 3: insert<br>
	* 4: update<br>
	* 5: delete<br>
	* 6: select<br>
	* 7: start working<br>
	* 8: end working<br>
	* 9: send report<br>
	* 10: read report<br>
	* 11: change password
	* @param user an user to add audit history
	* @param type type of audit
	* @throws ParseException 
	*/
	public static void addAuditHistory(User user, int type) throws ParseException{
		AuditHistory auditHis = new AuditHistory();
		Audit auditType = AuditBUS.getById(type);
		auditHis.setUser(user);
		auditHis.setAudit(auditType);
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date dateNow = new Date();
		
		auditHis.setAuditTime(df.parse(df.format(dateNow)));
		AuditHistoryBUS.add(auditHis);
	}
}

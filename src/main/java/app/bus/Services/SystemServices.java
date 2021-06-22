package app.bus.Services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class SystemServices {
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
}

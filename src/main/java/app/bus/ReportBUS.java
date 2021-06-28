package app.bus;

import java.util.List;

import app.dao.ReportDAO;
import app.dto.Report;

public class ReportBUS {
	public static int add(Report report) {
		return ReportDAO.add(report);
	}
	
	public static void update(Report report) {
		ReportDAO.update(report);
	}
	
	public static List<Report> getReportsById(int id) {
		return ReportDAO.getReportsById(id);
	}
	
	public static Report getById(int id) {
		return ReportDAO.getById(id);
	}
}

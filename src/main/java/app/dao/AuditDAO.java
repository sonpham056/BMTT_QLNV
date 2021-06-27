package app.dao;

import org.hibernate.Session;

import app.dto.Audit;
import app.util.HibernateUtil;

public class AuditDAO {
	public static Audit getById(int id) {
		Audit audit = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			audit = session.get(Audit.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return audit;
	}
}

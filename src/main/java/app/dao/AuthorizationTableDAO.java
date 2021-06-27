package app.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import app.dto.AuthorizationTable;
import app.util.HibernateUtil;

public class AuthorizationTableDAO {
	public static int add(AuthorizationTable author) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		int id = -1;
		try {
			id = (Integer) session.save(author);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return id;
	}
	
	public static void update(AuthorizationTable author) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(author);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}

package app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import app.dto.Report;
import app.util.HibernateUtil;

public class ReportDAO {
	public static int add(Report report) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		int id = -1;
		try {
			id = (Integer) session.save(report);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return id;
	}
	
	public static void update(Report report) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(report);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public static void delete(Report report) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.remove(report);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Report> getReportsById(int id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Report> list = null;
		try {
			String hql = "from Report r where r.receiver.userId = ?0 order by r.sendDate desc";
			Query query = session.createQuery(hql);
			query.setParameter(0, id);
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	public static Report getById(int id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Report report = null;
		try {
			report = session.get(Report.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return report;
	}
}

package app.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import app.dto.AuditHistory;
import app.util.HibernateUtil;

public class AuditHistoryDAO {
	public static int add(AuditHistory audit) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		int id = -1;
		try {
			id = (Integer) session.save(audit);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return id;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<AuditHistory> getAuditHistory(String userType) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		List<AuditHistory> list = null;
		try {
			String hql = "select his from AuditHistory his, User u, Role r "
					+ "where his.user.userId = u.userId and u.role.roleId = r.roleId and r.roleName = ?0 "
					+ "order by his.auditTime desc";
			Query query = session.createQuery(hql);
			query.setParameter(0, userType);
			list = query.list();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<AuditHistory> getAuditHistoryWithDates(Date from, Date to, String userType) {
		List<AuditHistory> list = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hql = "select his from AuditHistory his, User u, Role r "
					+ " where his.user.userId = u.userId and u.role.roleId = r.roleId and r.roleName = ?0 "
					+ " AND his.auditTime >= ?1"
					+ " AND his.auditTime <= ?2"
					+ " order by his.auditTime desc";
			Query query = session.createQuery(hql);
			query.setParameter(0, userType);
			query.setParameter(1, from);
			query.setParameter(2, to);
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
}

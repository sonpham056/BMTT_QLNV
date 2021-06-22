package app.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import app.dto.User;
import app.util.HibernateUtil;

public class UserDAO {
	public static User getById(int id) {
		User user = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			user = session.get(User.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}
	
	@SuppressWarnings("rawtypes")
	public static User getByName(String name) {
		User user = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hql = "from User u where u.name = ?0";
			Query query = session.createQuery(hql);
			query.setParameter(0, name);
			user = (User) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}
	
	@SuppressWarnings("rawtypes")
	public static User getByEmail(String email) {
		User user = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hql = "from User u where u.email = ?0";
			Query query = session.createQuery(hql);
			query.setParameter(0, email);
			user = (User) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}
	
	@SuppressWarnings("rawtypes")
	public static User getLoginUser(String email, String password) {
		User user = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hql = "from User u where u.email = ?0 and u.password = ?1";
			Query query = session.createQuery(hql);
			query.setParameter(0, email);
			query.setParameter(1, password);
			user = (User) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}
	
	public static int add(User user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		int id = -1;
		try {
			id = (Integer) session.save(user);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return id;
	}
}

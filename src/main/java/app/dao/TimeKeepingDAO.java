package app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import app.dto.TimeKeeping;
import app.util.HibernateUtil;

public class TimeKeepingDAO {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<TimeKeeping> getById(int id) {
		List<TimeKeeping> list = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hql = "from TimeKeeping t where t.user.userId = ?0"
					+ "order by t.startTime desc";
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
	
	public static int addStartTime(TimeKeeping time) {
		int id = -1;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			id = (Integer) session.save(time);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return id;
	}
	
	public static void updateEndTime(TimeKeeping time) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(time);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static TimeKeeping getLatestTimeKeeping(String userName) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		TimeKeeping time = null;
		try {
			String hql = "from TimeKeeping time where time.user.email = ?0 order by time.startTime desc";
			Query query = session.createQuery(hql);
			query.setParameter(0, userName);
			time = (TimeKeeping) query.setMaxResults(1).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return time;
	}
}

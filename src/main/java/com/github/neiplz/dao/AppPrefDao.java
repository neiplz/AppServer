package com.github.neiplz.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.neiplz.entity.AppPref;
import com.github.neiplz.entity.AppUser;

@Repository
@Transactional
public class AppPrefDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void save(AppPref appPref) {
		getSession().save(appPref);
		return;
	}

	public void delete(AppPref appPref) {
		getSession().delete(appPref);
	}

	@SuppressWarnings("unchecked")
	public List<AppPref> getAll() {
		return getSession().createQuery("from AppPref").list();
	}

	public AppPref getById(long id) {
		return (AppPref) getSession()
				.createQuery("from AppPref where id = :id")
				.setParameter("id", id).uniqueResult();
	}

	public void update(AppPref appPref) {
		getSession().update(appPref);
	}
	

	public AppPref getByEmail(String email) {
		return (AppPref) getSession()
				.createQuery("from AppPref where email = :email")
				.setParameter("email", email).uniqueResult();
	}

	public int countByEmail(String email) {
		return getSession()
				.createQuery("from AppPref where email = :email")
				.setParameter("email", email).list().size();
	}

	public int updateAppPref(AppPref appPref) {
		if(null != appPref){
			String email = appPref.getEmail();
			int goal = appPref.getGoal();
			float sensistivity = appPref.getSensitivity();
			int stride = appPref.getStride();
			
			String hql = "update AppPref set goal = :goal, sensistivity = :sensistivity, stride = :stride where email = :email";
			Query query = getSession().createQuery(hql);
			
			query.setParameter("goal", goal);
			query.setParameter("sensistivity", sensistivity);
			query.setParameter("stride", stride);
			query.setParameter("email", email);
			return query.executeUpdate();
		}
		return 0;
	}

	public int updateAppPrefByGoal(String email, Integer goal) {
		Query query = getSession().createQuery(
				"update AppPref set goal = :goal where email = :email");
		query.setParameter("goal", goal);
		query.setParameter("email", email);
		return query.executeUpdate();
	}

	public int updateAppPrefByStride(String email, Integer stride) {
		Query query = getSession().createQuery(
				"update AppPref set stride = :stride where email = :email");
		query.setParameter("stride", stride);
		query.setParameter("email", email);
		return query.executeUpdate();
	}

	public int updateAppPrefBySensitivity(String email, Float sensitivity) {
		Query query = getSession().createQuery(
				"update AppPref set sensitivity = :sensitivity where email = :email");
		query.setParameter("sensitivity", sensitivity);
		query.setParameter("email", email);
		return query.executeUpdate();
	}

	
}

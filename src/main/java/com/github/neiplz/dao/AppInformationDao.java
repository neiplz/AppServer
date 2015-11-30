package com.github.neiplz.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.neiplz.entity.AppInformation;

@Repository
@Transactional
public class AppInformationDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void save(AppInformation appInfo) {
		getSession().save(appInfo);
		return;
	}

	public void delete(AppInformation appInfo) {
		getSession().delete(appInfo);
		return;
	}

	@SuppressWarnings("unchecked")
	public List<AppInformation> getAll() {
		return getSession().createQuery("from AppInformation").list();
	}

	public AppInformation getByName(String name) {
		return (AppInformation) getSession()
				.createQuery("from AppInformation where name = :name")
				.setParameter("name", name).uniqueResult();
	}

	public AppInformation getById(long id) {
//		return (AppInformation) getSession().load(AppInformation.class, id);
		return (AppInformation) getSession()
				.createQuery("from AppInformation where id = :id")
				.setParameter("id", id).uniqueResult();
	}

	public void update(AppInformation appInfo) {
		getSession().update(appInfo);
		return;
	}

}

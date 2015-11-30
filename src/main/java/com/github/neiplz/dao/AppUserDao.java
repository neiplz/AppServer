package com.github.neiplz.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.neiplz.entity.AppUser;

@Repository
@Transactional
public class AppUserDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void save(AppUser appUser) {
		getSession().save(appUser);
		return;
	}

	public void delete(AppUser appUser) {
		getSession().delete(appUser);
		return;
	}

	@SuppressWarnings("unchecked")
	public List<AppUser> getAll() {
		return getSession().createQuery("from AppUser").list();
	}

	public AppUser getByName(String name) {
		return (AppUser) getSession()
				.createQuery("from AppUser where name = :name")
				.setParameter("name", name).uniqueResult();
	}

	public AppUser getById(long id) {
//		return (AppUser) getSession().load(AppUser.class, id);
		return (AppUser) getSession()
				.createQuery("from AppUser where id = :id")
				.setParameter("id", id).uniqueResult();
	}

	public void update(AppUser appUser) {
		getSession().update(appUser);
		return;
	}
	

	public AppUser getByEmail(String email) {
		return (AppUser) getSession()
				.createQuery("from AppUser where email = :email")
				.setParameter("email", email).uniqueResult();
	}

	public int countByEmail(String email) {
		return getSession()
				.createQuery("from AppUser where email = :email")
				.setParameter("email", email).list().size();
	}

	public int countBy(String email, String pwd) {
		return getSession()
				.createQuery("from AppUser where email = :email and password = :password")
				.setParameter("email", email)
				.setParameter("password", pwd)
				.list()
				.size();
	}

	public int updatePassword(String email, String oldPwd, String newPwd) {
		Query query = getSession().createQuery("update AppUser set password = :newPassword" +
				" where email = :email and password = :oldPassword");
		query.setParameter("newPassword", newPwd);
		query.setParameter("email", email);
		query.setParameter("oldPassword", oldPwd);
		return query.executeUpdate();
	}

	public int updateAppUser(AppUser appUser) {
		if(null != appUser){
			String email = appUser.getEmail();
			String name = appUser.getName();
			String gender = appUser.getGender();
			Float height = appUser.getHeight();
			Float weight = appUser.getWeight();
			
			boolean hasGender =  false;
			if(null != gender && gender.length() > 0){
				hasGender = true;
			}
			
			boolean hasName = false;
			if(null != name && name.length() > 0){
				hasName = true;
			}
			
			boolean hasHeight = false;
			if(null != height){
				hasHeight = true;
			}
			
			boolean hasWeight = false;
			if(null != weight){
				hasWeight = true;
			}
			
			if(!hasGender && !hasHeight && !hasWeight){
				return -1; 
			}
			
			int count = 0;
			StringBuffer hql = new StringBuffer();
			hql.append("update AppUser set");
			if(hasGender){
				hql.append(" gender = :gender ");
				count++;
			}
			
			if(hasName){
				if(count > 0){
					hql.append(",");
				}
				hql.append(" name = :name ");
				count++;
			}
			
			if(hasHeight){
				if(count > 0){
					hql.append(",");
				}
				hql.append(" height = :height ");
				count++;
			}
			
			if(hasWeight){
				if(count > 0){
					hql.append(",");
				}
				hql.append(" weight = :weight ");
				count++;
			}
			
			hql.append(" where email = :email");
			
			Query query = getSession().createQuery(hql.toString());
			if(hasGender){
				query.setParameter("gender", gender);
			}
			if(hasName){
				query.setParameter("name", name);
			}
			if(hasHeight){
				query.setParameter("height", height);
			}
			if(hasWeight){
				query.setParameter("weight", weight);
			}
			query.setParameter("email", email);
			return query.executeUpdate();
		}
		return 0;
	}

	
}

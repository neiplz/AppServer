package com.github.neiplz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.neiplz.dao.AppUserDao;
import com.github.neiplz.entity.AppInformation;
import com.github.neiplz.entity.AppUser;

@Component
public class AppUserService {
	
	@Autowired
	private AppUserDao appUserDao;

	public void delete(AppUser appUser) {
		appUserDao.delete(appUser);
	}

	public AppUser getByName(String name) {
		AppUser appUser = appUserDao.getByName(name);
		return appUser;
	}

	public void save(AppUser appUser) {
		appUserDao.save(appUser);
	}
	
	public List<AppUser> getAll() {
		return appUserDao.getAll();
	}

	public AppUser getById(long id) {
		return appUserDao.getById(id);
	}
	
	public AppUser getByEmail(String email) {
		AppUser appUser = appUserDao.getByEmail(email);
		return appUser;
	}
	

	public int countByEmail(String email) {
		return appUserDao.countByEmail(email);
	}
	
	public int countBy(String email, String pwd) {
		return appUserDao.countBy(email, pwd);
	}

	public int updatePassword(String email, String oldPwd, String newPwd) {
		return appUserDao.updatePassword(email, oldPwd, newPwd);
	}
	
	public int updateAppUser(AppUser appUser){
		return appUserDao.updateAppUser(appUser);
	}
}

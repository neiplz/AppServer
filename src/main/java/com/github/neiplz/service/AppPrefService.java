package com.github.neiplz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.neiplz.dao.AppPrefDao;
import com.github.neiplz.entity.AppPref;
import com.github.neiplz.entity.AppUser;

@Component
public class AppPrefService {
	
	@Autowired
	private AppPrefDao appPrefDao;

	public void delete(AppPref appPref) {
		appPrefDao.delete(appPref);
	}

	public void save(AppPref appPref) {
		appPrefDao.save(appPref);
	}
	
	public List<AppPref> getAll() {
		return appPrefDao.getAll();
	}

	public AppPref getById(long id) {
		return appPrefDao.getById(id);
	}
	
	public AppPref getByEmail(String email) {
		AppPref appPref = appPrefDao.getByEmail(email);
		return appPref;
	}
	

	public int countByEmail(String email) {
		return appPrefDao.countByEmail(email);
	}

	public int updateAppPref(AppPref appPrefr) {
		return appPrefDao.updateAppPref(appPrefr);
	}

	public int updateAppPrefByGoal(String email, Integer goal) {
		return appPrefDao.updateAppPrefByGoal(email,goal);
	}

	public int updateAppPrefByStride(String email, Integer stride) {
		return appPrefDao.updateAppPrefByStride(email,stride);
	}

	public int updateAppPrefBySensitivity(String email, Float sensitivity) {
		return appPrefDao.updateAppPrefBySensitivity(email,sensitivity);
	}
	
}

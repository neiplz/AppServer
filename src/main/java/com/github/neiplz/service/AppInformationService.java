package com.github.neiplz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.neiplz.dao.AppInformationDao;
import com.github.neiplz.entity.AppInformation;

@Component
public class AppInformationService {
	
	@Autowired
	private AppInformationDao appInfoDao;

	public void delete(AppInformation appInfo) {
		appInfoDao.delete(appInfo);
	}

	public AppInformation getByName(String name) {
		AppInformation appInfo = appInfoDao.getByName(name);
		return appInfo;
	}
	
	public AppInformation getById(long id) {
		return appInfoDao.getById(id);
	}

	public void save(AppInformation appInfo) {
		appInfoDao.save(appInfo);
	}
	
	public List<AppInformation> getAll() {
		return appInfoDao.getAll();
	}
	
}

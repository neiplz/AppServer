package com.github.neiplz.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.neiplz.entity.AppInformation;
import com.github.neiplz.service.AppInformationService;
import com.github.neiplz.utils.DateUtils;

@Controller
@RequestMapping(value = "/appInfo")
public class AppInformationController {

	@Autowired
	private AppInformationService appInfoService;

	@RequestMapping(value = "/delete")
	@ResponseBody
	public String delete(long id) {
		AppInformation appInfo = new AppInformation(id);
		appInfoService.delete(appInfo);
		return "App信息[id=" + id + "]删除成功!";
	}

	@RequestMapping(value = "/getByName")
	@ResponseBody
	public AppInformation getByName(String name) {
		AppInformation appInfo = appInfoService.getByName(name);
//		if (null != appInfo) {
//			return "App信息: " + appInfo.toString();
//		} else {
//			return "获取App信息失败";
//		}
		return appInfo;
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	public String create(String name, String versionName, Integer versionCode) {
		AppInformation appInfo = new AppInformation(name, versionName, versionCode);
		appInfo.setCreateTime(DateUtils.getDateTime());
		appInfoService.save(appInfo);
		return "App信息添加成功！";
	}
	
	@RequestMapping("/getById/{id}")
	@ResponseBody
	public AppInformation getById(@PathVariable("id") long id) {
		AppInformation appInfo = appInfoService.getById(id);
		return appInfo;
	}

	@RequestMapping(value = "/h")
	public String home() {
		return "home.jsp";
	}

}

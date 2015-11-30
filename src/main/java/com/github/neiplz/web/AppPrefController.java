package com.github.neiplz.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.neiplz.entity.AppPref;
import com.github.neiplz.entity.AppUser;
import com.github.neiplz.entity.LoginCheckResult;
import com.github.neiplz.service.AppPrefService;
import com.github.neiplz.utils.Constants;
import com.github.neiplz.utils.EncryptionUtils;

@Controller
@RequestMapping(value = "/pref")
public class AppPrefController {

	@Autowired
	private AppPrefService appPrefService;

	@RequestMapping(value = "/delete")
	@ResponseBody
	public String delete(long id) {
		AppPref appPref = new AppPref(id);
		appPrefService.delete(appPref);
		return "用户信息[id=" + id + "]删除成功!";
	}

	@RequestMapping(value = "/getByEmail")
	@ResponseBody
	public Map<String, Object> getByEmail(String email){
		Map<String, Object> map = new HashMap<String, Object>();
		
		AppPref appPref = appPrefService.getByEmail(email);
		if(null != appPref){
			map.put("resultCode", 0);
			map.put("data", appPref);
		} else {
			map.put("resultCode", 1);//信息获取失败
		}
		return map;
	}
	
	@RequestMapping(value = "/updateAppPref")
	@ResponseBody
	public Map<String, Integer> updateAppPref(String email, Integer stride, Integer goal,Float sensitivity){
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		AppPref appPrefr = new AppPref();
		appPrefr.setEmail(email);
		appPrefr.setStride(null==stride?0:stride);
		appPrefr.setGoal(null==goal?0:goal);
		appPrefr.setSensitivity(null==sensitivity?0.0f:sensitivity);
		
		int result = appPrefService.updateAppPref(appPrefr);
		if(result == 1){
			map.put("resultCode", 0);//更新成功
		} else if(result == -1){
			map.put("resultCode", 1);//未提供相应参数，不更新
		} else {
			map.put("resultCode", 2);//更新信息失败
		}
		return map;
	}
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Integer> create(String email, Integer stride, Integer goal,Float sensitivity) {

		Map<String, Integer> map = new HashMap<String, Integer>();
		
		AppPref appPrefr = new AppPref();
		appPrefr.setEmail(email);
		appPrefr.setStride(null==stride?0:stride);
		appPrefr.setGoal(null==goal?0:goal);
		appPrefr.setSensitivity(null==sensitivity?0.0f:sensitivity);

		int count = appPrefService.countByEmail(email);
		if (count == 0) {
			appPrefService.save(appPrefr);
			map.put("resultCode", 0);
		} else {
			map.put("resultCode", 2);// 邮箱被占用
		}

		return map;
	}

	@RequestMapping("/getById/{id}")
	@ResponseBody
	public AppPref getById(@PathVariable("id") long id) {
		AppPref appPref = appPrefService.getById(id);
		return appPref;
	}


	@RequestMapping(value = "/sync")
	@ResponseBody
	public Map<String, Object> sync(Integer flag, String email, Integer stride, Integer goal,Float sensitivity) {

		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultCode", 10);// 参数错误
		
		if(null != flag){
			if(1 == flag){
				map.put("resultCode", 5);// 上传失败，提交参数错误
				//上传
				if(null != email && email.length() > 0){
					AppPref pref = appPrefService.getByEmail(email);
					if(null == pref){
						//服务器上无该账号的配置信息
						pref = new AppPref();
						pref.setEmail(email);
						pref.setStride(null==stride?0:stride);
						pref.setGoal(null==goal?0:goal);
						pref.setSensitivity(null==sensitivity?0.0f:sensitivity);
						
						appPrefService.save(pref);
						map.put("resultCode", 1);// 上传成功
					} else {
						int rows = 0;
						if(null != goal && goal > 0){
							rows += appPrefService.updateAppPrefByGoal(email,goal);
						}
						
						if(null != stride && stride > 0 ){
							rows += appPrefService.updateAppPrefByStride(email,stride);
						}
						
						if(null != sensitivity && sensitivity > 0.0f ){
							rows += appPrefService.updateAppPrefBySensitivity(email,sensitivity);
						}
						
						if(rows > 0){
							map.put("resultCode", 1);// 上传成功
						} else {
							map.put("resultCode", 3);// 上传失败，参数无效
						}
					}
				}
				
			} else if(2 == flag){
				map.put("resultCode", 6);// 下载失败，提交参数错误
				//下载
				if(null != email && email.length() > 0){
					AppPref pref = appPrefService.getByEmail(email);
					if(null != pref){
						map.put("resultCode", 2);// 下载成功
						map.put("data",pref);
					} else{
						map.put("resultCode", 4);// 服务器 无该账号对应的配置
					}
				}
			}
		}
		return map;
	}
}

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

import com.github.neiplz.entity.AppUser;
import com.github.neiplz.entity.LoginCheckResult;
import com.github.neiplz.service.AppUserService;
import com.github.neiplz.utils.Constants;
import com.github.neiplz.utils.DateUtils;
import com.github.neiplz.utils.EncryptionUtils;

@Controller
@RequestMapping(value = "/user")
public class AppUserController {

	@Autowired
	private AppUserService appUserService;

	@RequestMapping(value = "/delete")
	@ResponseBody
	public String delete(long id) {
		AppUser appUser = new AppUser(id);
		appUserService.delete(appUser);
		return "用户信息[id=" + id + "]删除成功!";
	}

	@RequestMapping(value = "/getByName")
	@ResponseBody
	public AppUser getByName(String name) {
		AppUser appUser = appUserService.getByName(name);
		// if (null != appUser) {
		// return "用户信息: " + appUser.toString();
		// } else {
		// return "获取用户信息失败";
		// }
		return appUser;
	}
	
	@RequestMapping(value = "/getByEmail")
	@ResponseBody
	public Map<String, Object> getByEmail(String email){
		Map<String, Object> map = new HashMap<String, Object>();
		
		AppUser appUser = appUserService.getByEmail(email);
		if(null != appUser){
			appUser.setPassword(null);
			map.put("resultCode", 0);
			map.put("data", appUser);
		} else {
			map.put("resultCode", 1);//信息获取失败
		}
		return map;
	}
	
	@RequestMapping(value = "/updateAppUser")
	@ResponseBody
	public Map<String, Integer> updateAppUser(String email, String name, String gender,Float height, Float weight){
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		AppUser appUser = new AppUser();
		appUser.setEmail(email);
		appUser.setName(name);
		appUser.setGender(gender);
		appUser.setHeight(height);
		appUser.setWeight(weight);
		
		int result = appUserService.updateAppUser(appUser);
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
	public Map<String, Integer> create(String email, String name,
			String password) {

		Map<String, Integer> map = new HashMap<String, Integer>();
		
		try {
			password = URLDecoder.decode(password, "UTF-8");
			//处理请求中带来的'\n'问题
			password = password.replaceAll("\\s*|\t|\r|\n", "");  
//			password = EncryptionUtils.decryptDES(password, Constants.ENCRYPTION_KEY);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 

		int count = appUserService.countByEmail(email);
		if (count == 0) {
			AppUser appUser = new AppUser(email, name, password);
			appUser.setCreateTime(DateUtils.getDateTime());
			appUserService.save(appUser);
			map.put("resultCode", 0);
		} else {
			map.put("resultCode", 2);// 邮箱被占用
		}

		// AppUser appUser = new AppUser(email, name, password);
		// appUser.setCreateTime(DateUtils.getDateTime());
		// appUserService.save(appUser);

		return map;
	}

	@RequestMapping("/getById/{id}")
	@ResponseBody
	public AppUser getById(@PathVariable("id") long id) {
		AppUser appUser = appUserService.getById(id);
		return appUser;
	}

	@RequestMapping("/loginCheck")
	@ResponseBody
	public LoginCheckResult loginCheck(String email, String password) {
		LoginCheckResult result = new LoginCheckResult();
		AppUser appUser = appUserService.getByEmail(email);

		try {
			password = URLDecoder.decode(password, "UTF-8");
			password = EncryptionUtils.decryptDES(password, Constants.ENCRYPTION_KEY);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null != appUser) {
			String pwd = appUser.getPassword();
			try {
				pwd = EncryptionUtils.decryptDES(pwd, Constants.ENCRYPTION_KEY);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (null != pwd && pwd.equals(password)) {
				result.setResultCode(0);
			} else {
				result.setResultCode(1);
				result.setErrorMsg("邮箱、密码不匹配！");
			}
		} else {
			result.setResultCode(2);
			result.setErrorMsg("邮箱不存在！");
		}
		return result;
	}
	
	@RequestMapping(value = "/updatePwd")
	@ResponseBody
	public Map<String, Object> updatePwd(String email, String oldPwd, String newPwd) {

		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			oldPwd = URLDecoder.decode(oldPwd, "UTF-8");
			newPwd = URLDecoder.decode(newPwd, "UTF-8");
			//处理请求中带来的'\n'问题
			oldPwd = oldPwd.replaceAll("\\s*|\t|\r|\n", "");  
			newPwd = newPwd.replaceAll("\\s*|\t|\r|\n", "");  
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 

		int count = appUserService.countBy(email,oldPwd);
		
//		map.put("resultCode", 2);
//		map.put("errorMsg", "密码修改失败");
		if (count == 0) {
			map.put("resultCode", 1);//原密码不正确
			map.put("errorMsg", "原密码不正确");
		} else if(count == 1) {
			int result = appUserService.updatePassword(email,oldPwd,newPwd);
			if(result ==1){
				map.put("resultCode", 0);// 密码修改成功
			}
		}
		
		if(null == map.get("resultCode")){
			map.put("resultCode", 2);
			map.put("errorMsg", "密码修改失败");
		}
		return map;
	}

}

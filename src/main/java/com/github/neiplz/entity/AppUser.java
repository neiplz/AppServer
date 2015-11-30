package com.github.neiplz.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.github.neiplz.utils.DateUtils;

@Entity
@Table(name = "app_user")
public class AppUser implements Serializable{

	private static final long serialVersionUID = 8294115816940345939L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Size(min = 3, max = 100)
	private String email;

	@NotNull
	@Size(min = 2, max = 100)
	private String name;

	@NotNull
	@Size(min = 6, max = 30)
	private String password;

	@Size(min = 1, max = 1)
	private String gender = "0";
	@Size(min = 3, max = 100)
	private String phone;

	@Size(min = 1, max = 200)
	private String remark;

	@Size(min = 0, max = 19)
	private String createTime;
	@Size(min = 0, max = 19)
	private String updateTime;

	@Size(min = 0, max = 1)
	private String delFlag = "0";

	private Float weight;
	private Float height;

	public AppUser() {
		super();
		// TODO Auto-generated constructor stub
		this.updateTime = DateUtils.getDateTime();
	}

	public AppUser(long id) {
		super();
		this.id = id;
		this.updateTime = DateUtils.getDateTime();
	}

	public AppUser(String email, String name, String password) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
		this.updateTime = DateUtils.getDateTime();
	}

	public AppUser(long id, String email, String name, String gender,
			String phone) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.gender = gender;
		this.phone = phone;
		this.updateTime = DateUtils.getDateTime();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public Float getHeight() {
		return height;
	}

	public void setHeight(Float height) {
		this.height = height;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "AppUser [id=" + id + ", email=" + email + ", name=" + name
				+ ", password=" + password + ", gender=" + gender + ", phone="
				+ phone + ", remark=" + remark + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", delFlag=" + delFlag
				+ ", weight=" + weight + ", height=" + height + "]";
	}

}

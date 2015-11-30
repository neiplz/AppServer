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
@Table(name = "app_information")
public class AppInformation implements Serializable{

	private static final long serialVersionUID = -9107973163838068757L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	@Size(min = 2, max = 80)
	private String name;

	@NotNull
	@Size(min = 1, max = 50)
	private String versionName;

	private Integer versionCode;

	@Size(min = 1, max = 255)
	private String description;

	@Size(min = 1, max = 1000)
	private String downloadUrl;

	@Size(min = 1, max = 200)
	private String remark;

	@Size(min = 0, max = 19)
	private String createTime;
	@Size(min = 0, max = 19)
	private String updateTime;

	@Size(min = 0, max = 1)
	private String delFlag = "0";

	public AppInformation() {
		this.updateTime = DateUtils.getDateTime();
	}

	public AppInformation(long id) {
		this.id = id;
		this.updateTime = DateUtils.getDateTime();
	}

	public AppInformation(String name, String versionName, Integer versionCode) {
		super();
		this.name = name;
		this.versionName = versionName;
		this.versionCode = versionCode;
		this.updateTime = DateUtils.getDateTime();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public Integer getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(Integer versionCode) {
		this.versionCode = versionCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
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

	@Override
	public String toString() {
		return "AppInformation [id=" + id + ", name=" + name + ", versionName="
				+ versionName + ", versionCode=" + versionCode
				+ ", description=" + description + ", downloadUrl="
				+ downloadUrl + ", remark=" + remark + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", delFlag="
				+ delFlag + "]";
	}

}

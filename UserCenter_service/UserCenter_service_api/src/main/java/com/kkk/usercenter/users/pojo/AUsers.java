package com.kkk.usercenter.users.pojo;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;

public class AUsers
{
	//用户id
	private int id;
	//用户邮箱
	private String email;
	//用户密码
	private String password;
	//用户手机
	private String phone;
	//用户qq
	private String qq;
	//用户地址
	private String address;
	//登录失败次数
	private int loginFailedCount;
	//登录失败时间
	private Date loginFailedTime;
	//图片路径
	private String photoPath;
	//邮箱状态
	private byte emailStatus;
	//手机状态
	private byte phoneStatus;
	//用户状态
	private byte status;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;
	//上次登录时间
	private Date lastLoginTime;
	
	//关联用户扩展表
	private AUsersExtend usersExtend;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getQq()
	{
		return qq;
	}

	public void setQq(String qq)
	{
		this.qq = qq;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public int getLoginFailedCount()
	{
		return loginFailedCount;
	}

	public void setLoginFailedCount(int loginFailedCount)
	{
		this.loginFailedCount = loginFailedCount;
	}

	public Date getLoginFailedTime()
	{
		return loginFailedTime;
	}

	public void setLoginFailedTime(Date loginFailedTime)
	{
		this.loginFailedTime = loginFailedTime;
	}

	public String getPhotoPath()
	{
		return photoPath;
	}

	public void setPhotoPath(String photoPath)
	{
		this.photoPath = photoPath;
	}

	public byte getEmailStatus()
	{
		return emailStatus;
	}

	public void setEmailStatus(byte emailStatus)
	{
		this.emailStatus = emailStatus;
	}

	public byte getPhoneStatus()
	{
		return phoneStatus;
	}

	public void setPhoneStatus(byte phoneStatus)
	{
		this.phoneStatus = phoneStatus;
	}

	public byte getStatus()
	{
		return status;
	}

	public void setStatus(byte status)
	{
		this.status = status;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public Date getUpdateTime()
	{
		return updateTime;
	}

	public void setUpdateTime(Date updateTime)
	{
		this.updateTime = updateTime;
	}

	public Date getLastLoginTime()
	{
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime)
	{
		this.lastLoginTime = lastLoginTime;
	}

	public AUsersExtend getUsersExtend()
	{
		return usersExtend;
	}

	public void setUsersExtend(AUsersExtend usersExtend)
	{
		this.usersExtend = usersExtend;
	}

	public JSONObject toJSON()
	{
		return null;
	}
}

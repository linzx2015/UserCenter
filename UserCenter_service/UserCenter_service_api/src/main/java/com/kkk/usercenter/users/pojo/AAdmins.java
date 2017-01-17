package com.kkk.usercenter.users.pojo;

import java.util.Date;

public class AAdmins
{	
	//管理员id
	private int id;
	//角色表外键id
	private int roleId;
	//注册邮箱
	private String email;
	//管理员密码
	private String password;
	//真实姓名
	private String trueName;
	//手机号码
	private String phone;
	//qq
	private String qq;
	//登录失败次数
	private int loginFailedCount;
	//登录失败时间
	private Date loginFailedTime;
	//状态
	private byte status;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;
	//上次登录时间
	private Date lastLoginTime;
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getRoleId()
	{
		return roleId;
	}
	public void setRoleId(int roleId)
	{
		this.roleId = roleId;
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
	public String getTrueName()
	{
		return trueName;
	}
	public void setTrueName(String trueName)
	{
		this.trueName = trueName;
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
}

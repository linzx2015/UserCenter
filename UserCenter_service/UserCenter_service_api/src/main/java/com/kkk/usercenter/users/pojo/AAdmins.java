package com.kkk.usercenter.users.pojo;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.kkk.usercenter.common.util.DateFormatUtil;
/**
 * 管理员的pojo
 * @author kkk
 * */
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
	
	private String statusStr;
	//多对一,持有一个角色
	private ARole role;
	
	public ARole getRole()
	{
		return role;
	}
	public void setRole(ARole role)
	{
		this.role = role;
	}
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
	
	//用于前台展示的状态标识
	public String getStatusStr()
	{
		/*
		 * 状态:0 禁用,1 启用
		 * */
		AAdminsEnum[] adminsEnums=AAdminsEnum.values();
		for(int i=0;i<adminsEnums.length;i++)
		{
			AAdminsEnum adminsEnum=adminsEnums[i];
			if(adminsEnum.toString().startsWith("STATUS_"))
			{
				//循环枚举的数值进行比较,数据库的值等于当前值,则进行赋值
				if(this.status==adminsEnum.getStatus())
				{
					this.statusStr=adminsEnum.getInfo();
					break;
				}
			}
		}
		return statusStr;
	}
	
	public JSONObject toJSON()
	{
		DateFormatUtil dateFormatUtil=new DateFormatUtil();
		JSONObject resultJSON=new JSONObject();
		//开放的字段
		resultJSON.put("id", this.getId());
		resultJSON.put("email", this.getEmail());
		resultJSON.put("createTime", dateFormatUtil.formatDateTime(this.getCreateTime()));
		resultJSON.put("updateTime", dateFormatUtil.formatDateTime(this.getUpdateTime()));
		resultJSON.put("lastLoginTime", dateFormatUtil.formatDateTime(this.getLastLoginTime()));
		return resultJSON;
	}
	
}

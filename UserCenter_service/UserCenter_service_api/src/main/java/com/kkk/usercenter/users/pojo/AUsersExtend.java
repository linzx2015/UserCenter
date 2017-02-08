package com.kkk.usercenter.users.pojo;

import java.util.Date;

public class AUsersExtend
{	
	//扩展表id
	private int id;
	//用户表外键
	private int usersId;
	//身份证类型
	private byte idCardType;
	//身份证号码
	private String idCardNum;
	//身份证正面
	private String idCardHead;
	//身份证背面
	private String idCardBack;
	//手持身份证
	private String idCardHand;
	//邮件发送时间
	private Date emailSendTime;
	//邮件发送验证码
	private byte emailSendCode;
	//手机验证码发送时间
	private Date phoneSendTime;
	//手机验证码
	private byte phoneSendCode;
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
	public int getUsersId()
	{
		return usersId;
	}
	public void setUsersId(int usersId)
	{
		this.usersId = usersId;
	}
	public byte getIdCardType()
	{
		return idCardType;
	}
	public void setIdCardType(byte idCardType)
	{
		this.idCardType = idCardType;
	}
	public String getIdCardNum()
	{
		return idCardNum;
	}
	public void setIdCardNum(String idCardNum)
	{
		this.idCardNum = idCardNum;
	}
	public String getIdCardHead()
	{
		return idCardHead;
	}
	public void setIdCardHead(String idCardHead)
	{
		this.idCardHead = idCardHead;
	}
	public String getIdCardBack()
	{
		return idCardBack;
	}
	public void setIdCardBack(String idCardBack)
	{
		this.idCardBack = idCardBack;
	}
	public String getIdCardHand()
	{
		return idCardHand;
	}
	public void setIdCardHand(String idCardHand)
	{
		this.idCardHand = idCardHand;
	}
	public Date getEmailSendTime()
	{
		return emailSendTime;
	}
	public void setEmailSendTime(Date emailSendTime)
	{
		this.emailSendTime = emailSendTime;
	}
	public byte getEmailSendCode()
	{
		return emailSendCode;
	}
	public void setEmailSendCode(byte emailSendCode)
	{
		this.emailSendCode = emailSendCode;
	}
	public Date getPhoneSendTime()
	{
		return phoneSendTime;
	}
	public void setPhoneSendTime(Date phoneSendTime)
	{
		this.phoneSendTime = phoneSendTime;
	}
	public byte getPhoneSendCode()
	{
		return phoneSendCode;
	}
	public void setPhoneSendCode(byte phoneSendCode)
	{
		this.phoneSendCode = phoneSendCode;
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

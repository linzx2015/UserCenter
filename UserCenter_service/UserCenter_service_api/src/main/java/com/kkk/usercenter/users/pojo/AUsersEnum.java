package com.kkk.usercenter.users.pojo;

/**
 * 用户的枚举
 * 
 * @author kkk
 *
 */
public enum AUsersEnum
{
	/* 枚举项 */
	STATUS_ENABLE(Byte.valueOf("1"),"启用"),
	STATUS_DISABLE(Byte.valueOf("0"),"禁用");
	
	/* 状态值 */
	private byte status ;
	/* 描述 */
	private String info;

	private AUsersEnum(byte status, String info)
	{
		this.status = status;
		this.info = info;
	}

	public byte getStatus()
	{
		return status;
	}

	public void setStatus(byte status)
	{
		this.status = status;
	}

	public String getInfo()
	{
		return info;
	}

	public void setInfo(String info)
	{
		this.info = info;
	}
}

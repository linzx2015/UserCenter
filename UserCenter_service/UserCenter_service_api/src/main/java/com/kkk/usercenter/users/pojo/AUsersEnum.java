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
	STATUS_DISABLE(Byte.valueOf("0"),"禁用"),
	STATUS_ENABLE(Byte.valueOf("1"),"启用"),
	EMAILSTATUS_UNVERIFY(Byte.valueOf("0"),"未验证"),
	EMAILSTATUS_VERIFYED(Byte.valueOf("1"),"验证通过"),
	PHONESTATUS_UNVERIFY(Byte.valueOf("0"),"未验证"),
	PHONESTATUS_VERIFYED(Byte.valueOf("1"),"验证通过");
	
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

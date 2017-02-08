package com.kkk.usercenter.users.pojo;

public enum AAdminsEnum
{
	//设置枚举项
	STATUS_ENABLE(Byte.valueOf("1"),"启用"),
	STATUS_DISABLE(Byte.valueOf("0"),"禁用");
	
	//状态值
	private byte status;
	//提示信息
	private String info;
	private AAdminsEnum(byte status,String info)
	{
		this.status=status;
		this.info=info;
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

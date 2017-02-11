package com.kkk.usercenter.system.pojo;

/**
 * 地区的枚举
 * @author kkk
 * */
public enum ARegionEnum
{
	STATUS_DISABLE(Byte.valueOf("0"),"禁用"),
	STATUS_ENABLE(Byte.valueOf("1"),"启用"),
	LEAFSTATUS_FALSE(Byte.valueOf("0"),"非叶子节点"),
	LEAFSTATUS_TRUE(Byte.valueOf("1"),"叶子节点");
	
	private byte status;
	private String name;
	
	private ARegionEnum(byte status,String name)
	{
		this.status=status;
		this.name=name;
	} 
	public byte getStatus()
	{
		return status;
	}
	public void setStatus(byte status)
	{
		this.status = status;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
	@Override
	public String toString()
	{
		return super.toString();
	}
}

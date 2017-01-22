package com.kkk.usercenter.users.pojo;

import java.util.Date;
/**
 * 角色表的pojo
 * @author kkk
 * */
public class ARole
{
	//角色表id
	private int id;
	//姓名
	private String name;
	//内容
	private String content;
	//状态
	private byte status;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;
	//发布时间
	private Date pubTime;
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
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
	public Date getPubTime()
	{
		return pubTime;
	}
	public void setPubTime(Date pubTime)
	{
		this.pubTime = pubTime;
	}
}

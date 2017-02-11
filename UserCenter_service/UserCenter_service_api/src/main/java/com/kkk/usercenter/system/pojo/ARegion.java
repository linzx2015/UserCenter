package com.kkk.usercenter.system.pojo;

import java.util.Date;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kkk.usercenter.common.util.DateFormatUtil;

/**
 * 全国地区的pojo
 * @author kkk
 * */
public class ARegion
{
	private int id;
	private int parentId;
	private String name;
	private String pinyin;
	private String areacode;
	private String content;
	private byte leafStatus;
	private byte status;
	private Date createTime;
	private Date updateTime;
	private Date pubTime;
	
	//关联父级菜单
	private ARegion parentRegion;
	
	//树形分类菜单
	private String treeName;
	private String leafStatusStr;
	private String statusStr;
	
	//json数据传输到前台展示
	private JSONArray treeJSON;
	private JSONArray childrenListJSON;
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getParentId()
	{
		return parentId;
	}
	public void setParentId(int parentId)
	{
		this.parentId = parentId;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getPinyin()
	{
		return pinyin;
	}
	public void setPinyin(String pinyin)
	{
		this.pinyin = pinyin;
	}
	public String getAreacode()
	{
		return areacode;
	}
	public void setAreacode(String areacode)
	{
		this.areacode = areacode;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public byte getLeafStatus()
	{
		return leafStatus;
	}
	public void setLeafStatus(byte leafStatus)
	{
		this.leafStatus = leafStatus;
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
	public ARegion getParentRegion()
	{
		return parentRegion;
	}
	public void setParentRegion(ARegion parentRegion)
	{
		this.parentRegion = parentRegion;
	}
	public String getTreeName()
	{
		return treeName;
	}
	public void setTreeName(String treeName)
	{
		this.treeName = treeName;
	}
	public JSONArray getTreeJSON()
	{
		return treeJSON;
	}
	public void setTreeJSON(JSONArray treeJSON)
	{
		this.treeJSON = treeJSON;
	}
	public JSONArray getChildrenListJSON()
	{
		return childrenListJSON;
	}
	public void setChildrenListJSON(JSONArray childrenListJSON)
	{
		this.childrenListJSON = childrenListJSON;
	}
	
	//将地区对象拼装成json数据
	public JSONObject toJSON()
	{
		DateFormatUtil dateFormatUtil=new DateFormatUtil();
		JSONObject regionJSON=new JSONObject();
		regionJSON.put("id", this.getId()+"");
		regionJSON.put("parentId", this.getParentId()+"");
		regionJSON.put("name", this.getName());
		regionJSON.put("pinyin", this.getPinyin());
		regionJSON.put("areacode", this.getAreacode());
		regionJSON.put("content", this.getContent());
		regionJSON.put("leafStatus", this.getLeafStatus());
		regionJSON.put("createTime", dateFormatUtil.formatDateTime(this.getCreateTime()));
		regionJSON.put("updateTime", dateFormatUtil.formatDateTime(this.getUpdateTime()));
		regionJSON.put("pubTime", dateFormatUtil.formatDateTime(this.getPubTime()));
		regionJSON.put("treeName", this.getTreeName());
		
		if(regionJSON!=null)
		{
			//增加父节点
			regionJSON.put("treeJSON", treeJSON);
		}
		if(childrenListJSON!=null)
		{
			//增加子节点列表
			regionJSON.put("childrenListJSON", childrenListJSON);
		}
		dateFormatUtil=null;
		return regionJSON;
	}
	
	/**
	 * 解析一个json
	 * @param sourceJSON
	 * @return ARegion
	 * */
	public ARegion parseJSON(JSONObject sourceJSON)
	{
		DateFormatUtil dateFormatUtil=new DateFormatUtil();
		ARegion region=new ARegion();
		region.setId(sourceJSON.getIntValue("id"));
		if(sourceJSON.get("parentId")!=null && !"null".equalsIgnoreCase(sourceJSON.get("parentId")+""))
		{
			region.setParentId(sourceJSON.getIntValue("parentId"));
		}
		region.setName(sourceJSON.getString("name"));
		region.setPinyin(sourceJSON.getString("pinyin"));
		region.setAreacode(sourceJSON.getString("areacode"));
		region.setLeafStatus(sourceJSON.getByte("leafStatus"));
		region.setCreateTime(dateFormatUtil.parseDateTime(sourceJSON.getString("createTime")));
		region.setUpdateTime(dateFormatUtil.parseDateTime(sourceJSON.getString("updateTime")));
		region.setPubTime(dateFormatUtil.parseDateTime(sourceJSON.getString("pubTime")));
		region.setTreeName(sourceJSON.getString("treeName"));
		
		region.setTreeJSON(sourceJSON.getJSONArray("treeJSON"));
		region.setChildrenListJSON(sourceJSON.getJSONArray("childrenListJSON"));
		dateFormatUtil=null;
		return region;
	}
	
	//获取叶子枚举名称
	public String getLeafStatusStr()
	{
		ARegionEnum[] regionEnums=ARegionEnum.values();
		for(int i=0;i<regionEnums.length;i++)
		{
			ARegionEnum regionEnum=regionEnums[i];
			if(regionEnum.toString().startsWith("LEAFSTATUS_"))
			{
				//状态的标识
				if(regionEnum.getStatus()==this.getLeafStatus())
				{
					this.leafStatusStr=regionEnum.getName();
					break;
				}
			}
		}
		return leafStatusStr;
	}
	//获取状态枚举名称
	public String getStatusStr()
	{
		ARegionEnum[] regionEnums=ARegionEnum.values();
		for(int i=0;i<regionEnums.length;i++)
		{
			ARegionEnum regionEnum=regionEnums[i];
			if(regionEnum.toString().startsWith("STATUS_"))
			{
				if(regionEnum.getStatus()==this.getStatus())
				{
					this.statusStr=regionEnum.getName();
					break;
				}
			}
		}
		return statusStr;
	}
}

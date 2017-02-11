package com.kkk.usercenter.system.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.kkk.usercenter.common.util.ConstantFinalUtil;
import com.kkk.usercenter.common.util.DateFormatUtil;
import com.kkk.usercenter.common.util.PageInfoUtil;
import com.kkk.usercenter.system.dao.IARegionDAO;
import com.kkk.usercenter.system.pojo.ARegion;
import com.kkk.usercenter.system.pojo.ARegionEnum;
import com.kkk.usercenter.system.service.ISystemService;

/**
 * 属性菜单数据模块实现
 * @author kkk
 * */
@Service("systemService")
public class SystemServiceImpl implements ISystemService
{
	@Resource
	private IARegionDAO regionDao;
	@Override
	public JSONObject insertOneAregionService(ARegion region)
	{
		JSONObject resultJSON=new JSONObject();
		Map<String,Object> paramMap=new HashMap<String,Object>();
		//通过传入父id查询对应的父节点
		paramMap.put("id",region.getParentId());
		ARegion regionRes=this.queryOneAregionService(paramMap);
		if(regionRes!=null)
		{
			//有上一级的菜单,更新父节点的叶子节点
			if(regionRes.getLeafStatus()==ARegionEnum.LEAFSTATUS_FALSE.getStatus())
			{
				regionRes.setLeafStatus(ARegionEnum.LEAFSTATUS_TRUE.getStatus());
				regionRes.setUpdateTime(new Date());
				this.updateOneAregionService(region);
			}
		}
		int res=this.regionDao.insertOne(region);
		if(res>0)
		{
			resultJSON.put("code", "0");
			resultJSON.put("info", ConstantFinalUtil.INFO_JSON.getString("0"));
		}else
		{
			resultJSON.put("code", "1");
			resultJSON.put("info", ConstantFinalUtil.INFO_JSON.getString("1"));
		}
		return resultJSON;
	}

	@Override
	public JSONObject updateOneAregionService(ARegion region)
	{
		JSONObject resultJSON=new JSONObject();
		int res=this.regionDao.updateOne(region);
		if(res>0)
		{
			resultJSON.put("code", "0");
			resultJSON.put("info", ConstantFinalUtil.INFO_JSON.getString("0"));
		}else
		{
			resultJSON.put("code", "2");
			resultJSON.put("info", ConstantFinalUtil.INFO_JSON.getString("2"));
		}
		return resultJSON;
	}

	@Override
	public JSONObject deleteOneAregionService(ARegion region)
	{
		JSONObject resultJSON=new JSONObject();
		int res=this.regionDao.deleteOne(region);
		if(res>0)
		{
			resultJSON.put("code", "0");
			resultJSON.put("info", ConstantFinalUtil.INFO_JSON.getString("0"));
		}else
		{
			resultJSON.put("code", "3");
			resultJSON.put("info", ConstantFinalUtil.INFO_JSON.getString("3"));
		}
		return resultJSON;
	}

	@Override
	public ARegion queryOneAregionService(Map<String, Object> paramMap)
	{
		ARegion region=this.regionDao.queryOne(paramMap);
		return region;
	}

	@Override
	public List<ARegion> queryMultiAregionService(PageInfoUtil pageInfoUtil, Map<String, Object> paramMap)
	{
		List<ARegion> regionList=Collections.EMPTY_LIST;
		//设置分页相关信息
		if(pageInfoUtil!=null)
		{
			paramMap.put("pageCond", "true");
			regionList=this.regionDao.queryMulti(paramMap);
			//先查总记录数,设置分页工具,然后再查询分页
			if(regionList.size()==1)
			{
				ARegion region=regionList.get(0);
				//insert语句中已经将总记录书数存入id中
				pageInfoUtil.setTotalRecord(region.getId());
			}
			paramMap.put("pageCond", "false");
			paramMap.put("currentRecord", pageInfoUtil.getCurrentRecord());
			paramMap.put("pageSize", pageInfoUtil.getPageSize());
		}
		//不分页,直接查询总记录
		regionList=this.regionDao.queryMulti(paramMap);
		return regionList;
	}

	@Override
	public List<ARegion> queryTreeRegionService(ARegion region)
	{
		//未完成
		List<ARegion> regionList=new ArrayList<ARegion>();
		if(region!=null)
		{
			this.queryRegionTreeListUtil(regionList, region);
			Collections.reverse(regionList);
			
			StringBuffer sb=new StringBuffer();
			//遍历全部的region,将名称一一加入到sb中
			for (Iterator iterator = regionList.iterator(); iterator.hasNext();)
			{
				ARegion tmpRegion = (ARegion) iterator.next();
				sb.append(tmpRegion.getName()+ConstantFinalUtil.SPLIT_STR);
			}
			if(!"".equalsIgnoreCase(sb.toString()))
			{
				//sb中的字符串不为空,则将最后一个分隔符去除
				sb.delete(sb.lastIndexOf(ConstantFinalUtil.SPLIT_STR), sb.length());
			}
			region.setTreeName(sb.toString());
		}
		return regionList;
	}
	/**
	 * 递归查询树形菜单结构方法,一级菜单->二级菜单->三级菜单
	 * @param regionList
	 * @param region
	 * */
	private void queryRegionTreeListUtil(List<ARegion> regionList,ARegion region)
	{
		if(region==null)
		{
			return;
		}
		//将每次递归遍历出的region加入到集合中
		regionList.add(region);
		if(region.getParentId()!=0)
		{
			Map<String,Object> paramMap=new HashMap<String,Object>();
			paramMap.put("id", region.getParentId());
			ARegion regionRes=this.queryOneAregionService(paramMap);
			queryRegionTreeListUtil(regionList, regionRes);
		}
	}
}

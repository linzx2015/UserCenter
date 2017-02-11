package com.kkk.usercenter.outer.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kkk.usercenter.common.util.ConstantFinalUtil;
import com.kkk.usercenter.common.util.DateFormatUtil;
import com.kkk.usercenter.outer.service.IOuterService;
import com.kkk.usercenter.system.pojo.ARegion;
import com.kkk.usercenter.system.service.ISystemService;

/**
 * 外部交互接口实现类
 * @author kkk
 * */
@Service("outerService")
public class OuterServiceImpl implements IOuterService
{
	@Resource
	private ISystemService systemService;
	@Resource 
	private DateFormatUtil dateFormatUtil;
	/**
	 * 拼装成下行的json字符串
	 * */
	@Override
	public JSONObject validator01AdminService(JSONObject reqJSON)
	{
		JSONObject dataReqJSON=reqJSON.getJSONObject("data");
		JSONObject resultJSON=new JSONObject();
		//下行的data json
		JSONObject dataRespJSON=new JSONObject();
		//+""可避免空指针异常
		String token=dataReqJSON.getString("token")+"";
		//从token容器中获取admin的json数据
		JSONObject adminJSON=ConstantFinalUtil.TOKENS_MAP.get(token);
		if(adminJSON!=null)
		{
			//拼装响应结果的json串
			dataRespJSON.put("admin", adminJSON);
			resultJSON.put("data", dataRespJSON);
			
			resultJSON.put("version", reqJSON.getString("version"));
			resultJSON.put("code", "0");
			resultJSON.put("info",ConstantFinalUtil.INFO_JSON.get("0"));
		}else
		{
			resultJSON.put("code", "10");
			resultJSON.put("info", ConstantFinalUtil.INFO_JSON.get("10"));
		}
		return resultJSON;
	}
	@Override
	public JSONObject regionList01Service(JSONObject reqJSON)
	{
		 JSONObject resultJSON=new JSONObject();
		 JSONObject dataReqJSON=reqJSON.getJSONObject("data");
		 String parentId=dataReqJSON.getString("parentId");
		 Map<String,Object> paramMap=new HashMap<String,Object>();
		 paramMap.put("parentId", parentId);
		 List<ARegion> childRegionList=this.systemService.queryMultiAregionService(null, paramMap);
		
		 JSONArray regionList=new JSONArray();
		 for(Iterator it=childRegionList.iterator();it.hasNext();)
		 {
			 ARegion regionChild=(ARegion) it.next();
			 JSONObject regionChildJSON=regionChild.toJSON();
			 regionList.add(regionChildJSON);
		 }
		 JSONObject dataResJSON=new JSONObject();
		 dataResJSON.put("regionList", regionList);
		 
		 resultJSON.put("code", "0");
		 resultJSON.put("info", ConstantFinalUtil.INFO_JSON.getString("0"));
		 resultJSON.put("data", dataResJSON);
		return resultJSON;
	}
}

package com.kkk.usercenter.outer.service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.kkk.usercenter.common.util.ConstantFinalUtil;
import com.kkk.usercenter.outer.service.IOuterService;

/**
 * 外部交互接口实现类
 * @author kkk
 * */
@Service("outerService")
public class OuterServiceImpl implements IOuterService
{
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
}

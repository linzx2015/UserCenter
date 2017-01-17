package com.kkk.usercenter.common.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ConstantFinalUtil
{
	//log4j信息提示
	public static final Logger loggerMsg=LogManager.getLogger(ConstantFinalUtil.class);
	//解析json配置文件使用
	public static JSONObject RESOURCE_JSON=new JSONObject();
	//解析配置中的info内容
	public static JSONObject INFO_JSON=new JSONObject();
	static
	{
		//读取配置文件
		ResourceFileUtil rf=new ResourceFileUtil();
		try
		{
			//使用getResourceAsStream获取当前环境下的文件对象
			String resultStr=rf.readFile(ConstantFinalUtil.class.getClassLoader().getResourceAsStream("resourceInfo.json"));
			
			//将全部配置信息转成JSON
			RESOURCE_JSON=(JSONObject) JSON.parse(resultStr);
			//获取配置中的info
			INFO_JSON=RESOURCE_JSON.getJSONObject("info");
			ConstantFinalUtil.loggerMsg.info("--获取JSON配置信息--");
		} catch (Exception e)
		{
			ConstantFinalUtil.loggerMsg.error("--获取JSON配置信息失败--");
		}
	}
}	
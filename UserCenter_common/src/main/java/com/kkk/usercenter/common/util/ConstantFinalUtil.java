package com.kkk.usercenter.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ConstantFinalUtil
{
	// log4j信息提示
	public static final Logger loggerMsg = LogManager.getLogger(ConstantFinalUtil.class);
	// 解析json配置文件使用
	public static JSONObject RESOURCE_JSON = new JSONObject();
	// 解析配置中的info内容
	public static JSONObject INFO_JSON = new JSONObject();
	// 连接超时时间
	public static final int CONNECT_TIMEOUT = 100000;
	// 读取超时时间
	public static final int READ_TIMEOUT = 100000;
	// 分隔符 
	public static final String SPLIT_STR = "|-->";
	// 邮件验证超时时间
	public static final long EMAIL_AUTH_TIMEOUT=1000*60*60*24;
	// 获取properties属性文件信息,只需获取属性文件名称即可
	public static ResourceBundle BUNDLE = ResourceBundle.getBundle("common");
	// 存储所有的token数据,token为键,Admins对应的json为值
	public static Map<String, JSONObject> TOKENS_MAP = new HashMap<String, JSONObject>();
	// 全部随机字符的字母表
	public static String ALLSTR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	static
	{
		// 读取配置文件
		ResourceFileUtil rf = new ResourceFileUtil();
		try
		{
			// 使用getResourceAsStream获取当前环境下的文件对象
			String resultStr = rf
					.readFile(ConstantFinalUtil.class.getClassLoader().getResourceAsStream("resourceInfo.json"));

			// 将全部配置信息转成JSON
			RESOURCE_JSON = (JSONObject) JSON.parse(resultStr);
			// 获取配置中的info
			INFO_JSON = RESOURCE_JSON.getJSONObject("info");
			ConstantFinalUtil.loggerMsg.info("--获取JSON配置信息--");
		} catch (Exception e)
		{
			ConstantFinalUtil.loggerMsg.error("--获取JSON配置信息失败--");
		}
	}
}
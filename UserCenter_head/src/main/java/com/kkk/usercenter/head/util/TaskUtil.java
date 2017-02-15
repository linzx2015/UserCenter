package com.kkk.usercenter.head.util;

import java.util.Date;
import java.util.Iterator;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.kkk.usercenter.common.util.ConstantFinalUtil;
import com.kkk.usercenter.common.util.DateFormatUtil;

/**
 * 清理token任务调度
 * @author kkk
 * */
@Component("taskUtil")
public class TaskUtil
{
	/**
	 * 定时清理token
	 * 由spring的任务进行调度 
	 * */
	public void runTask()
	{
		ConstantFinalUtil.loggerMsg.info("----调度任务开始-----");
		this.cleanToken();
		ConstantFinalUtil.loggerMsg.info("----调度任务结束-----");
	}
	/**
	 * 真正开始清理token
	 * */
	public void cleanToken()
	{
		ConstantFinalUtil.loggerMsg.info("----清理token开始---token数量:{}--",ConstantFinalUtil.TOKENS_MAP.size());
		DateFormatUtil dateFormatUtil=new DateFormatUtil();
		/**
		 * 循环存储token的map
		 * 取出token对应的管理员信息,当前时间-管理员上次验证的时间,如果大于两分钟,将此token移除
		 * */
		for(Iterator it=ConstantFinalUtil.TOKENS_MAP.entrySet().iterator();it.hasNext(); )
		{
			Entry me=(Entry) it.next();
			String token=me.getKey()+"";
			JSONObject adminJSON=(JSONObject) me.getValue();
			ConstantFinalUtil.loggerMsg.debug("--key{},value{}--",token,adminJSON);
			/**
			 * 将字符串转换日期时间
			 * */
			Date validate=dateFormatUtil.parseDateTime(adminJSON.get("validate")+"");
			if(System.currentTimeMillis()-validate.getTime()>1000*60*2)
			{
				ConstantFinalUtil.TOKENS_MAP.remove(token);
			}
		}
		ConstantFinalUtil.loggerMsg.info("----清理token结束---token数量:{}--",ConstantFinalUtil.TOKENS_MAP.size());
	}
}

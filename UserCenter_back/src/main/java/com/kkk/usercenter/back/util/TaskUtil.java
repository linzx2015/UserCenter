package com.kkk.usercenter.back.util;

import java.util.Date;
import java.util.Iterator;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.kkk.usercenter.common.util.ConstantFinalUtil;
import com.kkk.usercenter.common.util.DateFormatUtil;
import com.kkk.usercenter.users.pojo.AAdmins;

/**
 * 所有的任务调度
 * @author kkk
 * */
@Component("taskUtil")
public class TaskUtil
{
	/**
	 * 需要定时执行任务的方法
	 * 要配置spring任务调度中
	 * */
	public void runTask()
	{
		ConstantFinalUtil.loggerMsg.info("----任务调度开始----");
		this.cleanToken();
		ConstantFinalUtil.loggerMsg.info("----任务调度结束----");
	}
	/**
	 * 真正执行token清理的方法
	 * 
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

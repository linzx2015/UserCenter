package com.kkk.usercenter.outer.service;

import com.alibaba.fastjson.JSONObject;

public interface IOuterService
{
	/**
	 * 验证token是否有效,第一个版本
	 * @param reqJSON 上行的json字符串
	 * @return 下行的json字符串
	 * {
	 * 	  "version":"1",
	 *    "consume":"",	 //消耗的时间
	 *    "data":
	 *    	"admins":
	 *    	{
	 *    	}
	 * } 
	 * */
	JSONObject validator01AdminService(JSONObject reqJSON);
}

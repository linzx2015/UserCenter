package com.kkk.usercenter.head.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kkk.usercenter.common.controller.BaseController;
import com.kkk.usercenter.common.util.ConstantFinalUtil;
import com.kkk.usercenter.outer.service.IOuterService;
/**
 * 外部接口的Controller
 * @author kkk
 * */
@Controller
@RequestMapping("/outer")
public class OuterController extends BaseController
{
	@Resource
	private IOuterService outerService;
	/**
	 * 地区列表
	 * @return
	 * */
	@ResponseBody
	@RequestMapping(value="/regionList",produces="text/html;charset=UTF-8")
	public String regionList(HttpServletRequest request)
	{
		ConstantFinalUtil.loggerMsg.info("---regionList----");
		long start=System.currentTimeMillis();
		JSONObject resultJSON=new JSONObject();
		String json=request.getParameter("json");
		if(json==null)
		{
			json="";
		}
		
		JSONObject reqJSON=null;
		try
		{
			//将上行的json字符串转换成json对象
			reqJSON=(JSONObject) JSON.parse(json);
		} catch (Exception e)
		{
			resultJSON.put("code", "11");
			resultJSON.put("info", ConstantFinalUtil.INFO_JSON.getString("11"));
		} 
		
		try
		{
			if("1".equalsIgnoreCase(reqJSON.getString("version")))
			{
				resultJSON=this.outerService.regionList01Service(reqJSON);
			}else
			{
				resultJSON.put("version", reqJSON.getString("version"));
				resultJSON.put("code", "9");
				resultJSON.put("info", ConstantFinalUtil.INFO_JSON.getString("9"));
			}
		} catch (Exception e)
		{
			ConstantFinalUtil.loggerMsg.error("拼装json数据出错了,上行数据为:{}",reqJSON,e);
			resultJSON.put("code", "-1");
			resultJSON.put("info", ConstantFinalUtil.INFO_JSON.getString("-1"));
		}
		
		//统计处理耗时
		long end=System.currentTimeMillis();
		resultJSON.put("consume",(end-start)+"毫秒");
		return resultJSON+"";
	}
}

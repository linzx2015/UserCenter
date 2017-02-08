package com.kkk.usercenter.common.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;

import com.kkk.usercenter.common.util.DateFormatUtil;
import com.kkk.usercenter.common.util.VerifyCodeUtil;

//在父controller中没有注解,在子controller中有注解即可
public class BaseController
{
	@Resource 
	protected DateFormatUtil dateFormatUtil;
	//展示提示信息
	protected String info;
	/**
	 * 生成验证码的图片
	 * @param request
	 * @param response
	 * @return 
	 * @throws IOException
	 * */
	@RequestMapping("/randImg")
	public String randImg(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		//生成随机字符串
		String verifyCode=VerifyCodeUtil.generatorVerifyCode(4);
		//获取session,没有session则创建
		HttpSession session=request.getSession(true);
		//并将验证码存入session中
		session.setAttribute("rand", verifyCode.toLowerCase());
		
		//生成图片
		int width=200,height=80;
		VerifyCodeUtil.outputImage(width, height, response.getOutputStream(), verifyCode);
		return null;
	}
	//处理参数传值时用的map
	private Map<String,Object> paramMap=new HashMap<String,Object>();
	protected Map<String,Object> getParamMap()
	{
		this.paramMap.clear();
		return this.paramMap;
	}
}

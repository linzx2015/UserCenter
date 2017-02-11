package com.kkk.usercenter.back.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kkk.usercenter.common.util.ConstantFinalUtil;
import com.kkk.usercenter.users.pojo.AAdmins;

/**
 * 管理员登录的拦截器
 * @author kkk
 * */
@Component("authInterceptor")
public class AuthInterceptor extends HandlerInterceptorAdapter
{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		String info="";
		HttpSession session=request.getSession();
		AAdmins admin=(AAdmins) session.getAttribute("admin");
		if(admin!=null)
		{
			return true;
		}
		//提示非法登录
		info=ConstantFinalUtil.INFO_JSON.getString("12");
		session.setAttribute("info",info);
		//客户端跳转登录界面
		response.sendRedirect(request.getContextPath()+"/login.html");
		//返回false则不放行
		return false;
	}
}

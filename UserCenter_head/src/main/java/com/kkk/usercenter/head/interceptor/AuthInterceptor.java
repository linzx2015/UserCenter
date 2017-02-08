package com.kkk.usercenter.head.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.kkk.usercenter.common.util.ConstantFinalUtil;
import com.kkk.usercenter.users.pojo.AUsers;
/**
 * 前台网民的拦截器
 * @author kkk
 * */
@Component("authInterceptor")
public class AuthInterceptor extends HandlerInterceptorAdapter
{
	/**
	 * 检验用户是否存在
	 * 
	 * */
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		ConstantFinalUtil.loggerMsg.info("---preHandle----");
		String info="";
		HttpSession session=request.getSession();
		AUsers users=(AUsers) session.getAttribute("users");
		if(users!=null)
		{
			return true;
		}
		info=ConstantFinalUtil.INFO_JSON.getString("12");
		session.setAttribute("info", info);
		response.sendRedirect(request.getContextPath()+"/login.html");
		return false;
	}
	
}

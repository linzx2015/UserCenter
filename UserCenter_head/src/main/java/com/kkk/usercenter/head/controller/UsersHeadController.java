package com.kkk.usercenter.head.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.kkk.usercenter.common.controller.BaseController;
import com.kkk.usercenter.common.util.ConstantFinalUtil;

/**
 * 用户中心前台界面
 * 网名看到的
 * @author kkk
 * */
@Controller
@RequestMapping("/head/users")
public class UsersHeadController extends BaseController
{
	/**
	 * 前台用户登录后的首页
	 * @return String
	 * */
	@RequestMapping("/main")
	public String main()
	{
		ConstantFinalUtil.loggerMsg.info("---main----");
		return "/head/users/main";
	}
	
	/**
	 * 退出登录操作,移除user等
	 * @param request
	 * @param session
	 * @return String
	 * */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpSession session)
	{
		ConstantFinalUtil.loggerMsg.info("-----logout--------");
		session.removeAttribute("users");
		session.removeAttribute("lastLoginTime");
		//溢出token
		String token=request.getParameter("token");
		if(token==null)
		{
			token="";
		}
		if(!"".equalsIgnoreCase(token))
		{
			ConstantFinalUtil.TOKENS_MAP.remove(token);
		}
		this.info=ConstantFinalUtil.INFO_JSON.getString("8");
		session.setAttribute("info", info);
		return "redirect:/login.html";
	}
}

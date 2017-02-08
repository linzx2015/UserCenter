package com.kkk.usercenter.back.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.kkk.usercenter.common.controller.BaseController;
import com.kkk.usercenter.common.util.ConstantFinalUtil;
import com.kkk.usercenter.common.util.PageInfoUtil;
import com.kkk.usercenter.users.pojo.AAdmins;
import com.kkk.usercenter.users.service.IUserService;

/**
 * 管理员后台的操作
 * 
 * @author kkk
 */
@Controller
@RequestMapping("/back/admins/")
public class AdminBackController extends BaseController
{
	@Resource
	private IUserService userService;
	/**
	 * 登录后的首页
	 * 
	 * @return String
	 */
	@RequestMapping("/main")
	public String main()
	{
		ConstantFinalUtil.loggerMsg.info("-----main-----");
		return "/back/admins/main";
	}

	/**
	 * 左侧的菜单切换
	 * 
	 * @return String
	 * 
	 */
	@RequestMapping("/leftMenu")
	public String leftMenu()
	{
		ConstantFinalUtil.loggerMsg.info("------leftMenu-----");
		return "/back/admins/leftMenu";
	}

	/**
	 * 管理员列表
	 * @param request
	 * @return 
	 * */
	@RequestMapping("/adminList")
	public String adminList(HttpServletRequest request)
	{
		ConstantFinalUtil.loggerMsg.info("---adminList---");
		//查询条件
		Map<String,Object> paramMap=this.getParamMap();
		//分页工具
		PageInfoUtil pageInfoUtil=new PageInfoUtil();
		//查询对应的列表
		List<AAdmins> adminList=this.userService.queryMultiAdminsService(pageInfoUtil, paramMap);
		
		//将参数设置到作用域中
		request.setAttribute("adminList",adminList);
		request.setAttribute("pageInfoUtil", pageInfoUtil);
		return "/back/admins/adminList";
	}
	
	/**
	 * 退出登录操作
	 * 
	 * @return String
	 */
	@RequestMapping("/logout")
	public String loginOut(HttpSession session,HttpServletRequest request)
	{
		ConstantFinalUtil.loggerMsg.info("------logout------");
		// 将之前登录的admin从session删除
		session.removeAttribute("admins");
		session.removeAttribute("lastLoginTime");
		
		//移除token
		String token=request.getParameter("token");
		if(token==null)
		{
			token="";
		}
		if(!"".equalsIgnoreCase(token))
		{
			ConstantFinalUtil.TOKENS_MAP.remove(token);
		}
		//将操作信息放入作用域
		this.info = ConstantFinalUtil.INFO_JSON.getString("8");
		session.setAttribute("info", info);
		return "redirect:/login.html";
	}
}

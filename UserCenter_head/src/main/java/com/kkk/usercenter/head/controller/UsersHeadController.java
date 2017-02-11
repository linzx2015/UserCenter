package com.kkk.usercenter.head.controller;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.kkk.usercenter.common.controller.BaseController;
import com.kkk.usercenter.common.util.ConstantFinalUtil;
import com.kkk.usercenter.common.util.EncryptUtil;
import com.kkk.usercenter.users.pojo.AUsers;
import com.kkk.usercenter.users.service.IUserService;

/**
 * 用户中心前台界面
 * 网名看到的
 * @author kkk
 * */
@Controller
@RequestMapping("/head/users")
public class UsersHeadController extends BaseController
{
	@Resource 
	private IUserService userService;
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
	 * 用户更新预操作
	 * @param request
	 * @return
	 * */
	@RequestMapping("/usersUpdate")
	public String usersUpdate(HttpServletRequest request)
	{
		ConstantFinalUtil.loggerMsg.info("----usersUpdate-----");
		HttpSession session=request.getSession();
		AUsers users=(AUsers) session.getAttribute("users");
		
		//根据id查询用户
		Map<String,Object> paramMap=this.getParamMap();
		paramMap.put("id", users.getId());
		paramMap.put("regionExtend","true");
		users=this.userService.queryOneAusersService(paramMap);
		request.setAttribute("users", users);
		return "/head/users/usersUpdate";
	}
	
	/**
	 * 用户更新提交操作
	 * @param request
	 * @return 
	 * */
	@RequestMapping("/usersUpdateSubmit")
	public String usersUpdateSubmit(HttpServletRequest request)
	{
		ConstantFinalUtil.loggerMsg.info("---usersUpdateSubmit----");
		HttpSession session=request.getSession();
		AUsers users=(AUsers) session.getAttribute("users");
		
		//根据id查询用户
		Map<String,Object> paramMap=this.getParamMap();
		paramMap.put("id", users.getId());
		users=this.userService.queryOneAusersService(paramMap);
		
		//操作标识
		String operType=request.getParameter("operType");
		//断言
		boolean updateFlag=false;
		if("updateInfo".equalsIgnoreCase(operType))
		{
			//更新个人资料
			String qq=request.getParameter("qq");
			String address=request.getParameter("address");
			//用户所在的地区码
			String regionId=request.getParameter("regionId");
			
			users.setRegionId(Integer.valueOf(regionId));
			users.setQq(qq);
			users.setAddress(address);
			updateFlag=true;
		}else if("updatePass".equalsIgnoreCase(operType))
		{
			String souPass=request.getParameter("souPass");
			String password=request.getParameter("password");
			if(EncryptUtil.checkEncodeStr(souPass, users.getPassword()))
			{
				users.setPassword(EncryptUtil.encodeStr(souPass));
				updateFlag=true;
			}else
			{
				this.info=ConstantFinalUtil.INFO_JSON.getString("17");
			}
		}else
		{
			this.info=ConstantFinalUtil.INFO_JSON.getString("18");
		}
		if(updateFlag)
		{
			users.setUpdateTime(new Date());
			JSONObject resultJSON=this.userService.updateOneAuserService(users);
			this.info=resultJSON.getString("info");
		}
		request.setAttribute("info", this.info);
		return this.usersUpdate(request);
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

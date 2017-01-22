package com.kkk.usercenter.back.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kkk.usercenter.common.controller.BaseController;
import com.kkk.usercenter.common.util.ConstantFinalUtil;
import com.kkk.usercenter.users.pojo.AAdmins;
import com.kkk.usercenter.users.service.IUserService;

@Controller
public class NoLoginController extends BaseController
{
	@Resource
	private IUserService userService;
	
	/**
	 * 管理员列表
	 * @return 跳转的jsp名称
	 * */
	@RequestMapping("/adminList")
	public String adminList(HttpServletRequest request)
	{
		ConstantFinalUtil.loggerMsg.info("----adminList-数据展示----");
		Map<String,Object> paramMap=this.getParamMap();
		List<AAdmins> adminList=this.userService.queryMultiAdminsService(null, paramMap);
		request.setAttribute("adminList", adminList);
		return "/back/adminList";
	}
}

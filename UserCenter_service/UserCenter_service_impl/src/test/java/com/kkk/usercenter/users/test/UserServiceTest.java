package com.kkk.usercenter.users.test;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kkk.usercenter.common.test.BaseTest;
import com.kkk.usercenter.common.util.ConstantFinalUtil;
import com.kkk.usercenter.users.pojo.AAdmins;
import com.kkk.usercenter.users.service.IUserService;

public class UserServiceTest extends BaseTest
{
	@Resource
	private IUserService userService;
	@Before
	public void init()
	{
		super.init();
		ConstantFinalUtil.loggerMsg.info("--init--{}",this.ac);
		this.userService=(IUserService) this.ac.getBean("userService");
	}
	@Test
	public void queryOneAdminServiceTest()
	{
		Map<String,Object> paramMap=this.getParamMap();
		paramMap.put("id", "1");
		AAdmins admin=this.userService.queryOneAdminsService(paramMap);
		ConstantFinalUtil.loggerMsg.info("----admin---{}-",admin.getEmail());
	}
	@Test
	public void insertOneAdminServiceTest()
	{
		AAdmins admins = new AAdmins();
		admins.setEmail("11");
		admins.setPassword("11");
		admins.setTrueName("");
		admins.setPhone("qqqq");
		admins.setCreateTime(new Date());
		admins.setUpdateTime(new Date());
		admins.setLastLoginTime(new Date());
		JSONObject resultJson=this.userService.insertOneAdminService(admins);
		ConstantFinalUtil.loggerMsg.info("--插入一条记录-{}-",resultJson);
	}
	@Test
	public void updateOneAdminServiceTest()
	{
		Map<String,Object> paramMap=this.getParamMap();
		paramMap.put("id", "1");
		AAdmins admin=this.userService.queryOneAdminsService(paramMap);
		admin.setEmail("aaaaa");
		JSONObject resultJson=this.userService.updateOneAdminService(admin);
		ConstantFinalUtil.loggerMsg.info("--更新一条记录--{}-",resultJson);
	}
	@Test
	public void deleteOneAdminServiceTest()
	{
		Map<String,Object> paramMap=this.getParamMap();
		paramMap.put("id", "2");
		AAdmins admin=this.userService.queryOneAdminsService(paramMap);
		JSONObject resultJson=this.userService.deleteOneAdminService(admin);
		ConstantFinalUtil.loggerMsg.info("--删除一条记录-{}-",resultJson);
	}
}

package com.kkk.usercenter.users.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.kkk.usercenter.common.util.PageInfoUtil;
import com.kkk.usercenter.users.pojo.AAdmins;

public interface IUserService
{
	//所有实现类都需要用到
    JSONObject resultJson=new JSONObject();
	JSONObject dataJson=new JSONObject();
	/* 管理员服务开始 */
	/**
	 * 插入一个管理员服务
	 * 
	 * @param AAdmins
	 * @return JSONObject
	 */
	JSONObject insertOneAdminService(AAdmins admin);

	/**
	 * 更新一个管理员服务
	 * 
	 * @param AAdmins
	 * @return JSONObject
	 */
	JSONObject updateOneAdminService(AAdmins admin);

	/**
	 * 删除一个管理员服务
	 * 
	 * @param AAdmins
	 * @return JSONObject
	 */
	JSONObject deleteOneAdminService(AAdmins admin);

	/**
	 * 查询一个管理员服务
	 * 
	 * @param Map
	 * @return AAdmins
	 */
	AAdmins queryOneAdminsService(Map<String, Object> paramMap);

	/**
	 * 查询多个管理员服务
	 * @param PageInfoUtil 为null不分页,否则进行分页
	 * @param Map
	 * @return List<AAdmins>
	 */
	List<AAdmins> queryMultiAdminsService(PageInfoUtil pageInfoUtil, Map<String, Object> paramMap);
	/* 管理员服务结束 */
}

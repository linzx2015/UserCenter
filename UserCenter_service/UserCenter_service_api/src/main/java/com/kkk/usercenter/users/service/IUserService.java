package com.kkk.usercenter.users.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.kkk.usercenter.common.util.PageInfoUtil;
import com.kkk.usercenter.users.pojo.AAdmins;
import com.kkk.usercenter.users.pojo.ARole;
import com.kkk.usercenter.users.pojo.AUsers;
import com.kkk.usercenter.users.pojo.AUsersExtend;

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
	
	/* 角色服务开始 */
	/**
	 * 插入一个角色服务
	 * 
	 * @param ARole
	 * @return JSONObject
	 */
	JSONObject insertOneAroleService(ARole role);

	/**
	 * 更新一个角色服务
	 * 
	 * @param ARole
	 * @return JSONObject
	 */
	JSONObject updateOneAroleService(ARole role);

	/**
	 * 删除一个角色服务
	 * 
	 * @param ARole
	 * @return JSONObject
	 */
	JSONObject deleteOneAroleService(ARole role);

	/**
	 * 查询一个角色服务
	 * 
	 * @param Map
	 * @return ARole
	 */
	ARole queryOneARolesService(Map<String, Object> paramMap);

	/**
	 * 查询多个角色服务
	 * @param PageInfoUtil 为null不分页,否则进行分页
	 * @param Map
	 * @return List<ARole>
	 */
	List<ARole> queryMultiARolesService(PageInfoUtil pageInfoUtil, Map<String, Object> paramMap);
	/* 角色服务结束 */
	
	/* 用户服务开始 */
	/**
	 * 插入一个用户服务
	 * 
	 * @param AUsers
	 * @return JSONObject
	 */
	JSONObject insertOneAuserService(AUsers user);

	/**
	 * 更新一个用户服务
	 * 
	 * @param AUsers
	 * @return JSONObject
	 */
	JSONObject updateOneAuserService(AUsers user);

	/**
	 * 删除一个用户服务
	 * 
	 * @param AUsers
	 * @return JSONObject
	 */
	JSONObject deleteOneAuserService(AUsers user);

	/**
	 * 查询一个用户服务
	 * 
	 * @param Map
	 * @return AUsers
	 */
	AUsers queryOneAusersService(Map<String, Object> paramMap);

	/**
	 * 查询多个用户服务
	 * @param PageInfoUtil 为null不分页,否则进行分页
	 * @param Map
	 * @return List<AUsers>
	 */
	List<AUsers> queryMultiAusersService(PageInfoUtil pageInfoUtil, Map<String, Object> paramMap);
	/* 用户服务结束 */
	
	/* 用户扩展服务开始 */
	/**
	 * 插入一个用户扩展表服务
	 * 
	 * @param AUsersExtend
	 * @return JSONObject
	 */
	JSONObject insertOneAUsersExtendService(AUsersExtend userExtend);

	/**
	 * 更新一个用户扩展表服务
	 * 
	 * @param AUsersExtend
	 * @return JSONObject
	 */
	JSONObject updateOneAUsersExtendService(AUsersExtend userExtend);

	/**
	 * 删除一个用户扩展表服务
	 * 
	 * @param AUsersExtend
	 * @return JSONObject
	 */
	JSONObject deleteOneAUsersExtendService(AUsersExtend userExtend);

	/**
	 * 查询一个用户扩展表服务
	 * 
	 * @param Map
	 * @return AUsersExtend
	 */
	AUsersExtend queryOneAUsersExtendService(Map<String, Object> paramMap);

	/**
	 * 查询多个用户扩展表服务
	 * @param PageInfoUtil 为null不分页,否则进行分页
	 * @param Map
	 * @return List<AUsersExtend>
	 */
	List<AUsersExtend> queryMultiAUsersExtendService(PageInfoUtil pageInfoUtil, Map<String, Object> paramMap);
	/* 用户扩展服务结束 */
}

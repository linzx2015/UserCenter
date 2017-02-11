package com.kkk.usercenter.users.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.kkk.usercenter.common.util.ConstantFinalUtil;
import com.kkk.usercenter.common.util.PageInfoUtil;
import com.kkk.usercenter.users.dao.IARoleDao;
import com.kkk.usercenter.users.dao.IAUsersDao;
import com.kkk.usercenter.users.dao.IAUsersExtendDao;
import com.kkk.usercenter.users.dao.IAdminDao;
import com.kkk.usercenter.users.pojo.AAdmins;
import com.kkk.usercenter.users.pojo.ARole;
import com.kkk.usercenter.users.pojo.AUsers;
import com.kkk.usercenter.users.pojo.AUsersExtend;
import com.kkk.usercenter.users.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService
{
	@Resource
	private IAdminDao adminDao;
	@Resource
	private IARoleDao roleDao;
	@Resource 
	private IAUsersDao userDao;
	@Resource
	private IAUsersExtendDao userExtendDao;
	
	/*  管理员服务实现开始    */
	/**
	 * 插入一条管理员记录
	 * JSONObject 格式{code:"1",info:"添加成功",data:{effectRows:"11","id":"11"}}
	 * */
	@Override
	public JSONObject insertOneAdminService(AAdmins admin)
	{
		this.resultJson.clear();
		int res=this.adminDao.insertOne(admin);
//		String str=null;
//		if(str.equals("aa"))
//		{
//		}
		if(res>0)
		{
			this.resultJson.put("code", "0");
			this.resultJson.put("info", ConstantFinalUtil.INFO_JSON.get("0"));
			this.dataJson.clear();
			this.dataJson.put("id", admin.getId());
			this.dataJson.put("effectRows", res);
			this.resultJson.put("data", this.dataJson);
		}else
		{
			this.resultJson.put("code", "1");
			this.resultJson.put("info", ConstantFinalUtil.INFO_JSON.get("1"));
		}
		return this.resultJson;
	}

	@Override
	public JSONObject updateOneAdminService(AAdmins admin)
	{
		this.resultJson.clear();
		int res=this.adminDao.updateOne(admin);
		if(res>0)
		{
			this.resultJson.put("code", "0");
			this.resultJson.put("info", ConstantFinalUtil.INFO_JSON.get("0"));
			this.dataJson.clear();
			this.dataJson.put("id", admin.getId());
			this.dataJson.put("effectRows", res);
			this.resultJson.put("data", this.dataJson);
		}else
		{
			this.resultJson.put("code", "2");
			this.resultJson.put("info", ConstantFinalUtil.INFO_JSON.get("2"));
		}
		return this.resultJson;
	}

	@Override
	public JSONObject deleteOneAdminService(AAdmins admin)
	{
		this.resultJson.clear();
		int res=this.adminDao.deleteOne(admin);
		if(res>0)
		{
			this.resultJson.put("code", "0");
			this.resultJson.put("info", ConstantFinalUtil.INFO_JSON.get("0"));
			this.dataJson.clear();
			this.dataJson.put("id", admin.getId());
			this.dataJson.put("effectRows", res);
			this.resultJson.put("data", this.dataJson);
		}else
		{
			this.resultJson.put("code", "3");
			this.resultJson.put("info", ConstantFinalUtil.INFO_JSON.get("3"));
		}
		return this.resultJson;
	}

	@Override
	public AAdmins queryOneAdminsService(Map<String, Object> paramMap)
	{
		return this.adminDao.queryOne(paramMap);
	}

	@Override
	public List<AAdmins> queryMultiAdminsService(PageInfoUtil pageInfoUtil, Map<String, Object> paramMap)
	{
		//模糊查询的拼接由后台进行控制,避免用户乱输入
		if(paramMap.get("keywords")!=null)
		{
			paramMap.put("keywords","%"+paramMap.get("keywords")+"%");
		}
		//进行分页
		if(pageInfoUtil!=null)
		{
			//先查询全部记录
			paramMap.put("pageCond", "true");
			List<AAdmins> adminList=this.adminDao.queryMulti(paramMap);
			//查出总记录数
			if(adminList.size()==1)
			{
				//总记录数已经存放在id中
				pageInfoUtil.setTotalRecord(adminList.get(0).getId());
			}
			paramMap.put("pageCond", "false");
			paramMap.put("currentRecord", pageInfoUtil.getCurrentRecord());
			paramMap.put("pageSize", pageInfoUtil.getPageSize());
			return this.adminDao.queryMulti(paramMap);
		}
			return this.adminDao.queryMulti(paramMap);
	}
	/*  管理员服务实现结束   */

	/*  角色服务实现开始    */
	/**
	 * 插入一条角色记录
	 * JSONObject 格式{code:"1",info:"添加成功",data:{effectRows:"11","id":"11"}}
	 * */
	@Override
	public JSONObject insertOneAroleService(ARole role)
	{
		this.resultJson.clear();
		int res=this.roleDao.insertOne(role);
//		String str=null;
//		if(str.equals("aa"))
//		{
//		}
		if(res>0)
		{
			this.resultJson.put("code", "0");
			this.resultJson.put("info", ConstantFinalUtil.INFO_JSON.get("0"));
			this.dataJson.clear();
			this.dataJson.put("id", role.getId());
			this.dataJson.put("effectRows", res);
			this.resultJson.put("data", this.dataJson);
		}else
		{
			this.resultJson.put("code", "1");
			this.resultJson.put("info", ConstantFinalUtil.INFO_JSON.get("1"));
		}
		return this.resultJson;
	}

	@Override
	public JSONObject updateOneAroleService(ARole role)
	{
		this.resultJson.clear();
		int res=this.roleDao.updateOne(role);
		if(res>0)
		{
			this.resultJson.put("code", "0");
			this.resultJson.put("info", ConstantFinalUtil.INFO_JSON.get("0"));
			this.dataJson.clear();
			this.dataJson.put("id", role.getId());
			this.dataJson.put("effectRows", res);
			this.resultJson.put("data", this.dataJson);
		}else
		{
			this.resultJson.put("code", "2");
			this.resultJson.put("info", ConstantFinalUtil.INFO_JSON.get("2"));
		}
		return this.resultJson;
	}

	@Override
	public JSONObject deleteOneAroleService(ARole role)
	{
		this.resultJson.clear();
		int res=this.roleDao.deleteOne(role);
		if(res>0)
		{
			this.resultJson.put("code", "0");
			this.resultJson.put("info", ConstantFinalUtil.INFO_JSON.get("0"));
			this.dataJson.clear();
			this.dataJson.put("id", role.getId());
			this.dataJson.put("effectRows", res);
			this.resultJson.put("data", this.dataJson);
		}else
		{
			this.resultJson.put("code", "3");
			this.resultJson.put("info", ConstantFinalUtil.INFO_JSON.get("3"));
		}
		return this.resultJson;
	}

	@Override
	public ARole queryOneARolesService(Map<String, Object> paramMap)
	{
		return this.roleDao.queryOne(paramMap);
	}

	@Override
	public List<ARole> queryMultiARolesService(PageInfoUtil pageInfoUtil, Map<String, Object> paramMap)
	{
		//模糊查询的拼接由后台进行控制,避免用户乱输入
		if(paramMap.get("keywords")!=null)
		{
			paramMap.put("keywords","%"+paramMap.get("keywords")+"%");
		}
		//进行分页
		if(pageInfoUtil!=null)
		{
			//先查询全部记录
			paramMap.put("pageCond", "true");
			List<ARole> roleList=this.roleDao.queryMulti(paramMap);
			//查出总记录数
			if(roleList.size()==1)
			{
				//总记录数已经存放在id中
				pageInfoUtil.setTotalRecord(roleList.get(0).getId());
			}
			paramMap.put("pageCond", "false");
			paramMap.put("currentRecord", pageInfoUtil.getCurrentRecord());
			paramMap.put("pageSize", pageInfoUtil.getPageSize());
			return this.roleDao.queryMulti(paramMap);
		}
			return this.roleDao.queryMulti(paramMap);
	}
	/*  角色服务实现结束   */
	
	/*  用户服务实现开始    */
	/**
	 * 插入一条用户记录
	 * JSONObject 格式{code:"1",info:"添加成功",data:{effectRows:"11","id":"11"}}
	 * */
	@Override
	public JSONObject insertOneAuserService(AUsers user)
	{
		this.resultJson.clear();
		//检查邮箱不能重复
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("email", user.getEmail());
		AUsers userRes=this.queryOneAusersService(paramMap);
		if(userRes!=null)
		{
			resultJson.put("code","16");
			resultJson.put("info", ConstantFinalUtil.INFO_JSON.getString("16"));
			return resultJson;
		}
		//操作条数
		int res=this.userDao.insertOne(user);
		if(res>0)
		{
			//用户扩展表,如果为空,则new一个;否则直接使用
			AUsersExtend usersExtend=user.getUsersExtend();
			if(usersExtend==null)
			{
				usersExtend=new AUsersExtend();
				usersExtend.setUsersId(user.getId());
				//设置user关联用户扩展表
				user.setUsersExtend(usersExtend);
			}
			usersExtend.setCreateTime(new Date());
			usersExtend.setUpdateTime(new Date());
			
			this.resultJson.put("code", "0");
			this.resultJson.put("info", ConstantFinalUtil.INFO_JSON.get("0"));
			this.dataJson.clear();
			this.dataJson.put("id", user.getId());
			this.dataJson.put("effectRows", res);
			this.resultJson.put("data", this.dataJson);
		}else
		{
			this.resultJson.put("code", "1");
			this.resultJson.put("info", ConstantFinalUtil.INFO_JSON.get("1"));
		}
		return this.resultJson;
	}

	@Override
	public JSONObject updateOneAuserService(AUsers user)
	{
		this.resultJson.clear();
		int res=this.userDao.updateOne(user);
		if(res>0)
		{
			this.resultJson.put("code", "0");
			this.resultJson.put("info", ConstantFinalUtil.INFO_JSON.get("0"));
			//更新关联表,注意此处不能直接调用userdao执行操作
			if(user.getUsersExtend()!=null)
			{
				this.updateOneAUsersExtendService(user.getUsersExtend());
			}
			this.dataJson.clear();
			this.dataJson.put("id", user.getId());
			this.dataJson.put("effectRows", res);
			this.resultJson.put("data", this.dataJson);
		}else
		{
			this.resultJson.put("code", "2");
			this.resultJson.put("info", ConstantFinalUtil.INFO_JSON.get("2"));
		}
		return this.resultJson;
	}

	@Override
	public JSONObject deleteOneAuserService(AUsers user)
	{
		this.resultJson.clear();
		int res=this.userDao.deleteOne(user);
		if(res>0)
		{
			this.resultJson.put("code", "0");
			this.resultJson.put("info", ConstantFinalUtil.INFO_JSON.get("0"));
			this.dataJson.clear();
			this.dataJson.put("id", user.getId());
			this.dataJson.put("effectRows", res);
			this.resultJson.put("data", this.dataJson);
		}else
		{
			this.resultJson.put("code", "3");
			this.resultJson.put("info", ConstantFinalUtil.INFO_JSON.get("3"));
		}
		return this.resultJson;
	}

	@Override
	public AUsers queryOneAusersService(Map<String, Object> paramMap)
	{
		return this.userDao.queryOne(paramMap);
	}

	@Override
	public List<AUsers> queryMultiAusersService(PageInfoUtil pageInfoUtil, Map<String, Object> paramMap)
	{
		//模糊查询的拼接由后台进行控制,避免用户乱输入
		if(paramMap.get("keywords")!=null)
		{
			paramMap.put("keywords","%"+paramMap.get("keywords")+"%");
		}
		//进行分页
		if(pageInfoUtil!=null)
		{
			//先查询全部记录
			paramMap.put("pageCond", "true");
			List<AUsers> roleList=this.userDao.queryMulti(paramMap);
			//查出总记录数
			if(roleList.size()==1)
			{
				//总记录数已经存放在id中
				pageInfoUtil.setTotalRecord(roleList.get(0).getId());
			}
			paramMap.put("pageCond", "false");
			paramMap.put("currentRecord", pageInfoUtil.getCurrentRecord());
			paramMap.put("pageSize", pageInfoUtil.getPageSize());
			return this.userDao.queryMulti(paramMap);
		}
			return this.userDao.queryMulti(paramMap);
	}
	/*  用户服务实现结束   */
	
	/*  用户扩展表服务实现开始    */
	/**
	 * 插入一条用户扩展表记录
	 * JSONObject 格式{code:"1",info:"添加成功",data:{effectRows:"11","id":"11"}}
	 * */
	@Override
	public JSONObject insertOneAUsersExtendService(AUsersExtend userExtend)
	{
		this.resultJson.clear();
		int res=this.userExtendDao.insertOne(userExtend);
//		String str=null;
//		if(str.equals("aa"))
//		{
//		}
		if(res>0)
		{
			this.resultJson.put("code", "0");
			this.resultJson.put("info", ConstantFinalUtil.INFO_JSON.get("0"));
			this.dataJson.clear();
			this.dataJson.put("id", userExtend.getId());
			this.dataJson.put("effectRows", res);
			this.resultJson.put("data", this.dataJson);
		}else
		{
			this.resultJson.put("code", "1");
			this.resultJson.put("info", ConstantFinalUtil.INFO_JSON.get("1"));
		}
		return this.resultJson;
	}

	@Override
	public JSONObject updateOneAUsersExtendService(AUsersExtend userExtend)
	{
		this.resultJson.clear();
		int res=this.userExtendDao.updateOne(userExtend);
		if(res>0)
		{
			this.resultJson.put("code", "0");
			this.resultJson.put("info", ConstantFinalUtil.INFO_JSON.get("0"));
			this.dataJson.clear();
			this.dataJson.put("id", userExtend.getId());
			this.dataJson.put("effectRows", res);
			this.resultJson.put("data", this.dataJson);
		}else
		{
			this.resultJson.put("code", "2");
			this.resultJson.put("info", ConstantFinalUtil.INFO_JSON.get("2"));
		}
		return this.resultJson;
	}

	@Override
	public JSONObject deleteOneAUsersExtendService(AUsersExtend userExtend)
	{
		this.resultJson.clear();
		int res=this.userExtendDao.deleteOne(userExtend);
		if(res>0)
		{
			this.resultJson.put("code", "0");
			this.resultJson.put("info", ConstantFinalUtil.INFO_JSON.get("0"));
			this.dataJson.clear();
			this.dataJson.put("id", userExtend.getId());
			this.dataJson.put("effectRows", res);
			this.resultJson.put("data", this.dataJson);
		}else
		{
			this.resultJson.put("code", "3");
			this.resultJson.put("info", ConstantFinalUtil.INFO_JSON.get("3"));
		}
		return this.resultJson;
	}

	@Override
	public AUsersExtend queryOneAUsersExtendService(Map<String, Object> paramMap)
	{
		return this.userExtendDao.queryOne(paramMap);
	}

	@Override
	public List<AUsersExtend> queryMultiAUsersExtendService(PageInfoUtil pageInfoUtil, Map<String, Object> paramMap)
	{
		//模糊查询的拼接由后台进行控制,避免用户乱输入
		if(paramMap.get("keywords")!=null)
		{
			paramMap.put("keywords","%"+paramMap.get("keywords")+"%");
		}
		//进行分页
		if(pageInfoUtil!=null)
		{
			//先查询全部记录
			paramMap.put("pageCond", "true");
			List<AUsersExtend> adminList=this.userExtendDao.queryMulti(paramMap);
			//查出总记录数
			if(adminList.size()==1)
			{
				//总记录数已经存放在id中
				pageInfoUtil.setTotalRecord(adminList.get(0).getId());
			}
			paramMap.put("pageCond", "false");
			paramMap.put("currentRecord", pageInfoUtil.getCurrentRecord());
			paramMap.put("pageSize", pageInfoUtil.getPageSize());
			return this.userExtendDao.queryMulti(paramMap);
		}
			return this.userExtendDao.queryMulti(paramMap);
	}
	/*  用户扩展表服务实现结束   */
}

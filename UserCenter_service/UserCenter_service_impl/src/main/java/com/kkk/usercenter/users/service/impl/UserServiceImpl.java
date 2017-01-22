package com.kkk.usercenter.users.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.kkk.usercenter.common.util.ConstantFinalUtil;
import com.kkk.usercenter.common.util.PageInfoUtil;
import com.kkk.usercenter.users.dao.IAdminDao;
import com.kkk.usercenter.users.pojo.AAdmins;
import com.kkk.usercenter.users.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService
{
	@Resource
	private IAdminDao adminDao;
	
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
}

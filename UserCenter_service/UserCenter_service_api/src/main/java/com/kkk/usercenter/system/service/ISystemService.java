package com.kkk.usercenter.system.service;

import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
import com.kkk.usercenter.common.util.PageInfoUtil;
import com.kkk.usercenter.system.pojo.ARegion;

/**
 * 系统模块的service
 * 
 * @author kkk
 */
public interface ISystemService
{
	/*----- 全国地区模块管理开始 -----*/
	/**
	 * 插入一条地区记录
	 * 
	 * @param region
	 * @return JSONObject
	 */
	JSONObject insertOneAregionService(ARegion region);

	/**
	 * 更新一条地区记录
	 * 
	 * @param region
	 * @return JSONObject
	 */
	JSONObject updateOneAregionService(ARegion region);

	/**
	 * 删除一条地区记录
	 * 
	 * @param region
	 * @return JSONObject
	 */
	JSONObject deleteOneAregionService(ARegion region);

	/**
	 * 查询一条地区记录
	 * 
	 * @param paramMap
	 * @return ARegion
	 */
	ARegion queryOneAregionService(Map<String, Object> paramMap);

	/**
	 * 查询多条地区记录
	 * 
	 * @param pageInfoUtil
	 * @param paramMap
	 * @return List<ARegion>
	 */
	List<ARegion> queryMultiAregionService(PageInfoUtil pageInfoUtil, Map<String, Object> paramMap);

	/**
	 * 根据当前地区节点查询对应的字符串描述
	 * 一级节点-->二级节点-->三级节点
	 * @param region
	 * @return
	 */
	List<ARegion> queryTreeRegionService(ARegion regionS);
}

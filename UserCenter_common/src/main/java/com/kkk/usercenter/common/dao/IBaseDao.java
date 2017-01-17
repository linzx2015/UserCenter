package com.kkk.usercenter.common.dao;

import java.util.List;
import java.util.Map;

public interface IBaseDao<T>
{
	/**
	 * 插入一条记录
	 * 
	 * @param T
	 * @return int
	 */
	int insertOne(T t);

	/**
	 * 更新一条记录
	 * 
	 * @param T
	 * @return int
	 */
	int updateOne(T t);

	/**
	 * 删除一条记录
	 * 
	 * @param T
	 * @return int
	 */
	int deleteOne(T t);

	/**
	 * 查询一条记录
	 * 
	 * @param Map
	 * @return T
	 */
	T queryOne(Map<String, Object> paramMap);

	/**
	 * 查询多条记录
	 * 
	 * @param Map
	 * @return List<T>
	 */
	List<T> queryMulti(Map<String, Object> paramMap);
}

package com.kkk.usercenter.common.util;

import java.sql.Date;
import javax.annotation.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * redis缓存的工具类
 * @author kkk
 * */
@Component("redisUtil")
public class RedisUtil
{
	//注意此处redisTemplate跟applicationContext_redis.xml的bean id一致
	@Resource
	private RedisTemplate redisTemplate;
	/**
	 * 往reids里面存东西
	 * @param key 要合理存放数据
	 * @param valueJSON json中必须包含创建时间
	 * @return boolean
	 * */
	public boolean put(final String key,final String valueJSON)
	{
		try
		{
			return (boolean) this.redisTemplate.execute(new RedisCallback()
			{

				@Override
				public Object doInRedis(RedisConnection connection) throws DataAccessException
				{
					byte[] keyBye=redisTemplate.getStringSerializer().serialize(key);
					connection.set(keyBye,redisTemplate.getStringSerializer().serialize(valueJSON));
					return true;
				}
			});
		} catch (Exception e)
		{
			ConstantFinalUtil.loggerMsg.error("-向redis中存放数据失败了,键为{}--",key,e);
		}
		return false;
	}
	
	/**
	 * 往reids里面存东西
	 * @param key 要合理存放数据
	 * @param valueJSON json中必须包含创建时间
	 * @param timeOut 过期时间,单位是秒
	 * @return boolean
	 * */
	public boolean put(final String key,final String valueJSON,final int timeOut)
	{
		try
		{
			return (boolean) this.redisTemplate.execute(new RedisCallback()
			{

				@Override
				public Object doInRedis(RedisConnection connection) throws DataAccessException
				{
					byte[] keyByte=redisTemplate.getStringSerializer().serialize(key);
					connection.set(keyByte, redisTemplate.getStringSerializer().serialize(valueJSON));
					//设置一下过期时间
					boolean flag=connection.expire(keyByte,timeOut);
					if(!flag)
					{
						ConstantFinalUtil.loggerMsg.error(key+"--"+timeOut+",redis设置过时时间(毫秒)失败了--"+valueJSON);
					}
					return flag;
				}
			});
		} catch (Exception e)
		{
			ConstantFinalUtil.loggerMsg.error("-向redis中存放数据失败了,键为{}--",key,e);
		}
		return false;
	}
	/**
	 * 往reids里面存东西
	 * @param key 要合理存放数据
	 * @param valueJSON json中必须包含创建时间
	 * @param expDate 旋转的是指定的日期过期
	 * @return boolean
	 * */
	public boolean put(final String key,final String valueJSON,final Date expDate)
	{
		try
		{
			return (boolean) this.redisTemplate.execute(new RedisCallback()
			{

				@Override
				public Object doInRedis(RedisConnection connection) throws DataAccessException
				{
					byte[] keyByte=redisTemplate.getStringSerializer().serialize(key);
					connection.set(keyByte, redisTemplate.getStringSerializer().serialize(valueJSON));
					//设置一下过期时间
					boolean flag=connection.expireAt(keyByte, expDate.getTime());
					if(!flag)
					{
						ConstantFinalUtil.loggerMsg.error(key+"--"+expDate.toLocaleString()+",redis设置过时时间(毫秒)失败了--"+valueJSON);
					}
					return flag;
				}
			});
		} catch (Exception e)
		{
			ConstantFinalUtil.loggerMsg.error("-向redis中存放数据失败了,键为{}--",key,e);
		}
		return false;
	}
	
	/**
	 * 从redis中取数据
	 * @param key    
	 * @return 会返回null
	 * */
	public Object get(final String key)
	{
		try
		{
			return this.redisTemplate.execute(new RedisCallback()
			{

				@Override
				public Object doInRedis(RedisConnection connection) throws DataAccessException
				{
					byte[] keyByte=redisTemplate.getStringSerializer().serialize(key);
					if(connection.exists(keyByte))
					{
						byte[] valueByte=connection.get(keyByte);
						return redisTemplate.getStringSerializer().deserialize(valueByte);
					}
					return null;
				}
			});
		} catch (Exception e)
		{
			ConstantFinalUtil.loggerMsg.error("-从redis中读取数据报错了,键为:{}-"+key,e);
		}
		return null;
	}
	/**
	 * 删除数据
	 * @param key
	 * @return 返回删除的条数 -1为键不存在
	 * */
	public Object delete(final String key)
	{
		try
		{
			return redisTemplate.execute(new RedisCallback()
			{

				@Override
				public Object doInRedis(RedisConnection connection) throws DataAccessException
				{
					//先系列化,然后再查找是否有存在
					byte[] keyByte=redisTemplate.getStringSerializer().serialize(key);
					//存在该键,则进行删除操作
					if(connection.exists(keyByte))
					{
						long keyLon=connection.del(keyByte);
						return keyLon;
					}
					return null;
				}
			});
		} catch (Exception e)
		{
			ConstantFinalUtil.loggerMsg.error("-从redis中删除数据失败了,键为{}-",key,e);
		}
		return -1;
	}
}

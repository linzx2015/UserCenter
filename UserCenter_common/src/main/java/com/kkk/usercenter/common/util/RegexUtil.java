package com.kkk.usercenter.common.util;

import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
/**
 * 进行字符串匹配的工具类
 * @author kkk
 * */
@Component("regexUtil")
public class RegexUtil
{
	//正则匹配的字符串
	private String pattern_$="(\\$\\{)(\\w+)(\\})";
	/**
	 * 用于生成随机数
	 * @param length生成的长度
	 * @return 生成的字符串
	 * */
	public String productCode(int length)
	{
		StringBuffer sb=new StringBuffer();
		Random random=new Random();
		for(int i=0;i<length;i++)
		{
			int index=random.nextInt(ConstantFinalUtil.ALLSTR.length());
			char ch=ConstantFinalUtil.ALLSTR.charAt(index);
			sb.append(ch);
		}
		return sb.toString();
	}
	/**
	 * 将字符串中的${},替换成map中的值,${}中的值被替换
	 * @param sourceStr test_${email}sss 
	 * @param map<String,Object> key是email,value是kkk@qq.com
	 * @return test_kkk@qq.comsss
	 * */
	public String replace$(String sourceStr,Map<String,String> paramMap)
	{
		StringBuffer sb=new StringBuffer();
		//通过compile方法得到Pattern对象
		Pattern pattern=Pattern.compile(pattern_$);
		//将字符串进行正则匹配
		Matcher matcher=pattern.matcher(sourceStr);
		//循环遍历字符串
		while(matcher.find())
		{
			//取第二组元素
			String res=matcher.group(2);
			if(paramMap.get(res)!=null)
			{
				//取出map中的值,将matcher中的值替换
				matcher.appendReplacement(sb, paramMap.get(res));
			}
		}
		//将尾巴放到字符串
		matcher.appendTail(sb);
		return sb.toString();
	}
	
}

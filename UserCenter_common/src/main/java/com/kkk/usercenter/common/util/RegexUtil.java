package com.kkk.usercenter.common.util;

import java.util.Random;

public class RegexUtil
{
	//用于生成随机数的字符串
	private String codeStr="abcdefghijklmnopqrstuvwxyz0123456789";
	
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
			int index=random.nextInt(codeStr.length());
			char ch=codeStr.charAt(index);
			sb.append(ch);
		}
		return sb.toString();
	}
}

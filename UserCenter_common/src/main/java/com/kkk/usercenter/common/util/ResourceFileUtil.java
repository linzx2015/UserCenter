package com.kkk.usercenter.common.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourceFileUtil
{
	//读取配置文件
	public String readFile(String sourceFilePath)
	{
		try
		{
			return	this.readFile(new FileInputStream(sourceFilePath));
		} catch (FileNotFoundException e)
		{
			ConstantFinalUtil.loggerMsg.error("--文件不存在,读取源配置文件失败",e);
		}
		return "";
	}
	/**
	 * 读取配置文件
	 * @param inputStream
	 * @retun String
	 * */
	public String readFile(InputStream in)
	{
		StringBuffer sb=new StringBuffer();
		BufferedReader br=null;
		try
		{
			br=new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String line="";
			while((line=br.readLine())!=null)
			{
				//对得到的行,进行去头尾空字符串
				line=line.trim();
				if("".equalsIgnoreCase(line) || line.startsWith("//"))
				{
					continue;
				}
				sb.append(line);
			}
		} catch (Exception e)
		{
			ConstantFinalUtil.loggerMsg.error("--读取json配置文件失败:",e);
		}finally
		{
			if(br!=null)
			{
				try
				{
					br.close();
				} catch (IOException e)
				{
					ConstantFinalUtil.loggerMsg.error("--关闭br流失败",e);
				}
			}
		}
		return sb.toString();
	}
	public static void main(String[] args)
	{
		//在windows中\需要转义,/则不需要转义
		String sourceFilePath="D:/MainSoftWare/eclipse/Mybatis/UserCenter/UserCenter_common/src/main/resources/resourceInfo.json";
		ResourceFileUtil rfu=new ResourceFileUtil();
		String resultInfo=rfu.readFile(sourceFilePath);
		ConstantFinalUtil.loggerMsg.info("--读取到的json配置信息-{}",resultInfo);
	}
}

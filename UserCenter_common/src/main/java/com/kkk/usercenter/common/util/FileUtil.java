package com.kkk.usercenter.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;


/**
 * 文件读写的工具类
 * @author kkk
 * */
@Component("fileUtil")
public class FileUtil
{
	@Resource
	private RegexUtil regexUtil;
	
	/**
	 * 通过文件对象读取文件
	 * @param sourceFile
	 * @Return String
	 * */
	public String readFile(File file)
	{
		try
		{
			return this.readFileStream(new FileInputStream(file));
		} catch (FileNotFoundException e)
		{
			ConstantFinalUtil.loggerMsg.error("--通过文件流读取文件失败-{}-",e);
		}
		return "";
	}
	
	/**
	 * 通过inputStream读取文件
	 * @param in
	 * @return String
	 * */
	public String readFileStream(InputStream in)
	{
		StringBuffer sb=new StringBuffer();
		BufferedReader br=null;
		try
		{
			br=new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String line="";
			while((line=br.readLine())!=null)
			{
				line=line.trim();
				//处理注释
				if("".equalsIgnoreCase(line)||line.startsWith("//"))
				{
					continue;
				}
				sb.append(line+"\r\n");
			}
		} catch (IOException e)
		{
			ConstantFinalUtil.loggerMsg.error("--读取文件流失败--:",e);
		}finally
		{
			if(br!=null)
			{
				try
				{
					br.close();
				} catch (IOException e)
				{
					ConstantFinalUtil.loggerMsg.info("--IO流关闭失败--",e);
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * 根据fileName名称读去文件内容
	 * 将读出来的${}替换成的map对应的值
	 * @param fileName
	 * @param paramMap
	 * @return String
	 * */
	public String replaceFile(String fileName,Map<String,String> paramMap)
	{
		//先将fileName的内容读取出来
		String sourceStr=this.readFileStream(ConstantFinalUtil.class.getClassLoader().getResourceAsStream(fileName));
		return this.regexUtil.replace$(sourceStr, paramMap);
	}
}

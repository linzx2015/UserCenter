package com.kkk.usercenter.common.test;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.kkk.usercenter.common.util.ConstantFinalUtil;
import com.kkk.usercenter.common.util.FileUtil;
import com.kkk.usercenter.common.util.RegexUtil;
import com.kkk.usercenter.common.util.SpringEmailUtil;

/**
 * 邮件发送测试类
 * @author kkk
 * */
public class EmailSendTest extends BaseTest
{	
	@Resource
	private SpringEmailUtil springEmailUtil;
	@Resource
	private RegexUtil regexUtil;
	@Resource
	private FileUtil fileUtil;
	@Before
	public void init()
	{
		super.init();
		this.springEmailUtil=(SpringEmailUtil) this.ac.getBean("springEmailUtil");
		this.regexUtil=(RegexUtil) this.ac.getBean("regexUtil");
		this.fileUtil=(FileUtil) this.ac.getBean("fileUtil");
	}
	/**
	 * 发送邮件测试
	 * */
	@Test
	public void sendEmilTest()
	{
		//设置发送邮件的邮箱帐号的信息
		String to="linzx2014@163.com";
		String subject="测试邮件";
		String text="这是测<a href='http://www.baidu.com'>试的内</a>容";
		boolean flag=this.springEmailUtil.sendEmailHtml(to, subject, text);
		ConstantFinalUtil.loggerMsg.info("--邮件发送的标志-flag-{}--",flag);
	}
	
	/**
	 * 测试${}字符串替换
	 * */
	@Test
	public void replace$Test()
	{
		String sourceStr="te<a href=''>test</a>s t${email}aa${subject}bb";
		Map<String,String> paramMap=new HashMap<String,String>();
		paramMap.put("email","kk@qq.com");
		paramMap.put("subject","----");
		String replaceStr=this.regexUtil.replace$(sourceStr, paramMap);
		ConstantFinalUtil.loggerMsg.info("--替换后的字符串-{}-",replaceStr);
	}
	
	/**
	 * 测试${}字符串替换
	 * */
	@Test
	public void replaceFile$Test()
	{
		String fileName="template/emailVerify.html";
		Map<String,String> paramMap=new HashMap<String,String>();
		paramMap.put("email", "linzx2014@163.com");
		paramMap.put("subject", "测试发邮件");
		paramMap.put("verifyHref", "http://www.baidu.com");
		paramMap.put("date", "2017-2-9");
		String resultStr=this.fileUtil.replaceFile(fileName, paramMap);
		ConstantFinalUtil.loggerMsg.info("--{}--",resultStr);
	}
}

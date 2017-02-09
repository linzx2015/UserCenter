package com.kkk.usercenter.common.test;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.kkk.usercenter.common.util.ConstantFinalUtil;
import com.kkk.usercenter.common.util.SpringEmailUtil;

/**
 * 邮件发送测试类
 * @author kkk
 * */
public class EmailSendTest extends BaseTest
{	
	@Resource
	private SpringEmailUtil springEmailUtil;
	
	@Before
	public void init()
	{
		super.init();
		springEmailUtil=(SpringEmailUtil) this.ac.getBean("springEmailUtil");
	}
	/**
	 * 发送邮件
	 * 
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
}

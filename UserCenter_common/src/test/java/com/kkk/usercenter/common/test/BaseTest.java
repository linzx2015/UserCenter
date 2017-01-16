package com.kkk.usercenter.common.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kkk.usercenter.common.util.ConstantFinalUtil;

public class BaseTest
{
	protected ApplicationContext ac;
	@Before
	public void init()
	{
		this.ac=new ClassPathXmlApplicationContext("classpath:spring/applicationContext_*.xml");
		ConstantFinalUtil.loggerMsg.info("--init--{}",this.ac);
	}
	@Test
	public void test()
	{
		ConstantFinalUtil.loggerMsg.info("--test--");
	}
	@After
	public void close()
	{
		ClassPathXmlApplicationContext cpxac=(ClassPathXmlApplicationContext) this.ac;
		ConstantFinalUtil.loggerMsg.info("--close--");
	}
}

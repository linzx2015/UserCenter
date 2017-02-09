package com.kkk.usercenter.common.util;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * spring邮件发送工具
 * @author kkk
 * */
@Component("springEmailUtil")
public class SpringEmailUtil
{
	//组件交给spring管理
	@Resource(name = "javaMailSenderImpl")
	private JavaMailSender javaMailSender;
	@Resource
	private SimpleMailMessage simpleMailMessage;
	/**
	 * 发送邮件
	 * @return boolean
	 * */
	public boolean sendEmail(String to,String subject,String text)
	{
		try
		{
			this.simpleMailMessage.setTo(to);
			this.simpleMailMessage.setSubject(subject);
			this.simpleMailMessage.setText(text);
			this.javaMailSender.send(simpleMailMessage);
			return true;
		} catch (MailException e)
		{
			//发送失败,以异常展示
			ConstantFinalUtil.loggerMsg.error("--邮件发送失败-发给-{}-主题-{}--内容-{}--",to,subject,text,e);
		}
		return false;
	}
	
	/**
	 * 发送邮件中附带富文本文件
	 * @return boolean
	 * */
	public boolean sendEmailHtml(String to,String subject,String text)
	{
		MimeMessage mimeMessage=this.javaMailSender.createMimeMessage();
		/**
		 * 添加富文本文件
		 * 要设置编码
		 * */
		try
		{
			//true代表富文本文件传输,并设置编码
			MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true,"UTF-8");
			//设置谁发邮件,谁接收
			helper.setFrom(this.simpleMailMessage.getFrom());
			helper.setTo(to);
			helper.setSubject(subject);
			//设置true代表传输富文本
			helper.setText(text,true);
			this.javaMailSender.send(mimeMessage);
			ConstantFinalUtil.loggerMsg.info("--发送邮件html成功-{}-{}-{}-",to,subject,text);
			return true;
		} catch (Exception e)
		{
			ConstantFinalUtil.loggerMsg.error("--发送邮件html失败-{}-{}-{}-",to,subject,text,e);
		}
		return false;
	}
}

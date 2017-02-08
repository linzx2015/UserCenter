package com.kkk.usercenter.head.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.kkk.usercenter.common.controller.BaseController;
import com.kkk.usercenter.common.util.ConstantFinalUtil;
import com.kkk.usercenter.common.util.EncryptUtil;
import com.kkk.usercenter.users.pojo.AAdminsEnum;
import com.kkk.usercenter.users.pojo.AUsers;
import com.kkk.usercenter.users.pojo.AUsersEnum;
import com.kkk.usercenter.users.service.IUserService;

/**
 * 前台网民登录服务端,一般在类上不顶RequestMapping,防止被网民猜出路径
 * @author kkk
 * */
@Controller
public class NoLoginHeadController extends BaseController
{
	@Resource
	private IUserService userService;
	/**
	 * 前台登录的检查方法
	 * @param request,response 
	 * @return 
	 * @throws IOException
	 * */
	@RequestMapping("/login")
	public String login(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		ConstantFinalUtil.loggerMsg.info("---login---");
		HttpSession session=request.getSession();
		AUsers users=(AUsers) session.getAttribute("users");
		if(users!=null)
		{
			//验证是否有returnUrl,已经登录
			if(this.returnUrlExist(request, response, users))
			{
				return null;
			}else
			{
				//登录成功
				return "redirect:/head/users/main.html";
			}
		}
		return "/head/login";
	}
	
	/**
	 * 登录提交
	 * @throws IOException 
	 * 
	 * */
	@RequestMapping("/loginSubmit")
	public String loginSubmit(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		ConstantFinalUtil.loggerMsg.info("---loginSubmit-----");
		HttpSession session=request.getSession();
		//从前台获取到参数
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String randCode=request.getParameter("rand");
		
		//从session中取出验证码
		String rand=(String) session.getAttribute("rand");
		//比较验证码和使用万能验证码
		if(rand!=null && rand.equalsIgnoreCase(randCode) || "1234".equalsIgnoreCase(randCode))
		{
			Map<String,Object> paramMap=this.getParamMap();
			paramMap.put("email", email);
			AUsers users=this.userService.queryOneAusersService(paramMap);
			if(users!=null)
			{
				//验证密码,经过加密之后比较字符串
				if(EncryptUtil.checkEncodeStr(password, users.getPassword()))
				{
					//密码正确,校验用户是否已经启用
					if(users.getStatus()==AAdminsEnum.STATUS_ENABLE.getStatus())
					{
						//用户已经启用,放入session中,供前台使用
						session.setAttribute("users", users);
						session.setAttribute("lastLoginTime", users.getLastLoginTime());
						
						//修改上次登录时间为当前时间
						users.setLastLoginTime(new Date());
						this.userService.updateOneAuserService(users);
						ConstantFinalUtil.loggerMsg.info("--email-{}-登录时间-{}",users.getEmail(),users.getLastLoginTime());
					
						if(this.returnUrlExist(request, response, users))
						{
							//已经登录
							return null;
						}else
						{
							//登录成功,页面跳转
							return "redirect:/head/users/main.html";
						}
					}else
					{
						this.info=ConstantFinalUtil.INFO_JSON.getString("4");
					}
				}else
				{
					//密码不正确
					this.info=ConstantFinalUtil.INFO_JSON.getString("5");
				}
				//密码不正确,但是保留邮箱
				request.setAttribute("email", email);
			}else
			{
				//用户不存在
				this.info=ConstantFinalUtil.INFO_JSON.getString("6");
			}
		}else
		{
			//验证码不正确
			this.info=ConstantFinalUtil.INFO_JSON.getString("7");
		}
		request.setAttribute("info", this.info);
		return this.login(request, response);
	}
	
	/**
	 * 如果带上reurnUrl:跳转到returnUrl,并且加上token参数,页面跳转
	 * 如果木有带,跳转到用户中心自己登陆后的页面
	 * @param request
	 * @return 如果是true,表示有returnUrl,如果是false,表明木有returnUrl
	 * @throws IOException 
	 * */
	private boolean returnUrlExist(HttpServletRequest request,HttpServletResponse response,AUsers users) throws IOException
	{
		String returnUrl=request.getParameter("returnUrl");
		ConstantFinalUtil.loggerMsg.info("----returnUrl---{}-----",returnUrl);
		if(returnUrl==null)
		{
			returnUrl="";
		}
		if(!"".equalsIgnoreCase(returnUrl))
		{
			//请求有的url中returnUrl,要产生token
			String token=UUID.randomUUID()+"";
			//将token存储到TOKEN_MAP的键,值为JSONObject
			ConstantFinalUtil.TOKENS_MAP.put(token, users.toJSON());
			//加上token
			String tokenIndex="token=";
			if(returnUrl.indexOf("?")!=-1 && returnUrl.indexOf(token)!=-1)
			{
				/* http://aaa.com?token=aaa
				 * http://aaa.com?id=1&a=2&token=2222
				 * http://aaa.com?id=1&a=2&abcdtoken=2222:自行解决
				 *  */
				//截取从0到token位置的字符串,旧url带token
				returnUrl=returnUrl.substring(0, returnUrl.indexOf(token));
				returnUrl=returnUrl+"token="+token;
			}else if(returnUrl.indexOf("?")!=-1)
			{
				//有带参数,但没有token
				returnUrl=returnUrl+"&token="+token;
			}else
			{
				//没有参数,直接加token
				returnUrl=returnUrl+"?token="+token;
			}
			ConstantFinalUtil.loggerMsg.info("--加上token后的returnUrl-{}--",returnUrl);
			response.sendRedirect(returnUrl);
			return true;
		}
		return false;
	}
	
	/**
	 * 注册使用
	 * @return String
	 * */
	@RequestMapping("/register")
	public String register()
	{
		ConstantFinalUtil.loggerMsg.info("---register----");
		return "/head/register";
	}
	
	/**
	 * 注册提交服务
	 * 使用属性驱动
	 * @param AUsers,request
	 * */
	@RequestMapping("/registerSubmit")
	public String registerSubmit(HttpServletRequest request,AUsers users)
	{
		ConstantFinalUtil.loggerMsg.info("---registerSubmit---");
		HttpSession session=request.getSession();
		String randCode=request.getParameter("rand");
		
		//取出session的rand
		String rand=(String) session.getAttribute("rand");
		if(rand!=null && rand.equalsIgnoreCase(randCode)|| "0715".equals(randCode))
		{
			//属性对象驱动已经自动完成赋值 密码和邮箱
			users.setPassword(EncryptUtil.encodeStr(users.getPassword()));
			users.setStatus(AUsersEnum.STATUS_ENABLE.getStatus());
			users.setCreateTime(new Date());
			users.setUpdateTime(new Date());
			users.setLastLoginTime(new Date());
			
			JSONObject resultJSON=this.userService.insertOneAuserService(users);
			//提示用户
			this.info=resultJSON.getString("info");
		}else
		{
			this.info=ConstantFinalUtil.INFO_JSON.getString("7");
		}
		request.setAttribute("info", info);
		return this.register();
	}
}

package com.kkk.usercenter.head.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kkk.usercenter.common.controller.BaseController;
import com.kkk.usercenter.common.util.ConstantFinalUtil;
import com.kkk.usercenter.common.util.EncryptUtil;
import com.kkk.usercenter.outer.service.IOuterService;
import com.kkk.usercenter.users.pojo.AAdminsEnum;
import com.kkk.usercenter.users.pojo.AUsers;
import com.kkk.usercenter.users.pojo.AUsersEnum;
import com.kkk.usercenter.users.pojo.AUsersExtend;
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
	@Resource
	private IOuterService outerService;
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
		HttpSession session=request.getSession();
		//从前台获取到参数
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String randCode=request.getParameter("rand");
		ConstantFinalUtil.loggerMsg.info("---loginSubmit---{}--",password);
		
		//从session中取出验证码
		String rand=(String) session.getAttribute("rand");
		//比较验证码和使用万能验证码
		if(rand!=null && rand.equalsIgnoreCase(randCode) || "0715".equalsIgnoreCase(randCode))
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
	 * 对token进行验证是否有效 { "version":"1", "data":{ token:"" } }
	 * 
	 * @return userJSON
	 */
	@ResponseBody
	@RequestMapping(value = "/validateUser", produces = "text/html;charset=utf-8")
	public String validateUser(HttpServletRequest request)
	{
		JSONObject resultJSON = new JSONObject();
		String jsonStr = request.getParameter("json");
		if (jsonStr == null)
		{
			jsonStr = "";
		}

		try
		{
			// 转成JSONObject
			JSONObject reqJSON = (JSONObject) JSON.parse(jsonStr);
			// 根据版本号进行验证,如果有额外的版本号再另外增加,为多版本兼容多准备
			if ("1".equalsIgnoreCase(reqJSON.getString("version")))
			{
				resultJSON = this.outerService.validator01UserService(reqJSON);
			} else
			{
				resultJSON.put("version", reqJSON.getString("version"));
				resultJSON.put("code", 9);
				resultJSON.put("info", ConstantFinalUtil.INFO_JSON.getString("9"));
			}
		} catch (Exception e)
		{
			resultJSON.put("code", "11");
			resultJSON.put("info", ConstantFinalUtil.INFO_JSON.getString("11"));
		}

		return resultJSON + "";
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
		String password=request.getParameter("password");
		
		//取出session的rand
		String rand=(String) session.getAttribute("rand");
		if(rand!=null && rand.equalsIgnoreCase(randCode)|| "0715".equals(randCode))
		{
			//属性对象驱动已经自动完成赋值 密码和邮箱
			//String encodePwd=EncryptUtil.encodeStr(users.getPassword());
			users.setPassword(EncryptUtil.encodeStr(users.getPassword()));
		//	ConstantFinalUtil.loggerMsg.info("-加密的字符串-{}--{}",encodePwd,users.getPassword());
			users.setStatus(AUsersEnum.STATUS_ENABLE.getStatus());
			users.setCreateTime(new Date());
			users.setUpdateTime(new Date());
			users.setLastLoginTime(new Date());
			
			JSONObject resultJSON=this.userService.insertOneAuserService(users);
			//提示用户
			this.info=resultJSON.getString("info");
			//进行发邮件操作
			if("0".equalsIgnoreCase(resultJSON.getString("code")))
			{
				this.reSend(request,users.getEmail());
			}
		}else
		{
			this.info=ConstantFinalUtil.INFO_JSON.getString("7");
		}
		request.setAttribute("info", info);
		return this.register();
	}
	
	/**
	 * 邮件重新发送操作
	 * @param email
	 * */
	@RequestMapping("/reSend")
	public String reSend(HttpServletRequest request,String email)
	{
		//根据邮箱查询用户
		Map<String,Object> paramMap=this.getParamMap();
		paramMap.put("email", email);
		AUsers user=this.userService.queryOneAusersService(paramMap);
		/**
		 * 生成链接:http://127.0.0.1:10000/UserCenter_head/verifyEmail.htm?
		 * 	email=11@11.com&code=sha256(email+时间+uuid)
		 * */
		Date sendEmailTime =new Date();
		//sha256(11@11.com+2017-02-09 11:11:11+uuid)
		String codeUid=UUID.randomUUID().toString();
		//拼装成待加密的字符串
		String code=user.getEmail()+this.dateFormatUtil.formatDateTime(sendEmailTime)+codeUid;
		String verifyHref=ConstantFinalUtil.BUNDLE.getString("website.urlRootPath")+"/verifyEmail.html?email="+email+
				"&code="+DigestUtils.sha256Hex(code);
		//存储扩展表的数据
		AUsersExtend usersExtend=new AUsersExtend();
		usersExtend.setEmailSendTime(sendEmailTime);
		usersExtend.setEmailSendCode(codeUid);
		usersExtend.setUpdateTime(new Date());
		//更新用户扩展表的数据
		this.userService.updateOneAuserService(user);
		
		//发送邮件需要的信息
		String fileName="template/emailVerify.html";
		String subject="用户中心发送的邮件";
		Map<String,String> condMap=new HashMap<String,String>();
		//用户的email
		condMap.put("email", user.getEmail());
		condMap.put("subject",subject);
		condMap.put("verifyHref", verifyHref);
		condMap.put("date",this.dateFormatUtil.formatDate(new Date()));
		String resultStr=this.fileUtil.replaceFile(fileName, condMap);
		this.springEmailUtil.sendEmailHtml(user.getEmail(), subject, resultStr);
		this.info+=",邮件已经发送成功,请到邮箱中验证;<a href='"+request.getContextPath()+"/reSend.html?email="+user.getEmail()+"'>重新发送</a>";
		return this.register();
	}
	/**
	 * 验证邮件的链接
	 * @param request
	 * @param email
	 * @return String
	 */
	@RequestMapping("/verifyEmail")
	public String verifyEmail(HttpServletRequest request,String email)
	{
		Map<String,Object> paramMap=this.getParamMap();
		paramMap.put("email", email);
		AUsers users=this.userService.queryOneAusersService(paramMap);
		if(users==null)
		{
			this.info="邮箱帐号不存在";
		}else
		{
			AUsersExtend usersExtend=users.getUsersExtend();
			//检查邮件过期时间
			long timeDiff=new Date().getTime()-usersExtend.getEmailSendTime().getTime();
			if(timeDiff>ConstantFinalUtil.EMAIL_AUTH_TIMEOUT)
			{
				this.info=ConstantFinalUtil.INFO_JSON.getString("13");
				request.setAttribute("info", this.info);
				return "/head/info";
			}
			//验证链接的合法性;sha256(11@11.com+2017-02-09 11:11:11+uuid)
			String validCodeUid=usersExtend.getEmailSendCode();
			String validCode=users.getEmail()+this.dateFormatUtil.formatDateTime(usersExtend.getEmailSendTime())+validCodeUid;
			validCode=DigestUtils.sha256Hex(validCode);
			
			String reqCodeStr=request.getParameter("code");
			if(!validCode.equalsIgnoreCase(reqCodeStr))
			{
				this.info=ConstantFinalUtil.INFO_JSON.getString("14");
				request.setAttribute("info", this.info);
			}
			
			//邮箱还未验证,需要进行验证
			if(users.getEmailStatus()!=AUsersEnum.EMAILSTATUS_VERIFYED.getStatus())
			{
				users.setEmailStatus(AUsersEnum.EMAILSTATUS_VERIFYED.getStatus());
				JSONObject resultJSON=this.userService.updateOneAuserService(users);
				this.info=resultJSON.getString("info");
			}else
			{
				//已经验证过,无需再次验证
				this.info=ConstantFinalUtil.INFO_JSON.getString("15");
			}
		}
		request.setAttribute("info", this.info);
		return "/head/info";
	}
	
}

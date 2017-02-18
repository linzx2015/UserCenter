package com.kkk.usercenter.back.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kkk.usercenter.common.controller.BaseController;
import com.kkk.usercenter.common.util.ConstantFinalUtil;
import com.kkk.usercenter.common.util.EncryptUtil;
import com.kkk.usercenter.outer.service.IOuterService;
import com.kkk.usercenter.users.pojo.AAdmins;
import com.kkk.usercenter.users.pojo.AAdminsEnum;
import com.kkk.usercenter.users.service.IUserService;
/**
 * 后台管理员使用
 * @author kkk
 * */
@Controller
public class NoLoginController extends BaseController
{
	@Resource
	private IUserService userService;
	@Resource
	private IOuterService outerService;

	/**
	 * 管理员列表
	 * 
	 * @return 跳转的jsp名称
	 */
	@RequestMapping("/adminList")
	public String adminList(HttpServletRequest request)
	{
		ConstantFinalUtil.loggerMsg.info("----adminList-数据展示----");
		Map<String, Object> paramMap = this.getParamMap();
		List<AAdmins> adminList = this.userService.queryMultiAdminsService(null, paramMap);
		request.setAttribute("adminList", adminList);
		return "/back/adminList";
	}

	/**
	 * 登录
	 * 
	 * @return String
	 * @throws IOException 
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		ConstantFinalUtil.loggerMsg.info("--login--");
		HttpSession session=request.getSession();
		AAdmins admins=(AAdmins) session.getAttribute("admins");
		//如果admins不为空,则表示已经登录
		if(admins!=null)
		{
			//检验returnUrl,已经登录就不做处理
			if(this.returnUrlExist(request, response, admins))
			{
				return null;
			}else
			{
				return "redirect:/back/admins/main.html";
			}
		}
		return "/back/login";
	}

	/**
	 * 登录提交
	 * 
	 * @param request
	 * @param response
	 * @return String
	 * @throws IOException
	 */
	@RequestMapping("/loginSubmit")
	public String loginSubmit(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		ConstantFinalUtil.loggerMsg.info("---loginSubmit---");
		HttpSession session = request.getSession();

		String randCode = request.getParameter("rand");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		// 从session中取出随机数
		String sessionRandCode = session.getAttribute("rand") + "";
		if ((sessionRandCode != null && sessionRandCode.equalsIgnoreCase(randCode)) || "1234".equals(randCode))
		{
			// 根据邮箱查询对应的管理员
			Map<String, Object> paramMap = this.getParamMap();
			paramMap.put("email", email);
			AAdmins admin = this.userService.queryOneAdminsService(paramMap);
			// 管理员存在
			if (admin != null)
			{
				// 验证密码
				if (EncryptUtil.checkEncodeStr(password, admin.getPassword()))
				{
					// 如果用户启用,则进行下一步操作
					if (admin.getStatus() == AAdminsEnum.STATUS_ENABLE.getStatus())
					{
						// 将admin和lastLoginTime存入到session
						session.setAttribute("amdins", admin);
						session.setAttribute("lastLoginTime", admin.getLastLoginTime());

						// 修改管理员上次登录时间
						admin.setLastLoginTime(new Date());
						this.userService.updateOneAdminService(admin);
						ConstantFinalUtil.loggerMsg.info("登陆成功;id:{},邮箱:{},trueName:{},登陆时间:{}", admin.getId(),
								admin.getEmail(), admin.getTrueName(), admin.getLastLoginTime().toLocaleString());
						// 如果有returnUrl则进行另外处理
						if (this.returnUrlExist(request, response, admin))
						{
							return null;
						} else
						{
							// 登录成功
							return "redirect:/back/admins/main.html";
						}
					} else
					{
						this.info = ConstantFinalUtil.INFO_JSON.getString("4");
					}
				} else
				{
					// 密码不正确
					this.info = ConstantFinalUtil.INFO_JSON.getString("5");
				}
				// 用户存在,只是密码不正确,故保留邮箱
				request.setAttribute("email", email);
			} else
			{
				// 用户不存在
				this.info = ConstantFinalUtil.INFO_JSON.getString("6");
			}
		} else
		{
			// 验证码不正确
			this.info = ConstantFinalUtil.INFO_JSON.getString("7");
		}
		request.setAttribute("info", info);
		return this.login(request,response);
	}

	/**
	 * 如果有returnUrl则加上token,直接让其跳转到对应的url中 如果没有,则跳转到用户中心后台
	 * 
	 * @param request
	 * @param response
	 * @return boolean true:有returnUrl,false:没有returnUrl
	 * @throws IOException
	 */
	private boolean returnUrlExist(HttpServletRequest request, HttpServletResponse response, AAdmins admin)
			throws IOException
	{
		String returnUrl = request.getParameter("returnUrl");
		ConstantFinalUtil.loggerMsg.info("returnUrl===={}" , returnUrl);
		/* 不传为null,传了空字符串,两者合二为一 */
		if(returnUrl == null)
		{
			returnUrl = "" ; 
		}
		
		if(!"".equalsIgnoreCase(returnUrl))
		{
			/* 
			 * 生成uuid
			 * 加上token
			 * 	如果原来的url木有参数,加上?
			 * 	如果有参数,加上&
			 * 将returnUrl:加密
			 *  */
			/*生成uuid*/
			String token = UUID.randomUUID() + "" ; 
			
			// 存储到Token的容器中,值为管理员对应的JSON信息 
			/*JSONObject adminsJSON = new JSONObject();
			 存储管理员对象可开放的字段 
			adminsJSON.put("id", admins.getId());
			adminsJSON.put("email", admins.getEmail());
			adminsJSON.put("createTime", this.dateFormatUtil.format(admins.getCreateTime()));
			adminsJSON.put("updateTime", this.dateFormatUtil.format(admins.getUpdateTime()));
			adminsJSON.put("lastLoginTime", this.dateFormatUtil.format(admins.getLastLoginTime()));
			ConstatFinalUtil.TOKENS_MAP.put(token, adminsJSON);*/
			
			// 调用POJO对应的json存储 
			ConstantFinalUtil.TOKENS_MAP.put(token, admin.toJSON());
			//将token项redis中也存一份
			this.redisUtil.put(token,admin.toJSON().toJSONString(),60*2);
			
			/*加上token*/
			String tokenIndex = "token=" ;
			if(returnUrl.indexOf("?") != -1 && returnUrl.indexOf(tokenIndex) != -1)
			{
				/* http://aaa.com?token=aaa
				 * http://aaa.com?id=1&a=2&token=2222
				 * http://aaa.com?id=1&a=2&abcdtoken=2222:自行解决
				 *  */
				returnUrl = returnUrl.substring(0, returnUrl.indexOf(tokenIndex));
				returnUrl = returnUrl + "token=" + token ; 
			}else if(returnUrl.indexOf("?") != -1)
			{
				/* http://aaa.com?id=10
				 * 木有token */
				/* 肯定有参数 */
				returnUrl = returnUrl + "&token=" + token ; 
			}else
			{
				/* 肯定木有参数 */
				returnUrl = returnUrl + "?token=" + token ; 
			}
			ConstantFinalUtil.loggerMsg.info("returnUrl==加上token=={}",returnUrl);
			/* URL跳转 */
			response.sendRedirect(returnUrl);
			/* 将returnUrl:加密 */
			/*returnUrl = URLEncoder.encode(returnUrl, "UTF-8");*/
			return true ; 
		}
		return false ; 
	}

	/**
	 * 对token进行验证是否有效 { "version":"1", "data":{ token:"" } }
	 * 
	 * @return amdinJSON
	 */
	@ResponseBody
	@RequestMapping(value = "/validateAdmin", produces = "text/html;charset=utf-8")
	public String validateAdmin(HttpServletRequest request)
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
				resultJSON = this.outerService.validator01AdminService(reqJSON);
			}else if("2".equalsIgnoreCase(reqJSON.getString("version")))
			{
				//第二个版本存在redis中
				resultJSON=this.outerService.validator02AdminService(reqJSON);
			}else
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
	 * 验证token是否正确
	 *  参数的名称为json,值为json字符串:
	 * {
			"version":"1",
			"data":{
				"token":""
			}
		}
		@return JSON
	 * */
	@ResponseBody
	@RequestMapping(value="/validatorAdmins.htm",produces="text/html;charset=UTF-8")
	public String validatorAdmins(HttpServletRequest request)
	{
		ConstantFinalUtil.loggerMsg.info("----validatorAdmins----");
		JSONObject resultJSON=new JSONObject();
		String json=request.getParameter("json");
		if(json==null)
		{
			json="";
		}
		try
		{
			//将请求的数据转化成json
			JSONObject reqJSON=(JSONObject) JSON.parse(json);
			if("1".equalsIgnoreCase(reqJSON.getString("version")))
			{
				this.outerService.validator01AdminService(reqJSON);
			}/*else if("2".equalsIgnoreCase(reqJSON.getString("version")))
			{
				//为客户端多个版本升级做准备
				this.outerService.validator02AdminService(reqJSON);
			}*/
			else
			{
				resultJSON.put("version", reqJSON.getString("version"));
				resultJSON.put("code", "9");
				resultJSON.put("info", ConstantFinalUtil.INFO_JSON.getString("9"));
			}
		} catch (Exception e)
		{
			resultJSON.put("code", "11");
			resultJSON.put("info", ConstantFinalUtil.INFO_JSON.getString("11"));
		}
		return resultJSON+"";
	}
}

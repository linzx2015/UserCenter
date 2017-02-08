<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/include/commonPage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>用户注册 - 用户中心</title>
		<%@ include file="/common/include/title.jsp" %>
		
		<script src="${rootPath }/common/resource/formvalidator4.1.3/jquery-1.4.4.min.js" type="text/javascript"></script>
		<script src="${rootPath }/common/resource/formvalidator4.1.3/formValidator-4.1.3.js" type="text/javascript" charset="UTF-8"></script>
		<script src="${rootPath }/common/resource/formvalidator4.1.3/formValidatorRegex.js" type="text/javascript" charset="UTF-8"></script>
		<script type="text/javascript">
			/*
				$(document).ready(function(){
				页面加载完成之后,执行里面的方法					
			*/
			$(function()
			{
				$.formValidator.initConfig
				({
					formID:"form",
					theme:"126",
					submitOnce:true,
					ajaxPrompt : '有数据正在异步验证，请稍等...'
				});
				
				/* 邮箱的验证 */
				$("#email").formValidator
				({
					onShow:"请输入邮箱",
					onFocus:"邮箱6-100个字符",
					onCorrect:"邮箱填写正确"
				}).inputValidator
				({
					min:6,
					max:100,
					onError:"你输入的邮箱长度非法,请确认"
				}).regexValidator
				({
					regExp:"^([\\w-.]+)@(([[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.)|(([\\w-]+.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(]?)$",
					onError:"你输入的邮箱格式不正确"
				});
				
				/* 密码 */
				$("#password").formValidator
				({
					onShow:"请输入密码",
					onFocus:"至少1个长度",
					onCorrect:"密码合法"
				}).inputValidator
				({
					min:1,
					empty:
					{
						leftEmpty:false,
						rightEmpty:false,
						emptyError:"密码两边不能有空符号"
					},
					onError:"密码不能为空,请确认"
				});
				/* 确认密码 */
				$("#confirmPass").formValidator
				({
					onShow:"输再次输入密码",
					onFocus:"至少1个长度",
					onCorrect:"密码一致"
				}).inputValidator({
					min:1,empty:{
						leftEmpty:false,
						rightEmpty:false,
						emptyError:"确认密码两边不能有空符号"
					},
					onError:"确认密码不能为空,请确认"
				}).compareValidator
				({
					desID:"password",
					operateor:"=",
					onError:"2次密码不一致,请确认"
				});
				
				/* 验证码 */
				/* $("#rand").formValidator
				({
					onShow:"请输入验证码",
					onFocus:"验证码4个字符",
					onCorrect:"邮箱填写正确"
				}).inputValidator
				({
					min:4,
					max:4,
					onError:"你输入的验证码长度非法"
				}) */
			});
		</script>
	</head>
	<body>
		<div id="doc">
			<%@ include file="/common/include/header.jsp" %>
			<div id="s_bdw">
				<div id="s_bd">
					<div class="dl_zc">
						<div class="dl_zc_title">
							<h2 class="f_l">用户注册</h2>
							<div class="rt_bg f_r"></div>
						</div>
						<div class="dl-con cf" id="entry">
							<form id="form" action="${rootPath }/registerSubmit.html" method="post">
								<div class="form" style="width: 600px;">
									<div class="item">
										<span class="label">邮箱：</span>
										<div class="fl">
											<input type="text" id="email" name="email"
												class="text" tabindex="1" value="${requestScope.email }"/>
											<span id="emailTip"></span>
										</div>
									</div>
									<!--item end-->
									<div class="item">
										<span class="label">密码：</span>
										<div class="fl">
											<input type="password" id="password" name="password"
												class="text" tabindex="2" />
											<span id="passwordTip"></span>
										</div>
									</div>
									<!--item end-->
									<div class="item">
										<span class="label">确认密码：</span>
										<div class="fl">
											<input type="password" id="confirmPass" name="password"
												class="text" tabindex="2" />
											<span id="confirmPassTip"></span>
										</div>
									</div>
									<!--item end-->
									<div class="item">
										<span class="label">验证码：</span>
										<div class="fl">
											<input type="text" id="rand" name="rand" tabindex="2" class="text"/>
											<img src="${rootPath }/randImg.html" id="randImg" width="75" height="25" style="cursor: pointer;" 
												onclick="return refreshImg('randImg')"/>
											<div id="randTip"></div>
										</div>
									</div>
									<div class="item">
										<span class="label">&nbsp;</span>
										<span class="error_info">
											${info }
										</span>
									</div>
									<!--item end-->
									<div class="item">
										<span class="label">&nbsp;</span>
										<input type="submit" value="注册" tabindex="8"/>
									</div>
									<!--item end-->
								</div>
								<!--form end-->
								<div id="guide">
									<h5>有账号的朋友,请直接登陆</h5>
									<div class="content">没有的请先注册</div>
									<a href="${rootPath }/login.html" class="btn-personal">注册新用户</a>
								</div>
								<!--guide end-->
								<div class="clear"></div>
							</form>
						</div>
						<!--regist end-->
					</div>
					<!--dl_zc end-->
					<%-- <script type="text/javascript" src="${rootpath}/js/Validate.js"></script>
					<script type="text/javascript" src="${rootpath}/js/Validate.entry.js"></script> --%>
				</div>
				<!--s_bd end-->
			</div>
			<!--s_bdw end-->
			<%@ include file="/common/include/footer.jsp" %>
		</div>
	</body>
</html>
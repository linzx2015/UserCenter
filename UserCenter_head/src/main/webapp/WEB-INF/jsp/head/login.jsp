<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/include/commonPage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>用户登陆 - 用户中心</title>
		<%@ include file="/common/include/title.jsp" %>
	</head>
	<body>
		<div id="doc">
			<%@ include file="/common/include/header.jsp" %>
			<div id="s_bdw">
				<div id="s_bd">
	
					<div class="dl_zc">
						<div class="dl_zc_title">
							<h2 class="f_l">用户登录</h2>
							<div class="rt_bg f_r"></div>
						</div>
						<div class="dl-con cf" id="entry">
							<form id="formlogin" action="${rootPath }/loginSubmit.html" method="post">
								<div class="form" style="width: 600px;">
									<div class="item">
										<span class="label">邮箱：</span>
										<div class="fl">
											<input type="text" id="loginname" name="email"
												class="text" tabindex="1" value="${requestScope.email }"/>
											<label id="loginname_succeed" class="blank invisible"></label>
											<span class="clear"></span>
											<label id="loginname_error"></label>
										</div>
									</div>
									<!--item end-->
									<div class="item">
										<span class="label">密码：</span>
										<div class="fl">
											<input type="password" id="loginpwd" name="password"
												class="text" tabindex="2" />
											<label id="loginpwd_succeed" class="blank invisible"></label>
											<label><a href="forgot-password.html" class="blue">忘记密码?</a></label>
											<span class="clear"></span>
											<label id="loginpwd_error"></label>
										</div>
									</div>
									<!--item end-->
									<div class="item">
										<span class="label">验证码：</span>
										<div class="fl">
											<input type="text" id="rand" name="rand" tabindex="2" class="text"/>
											<img src="${rootPath }/randImg.html" id="randImg" width="75" height="25" style="cursor: pointer;" 
												onclick="return refreshImg('randImg')"/>
											<label id="loginpwd_succeed" class="blank invisible"></label>
											<span class="clear"></span>
											<label id="loginpwd_error"></label>
										</div>
									</div>
									<!--item end-->
									<div class="item">
										<span class="label">&nbsp;</span>
										<div class="fl">
											<input type="checkbox" name="dl" id="jz" value="" />
											<label for="jz">记住用户名</label>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="checkbox" name="dl" id="zd" value="" />
											<label for="zd">自动登录</label>
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
										<input type="submit" class="btnimg" id="loginsubmit" value="" tabindex="8"/>
									</div>
									<!--item end-->
								</div>
								<!--form end-->
								<div id="guide">
									<h5>还不是KKK商城用户？</h5>
	
									<div class="content">现在免费注册成为KKK商城用户，便能立刻享受便宜又放心的购物乐趣。</div>
									<a href="${rootPath }/register.html" class="btn-personal">注册新用户</a>
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
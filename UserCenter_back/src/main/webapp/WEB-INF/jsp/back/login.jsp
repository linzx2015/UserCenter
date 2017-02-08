<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/include/commonPage.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>管理员登录-用户中心</title>
		<link href="${rootPath}/dwz_jui/themes/css/login.css" rel="stylesheet" type="text/css" />
		<link href="${rootPath}/css/common.css" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript" src="${rootPath}/common/resource/jquery-3.0.0.min.js"></script>
		<script type="text/javascript" src="${rootPath}/common/resource/util.js"></script>
	</head>
	<body>
		<div id="login">
			<div id="login_header">
				<h1 class="login_logo">
					<a href="http://demo.dwzjs.com"><img
						src="${rootPath}/dwz_jui/themes/default/images/login_logo.gif" /></a>
				</h1>
				<div class="login_headerContent">
					<div class="navList">
						<ul>
							<li><a href="#">设为首页</a></li>
							<li><a href="http://bbs.dwzjs.com">反馈</a></li>
							<li><a href="doc/dwz-user-guide.pdf" target="_blank">帮助</a></li>
						</ul>
					</div>
					<h2 class="login_title">
						<img src="${rootPath}/dwz_jui/themes/default/images/login_title.png" />
					</h2>
				</div>
			</div>
			<div id="login_content">
				<div class="loginForm">
					<form action="${rootPath }/loginSubmit.html" method="post">
						<!-- returnUrl,从后台响应中得到 -->
						<input type="hidden" name="returnUrl" value="${param.returnUrl }"/>
						<p>
							<label>邮箱：</label>
							<input type="text" name="email" size="20" value="${requestScope.email }"
								class="login_input" />
						</p>
						<p>
							<label>密码：</label> <input type="password" name="password"
								size="20" class="login_input" />
						</p>
						<p>
							<label>验证码：</label>
							<input class="code" name="rand" type="text" size="5" />
							<span style="cursor: pointer;" onclick="return refreshImg('imgId')">
								<img src="${rootPath}/randImg.html" alt="" width="75" height="24" id="imgId"/>
							</span>
						</p>
						<div class="login_bar">
							<input class="sub" type="submit" value=" " />
						</div>
					</form>
					<div class="err_info">
						${info}
					</div>
				</div>
				<div class="login_banner">
					<img src="${rootPath}/dwz_jui/themes/default/images/login_banner.jpg" />
				</div>
				<div class="login_main">
					<ul class="helpList">
						<li><a href="#">下载驱动程序</a></li>
						<li><a href="#">如何安装密钥驱动程序？</a></li>
						<li><a href="#">忘记密码怎么办？</a></li>
						<li><a href="#">为什么登录失败？</a></li>
					</ul>
					<div class="login_inner">
						<p>您可以使用 网易网盘 ，随时存，随地取</p>
						<p>您还可以使用 闪电邮 在桌面随时提醒邮件到达，快速收发邮件。</p>
						<p>在 百宝箱 里您可以查星座，订机票，看小说，学做菜…</p>
					</div>
				</div>
			</div>
			<div id="login_footer">Copyright &copy; 2009 www.dwzjs.com Inc.
				All Rights Reserved.</div>
		</div>
	</body>
</html>
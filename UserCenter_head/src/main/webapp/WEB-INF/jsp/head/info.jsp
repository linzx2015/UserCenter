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
		<%-- 重新发送邮件跳转的页面 --%>
		<div id="doc">
			<%@ include file="/common/include/header.jsp" %>
			<div id="s_bdw">
				<div id="s_bd">
					<div class="dl_zc error_info">
						邮箱验证结果: ${info }	
					</div>
				</div>
			</div>
			<!--s_bdw end-->
			<%@ include file="/common/include/footer.jsp" %>
		</div>
	</body>
</html>
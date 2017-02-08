<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/include/commonPage.jsp" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>导航页-后台-用户中心</title>
</head>
<body>
	<table border="1" width="80%">
		<tr>
			<td>名称</td>
			<td>链接</td>
		</tr>
		<tr>
			<td>管理员列表</td>
			<td><a href="${rootPath}/adminList.html" target="_blank">管理员列表</a></td>
		</tr>
		<tr>
			<td>管理员登录</td>
			<td><a href="${rootPath}/login.html" target="_blank">管理员登录</a></td>
		</tr>
	</table>	
</body>
</html>
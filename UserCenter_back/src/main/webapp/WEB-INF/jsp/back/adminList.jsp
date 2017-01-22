<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/include/commonPage.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员列表</title>
</head>
<body>
	<table border="1" width="80%">
		<tr>
			<td>序号</td>
			<td>角色</td>
			<td>邮箱</td>
			<td>状态</td>
			<td>时间</td>
		</tr>
		<c:forEach items="${adminList}" var="admin" varStatus="stat">
			<tr>
				<td>${stat.count}</td>
				<td>${admin.role.name}</td>
				<td>${admin.email}</td>
				<td>${admin.status}</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${admin.createTime}"/></td>
			</tr>
		</c:forEach>
	</table>	
</body>
</html>
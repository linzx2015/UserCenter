<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/include/commonPage.jsp" %>
<div class="accordion" fillSpace="sidebar">
	<!-- 根据参数operType来做区别显示 -->
	<c:choose>
		<c:when test="${param.operType=='userManager'}">
			<div class="accordionContent">
				<ul class="tree treeFolder">
					<li>
						<a href="tabsPage.html" target="navTab">用户管理</a>
						<ul>
							<li><a href="main.html" target="navTab" rel="main">用户列表</a></li>
							<li><a href="main.html" target="navTab" rel="main">用户添加</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</c:when>
		<c:when test="${param.operType=='sysManager'}">
			<div class="accordionContent">
				<ul class="tree treeFolder">
					<li>
						<a href="tabsPage.html" target="navTab">管理员管理</a>
						<ul>
							<%-- target:navTab 新打开一个选项卡
								rel:选项卡唯一标识,如果rel有重复,则会被覆盖
							--%>
							<li><a href="${rootPath}/back/admins/adminList.html" target="navTab" rel="adminList">管理员列表</a></li>
							<li><a href="${rootPath}/back/admins/adminInsert.html" target="navTab" rel="adminInsert">管理员添加</a></li>
						</ul>
					</li>
					<li>
						<a href="tabsPage.html" target="navTab">角色管理</a>
						<ul>
							<li><a href="main.html" target="navTab" rel="main">角色列表</a></li>
							<li><a href="main.html" target="navTab" rel="main">角色添加</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</c:when>
	</c:choose>
</div>
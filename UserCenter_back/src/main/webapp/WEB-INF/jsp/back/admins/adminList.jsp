<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/include/commonPage.jsp"%>
<form id="pagerForm" method="post" action="demo_page1.html">
	<input type="hidden" name="status" value="${param.status}"> <input
		type="hidden" name="keywords" value="${param.keywords}" /> <input
		type="hidden" name="pageNum" value="1" /> <input type="hidden"
		name="numPerPage" value="${model.numPerPage}" /> <input type="hidden"
		name="orderField" value="${param.orderField}" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${rootPath }/back/admins/adminList.html"
		method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>我的客户：<input type="text" name="keyword" />
					</td>
					<td><select class="combox" name="province">
							<option value="">所有省市</option>
							<option value="北京">北京</option>
							<option value="上海">上海</option>
							<option value="天津">天津</option>
							<option value="重庆">重庆</option>
							<option value="广东">广东</option>
					</select></td>
					<td>建档日期：<input type="text" class="date" readonly="true" />
					</td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div></li>
					<li><a class="button" href="demo_page6.html" target="dialog"
						mask="true" title="查询框"><span>高级检索</span></a></li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="demo_page4.html" target="navTab"><span>添加</span></a></li>
			<li><a class="delete"
				href="demo/common/ajaxDone.html?uid={sid_user}" target="ajaxTodo"
				title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="demo_page4.html?uid={sid_user}"
				target="navTab"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls"
				target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="80">序号</th>
				<th width="80">邮箱</th>
				<th width="100">真实姓名</th>
				<th width="100">手机号</th>
				<th width="150">qq</th>
				<th width="80">登陆失败次数</th>
				<th width="80">登陆失败时间</th>
				<th width="80">状态</th>
				<th width="120">创建时间</th>
				<th width="120">更新时间</th>
				<th width="120">上次登陆时间</th>
			</tr>
		</thead>
		<tbody>
			
			<c:forEach items="${adminList}" var="admin" varStatus="stat">
				<tr target="sid_user" rel="1">
					<td>${stat.count}</td>
					<td>${admin.email}</td>
					<td>${admin.trueName}</td>
					<td>${admin.phone}</td>
					<td>${admin.qq}</td>
					<td>${admin.loginFailedCount}</td>
					<td>${admin.loginFailedTime}</td>
					<td>${admin.statusStr}</td>
					<td>${admin.createTime}</td>
					<td>${admin.updateTime}</td>
					<td>${admin.lastLoginTime}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<form action="${rootPath}/back/admins/adminList.html" id="adminListForm" onsubmit="return navTabSearch(this);" method="post">
				<a class="button" href="#" onclick="return pageInfoSubmit('adminListForm','adminCurrenPage',1,'adminPageSize','${requestScope.pageInfoUtil.pageSize}')"><span>首页</span>&nbsp;&nbsp;&nbsp;</a>
				<a class="button" href="#" onclick="return pageInfoSubmit('adminListForm','adminCurrenPage','${requestScope.pageInfoUtil.prePage}','adminPageSize','${requestScope.pageInfoUtil.pageSize}')"><span>上一页</span>&nbsp;&nbsp;&nbsp;</a>
				<a class="button" href="#" onclick="return pageInfoSubmit('adminListForm','adminCurrenPage','${requestScope.pageInfoUtil.nextPage}','adminPageSize','${requestScope.pageInfoUtil.pageSize}')"><span>下一页</span>&nbsp;&nbsp;&nbsp;</a>
				<a class="button" href="#" onclick="return pageInfoSubmit('adminListForm','adminCurrenPage','${requestScope.pageInfoUtil.totalPage}','adminPageSize','${requestScope.pageInfoUtil.pageSize}')"><span>尾页</span>&nbsp;&nbsp;&nbsp;</a>
				共${requestScope.pageInfoUtil.totalPage}页
				共${requestScope.pageInfoUtil.totalRecord}条
				第<input type="text" name="currentPage" id="adminCurrenPage" value="${requetScope.pageInfoUtil.currentPage}" size="5" maxlength="5"/>页
				每页<input type="text" name="pageSize" id="adminPageSize" value="${requestScope.pageInfoUtil.pageSize}" size="5" maxlength="5">条
				<input type="submit" value="GO"/>			
			</form>
		</div>	
	</div>		
</div>
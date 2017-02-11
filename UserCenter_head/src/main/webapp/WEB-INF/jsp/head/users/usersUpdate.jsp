<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/include/commonPage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>资料修改 - 用户中心</title>
		<%@ include file="/common/include/title.jsp"%>
		<link type="text/css" href="${rootPath }/css/my.css" rel="stylesheet">
		<script type="text/javascript">
			var timeout = 500;
			var closetimer = 0;
			var ddmenuitem = 0;
		
			$(document).ready(function() {
				$('.cat_item').mousemove(function() {
					$(this).addClass('cat_item_on');
				});
				$('.cat_item').mouseleave(function() {
					$(this).removeClass('cat_item_on');
				});
				$('#slideshow').imgSlideShow({
					itemclass : 'i'
				})
				$("#slide-qg").switchTab({
					titCell : "dt a",
					trigger : "mouseover",
					delayTime : 0
				});
				$("#s_cart_nums1").hover(function() {
					mcancelclosetime();
					if (ddmenuitem)
						ddmenuitem.hide();
					ddmenuitem = $(document).find("#s_cartbox");
					ddmenuitem.fadeIn();
				}, function() {
					mclosetime();
				});
				$("#s_cart_nums2").hover(function() {
					mcancelclosetime();
					if (ddmenuitem)
						ddmenuitem.hide();
					ddmenuitem = $(document).find("#s_cartbox");
					ddmenuitem.fadeIn();
				}, function() {
					mclosetime();
				});
				$("#s_cartbox").hover(function() {
					mcancelclosetime();
				}, function() {
					mclosetime();
				});
				var $cur = 1;
				var $i = 4;
				var $len = $('.hot_list>ul>li').length;
				var $pages = Math.ceil($len / $i);
				var $w = $('.hotp').width() - 66;
		
				var $showbox = $('.hot_list');
		
				var $pre = $('div.left_icon');
				var $next = $('div.rgt_icon');
		
				$pre.click(function() {
					if (!$showbox.is(':animated')) {
						if ($cur == 1) {
							$showbox.animate({
								left : '-=' + $w * ($pages - 1)
							}, 500);
							$cur = $pages;
						} else {
							$showbox.animate({
								left : '+=' + $w
							}, 500);
							$cur--;
						}
		
					}
				});
		
				$next.click(function() {
					if (!$showbox.is(':animated')) {
						if ($cur == $pages) {
							$showbox.animate({
								left : 0
							}, 500);
							$cur = 1;
						} else {
							$showbox.animate({
								left : '-=' + $w
							}, 500);
							$cur++;
						}
		
					}
				});
		
			});
			function mclose() {
				if (ddmenuitem)
					ddmenuitem.hide();
			}
			function mclosetime() {
				closetimer = window.setTimeout(mclose, timeout);
			}
			function mcancelclosetime() {
				if (closetimer) {
					window.clearTimeout(closetimer);
					closetimer = null;
				}
			}
		</script>
	</head>
	<body>
		<div id="doc">
			<%@ include file="/common/include/header.jsp"%>
			<div id="s_bdw">
				<div id="s_bd">
					<div style="margin: 10px 0 0 0;"></div>
					<div class="breadcrumbs">
						<div class="f-l">
							<a href="#">首页</a><span>»</span><a href="#">我的SXT</a><span>»</span>资料修改
						</div>
					</div>
					<%@ include file="/common/include/left_header.jsp"%>
					<div class="f-r presonalinfo">
						<div class="dl_zc_title">
							<h2 class="f_l">资料修改</h2>
							<div class="rt_bg f_r"></div>
						</div>
						<div class="ddbox">
							<div class="dl-con cf" id="entry">
								<form id="formlogin" action="${rootPath }/head/users/usersUpdateSubmit.html" method="post">
									<input type="hidden" name="operType" value="${param.operType }"/>
									<div class="form" style="width: 600px;">
										<div class="item">
											<span class="label">邮箱：</span>
											<div class="fl">
												${requestScope.users.email}(${requestScope.users.emailStatusStr})
												<c:choose>
													<c:when test="${requestScope.users.emailStatus == 1 }">
														<a href="">修改邮箱</a>
													</c:when>
													<c:otherwise>
														<a href="${rootPath}/reSend.html?email=${requestScope.users.email}">重新发送</a>
													</c:otherwise>
												</c:choose>
											</div>
										</div>
										<c:choose>
											<c:when test="${param.operType == 'updateInfo' }">
												<!--item end-->
												<div class="item">
													<span class="label">手机号：</span>
													<div class="fl">
														${requestScope.users.phone}(${requestScope.users.phoneStatusStr})
													</div>
												</div>
												<!--item end-->
												<div class="item">
													<span class="label">QQ:</span>
													<div class="fl">
														<input type="text" name="qq" class="text" value="${requestScope.users.qq}"/>
													</div>
												</div>
												<!--item end-->
												<div class="item">
													<span class="label">地区:</span>
													<div class="fl">
														<!-- 树形菜单名称展示 -->
														${requestScope.users.region.treeName }
														<input type="button" value="修改地区" onclick="return $('#regionDiv').show()"/>
													</div>
												</div>
												<!--item end-->
												<div class="item">
													<span class="label">:</span>
													<div class="fl">
														<input type="hidden" id="regionId" name="regionId" value="${requestScope.users.regionId}"/>
														<div id="regionDiv" style="display: none">
															<select id="regionId1" onchange="return selectRegionList('regionId1','regionId2','regionId')"></select>
															<select id="regionId2" onchange="return selectRegionList('regionId2','regionId3','regionId')"></select>
															<select id="regionId3" onchange="return selectRegionList('regionId3','regionId4','regionId')"></select>
														</div>
														<span class="clear"></span>
													</div>
												</div>
												<!--item end-->
												<div class="item">
													<span class="label">详细地址:</span>
													<div class="fl">
														<input type="text" name="address" class="text" value="${requestScope.users.address}"/>
													</div>
												</div>
												<!--item end-->
												<div class="item">
													<span class="label">头像:</span>
													<div class="fl">
														${requestScope.users.photoPath}
													</div>
												</div>
												<!--item end-->
											</c:when>
											<c:when test="${param.operType == 'updatePass' }">
												<%-- 修改密码 --%>
												<div class="item">
													<span class="label">原密码:</span>
													<div class="fl">
														<input type="password" name="souPass" class="text" value=""/>
													</div>
												</div>
												<!--item end-->
												<div class="item">
													<span class="label">新密码:</span>
													<div class="fl">
														<input type="password" name="password" class="text" value=""/>
													</div>
												</div>
												<div class="item">
													<span class="label">确认密码:</span>
													<div class="fl">
														<input type="password" name="confirmPassword" class="text" value=""/>
													</div>
												</div>
											</c:when>
										</c:choose>
										
										<div class="item">
											<span class="label">&nbsp;</span>
											<span class="error_info">
												${info }
											</span>
										</div>
										<!--item end-->
										<div class="item">
											<span class="label">&nbsp;</span>
											<input type="submit" id="loginsubmit" value="提交" tabindex="8"/>
										</div>
										<!--item end-->
									</div>
									<div class="clear"></div>
								</form>
							</div>
						</div>
					</div>
					<!--presonalinfo end-->
					<div class="clear"></div>
				</div>
				<!--s_bd end-->
			</div>
			<%@ include file="/common/include/footer.jsp"%>
		</div>
	</body>
</html>
<c:if test="${param.operType == 'updateInfo' }">
	<script type="text/javascript">
		/**
			查询地区列表
			页面一加载的时候,木有currSelectid
			regionId:目标存储结果的值
		*/
		function selectRegionList(currSelectid,tarSelectId,regionId)
		{
			/* 取得当前Select框的Id */
			var currSelect = $("#" + currSelectid) ;
			/* 0:表示取出所有的省份 */
			var parentId = "0"
			/* alert(currSelect.length); */
			if(currSelect.length == 1)
			{
				/* 表示取到数据 */
				parentId = currSelect.val();
			}
			
			$("#" + regionId).val(parentId)
			
			/* 找不到目标,就不再进行Ajax */
			if($("#" + tarSelectId).length == 0)
			{
				return ; 
			}
			$.post(
				"${rootPath}/outer/regionList.html",
				"json={'version':'1','data':{'parentId':'"+ parentId +"'}}",
				function(data)
				{
					//alert(data.code)
					if(data.code == '0')
					{
						/* 拼装html;select框 */
						var sb = "<option value='0'>请选择</option>" ; 
						for(var i = 0 ; i < data.data.regionList.length ; i ++)
						{
							var region = data.data.regionList[i] ; 
							//sb += region.name + "---" ;
							sb += "<option value='"+ region.id +"'>"+ region.name +"</option>"
						}
						$("#" + tarSelectId).html(sb);
					}
				},
				"json"
			);
		}
		
		/* 一开始加载,为省份初始化 */
		selectRegionList("0","regionId1");
	</script>
</c:if>
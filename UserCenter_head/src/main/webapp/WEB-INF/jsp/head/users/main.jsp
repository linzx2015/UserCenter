<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/include/commonPage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>我的个人中心 - 用户中心</title>
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
							<a href="#">首页</a><span>»</span><a href="#">我的易迅</a><span>»</span>我的订单
						</div>
					</div>
	
					<%@ include file="/common/include/left_header.jsp"%>
	
					<div class="f-r presonalinfo">
						<div class="dl_zc_title">
							<h2 class="f_l">个人中心</h2>
							<div class="rt_bg f_r"></div>
						</div>
						<div class="ddbox">
							<div class="dl-con cf" id="entry">
								<form id="formlogin" method="post" onsubmit="return false;">
									<div class="form" style="width: 600px;">
										<div class="item">
											<span class="label">邮箱：</span>
											<div class="fl">
												${sessionScope.users.email}(${sessionScope.users.emailStatus})
											</div>
										</div>
										<!--item end-->
										<div class="item">
											<span class="label">手机号：</span>
											<div class="fl">
												${sessionScope.users.phone}(${sessionScope.users.emailStatus})
											</div>
										</div>
										<!--item end-->
										<div class="item">
											<span class="label">QQ:</span>
											<div class="fl">
												${sessionScope.users.qq}
											</div>
										</div>
										<!--item end-->
										<div class="item">
											<span class="label">详细地址:</span>
											<div class="fl">
												${sessionScope.users.qq}
											</div>
										</div>
										<!--item end-->
										<div class="item">
											<span class="label">头像:</span>
											<div class="fl">
												${sessionScope.users.photoPath}
											</div>
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
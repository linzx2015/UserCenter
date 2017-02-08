<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${rootPath }/css/main.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="${rootPath}/css/lr.css" rel="stylesheet" />
<link type="text/css" href="${rootPath }/css/common.css"
	rel="stylesheet" />
<!--[if IE 6]> 
		<link href="${rootPath}/css/main.ie6.css" rel="stylesheet" type="text/css" />
		<![endif]-->
<!--[if IE 7]> 
		<link href="${rootPath}/css/main.ie7.css" rel="stylesheet" type="text/css" />
		<![endif]-->
<script type="text/javascript"
	src="${rootPath}/common/resource/jquery-3.0.0.min.js"></script>
<script type="text/javascript"
	src="${rootPath}/js/jquery-imgslideshow.js"></script>
<script type="text/javascript" src="${rootPath}/js/ks-switch.js"></script>
<script type="text/javascript" src="${rootPath}/js/lib.js"></script>
<script type="text/javascript" src="${rootPath}/common/resource/util.js"></script>
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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/page_header.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>京东方新品EN可视化管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/static/common/meta_base.jsp"%>
<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
}

html,body {
	height: 100%;
	overflow: hidden;
}

*html,*html body {
	overflow: hidden; /*隐藏IE的默认滚动条的宽度*/
}

#bodyDiv {
	width: 100%;
	height: 100%;
}

#topDiv {
	height: 12%;
	background: #ccc;
	border: 0px solid #000;
	overflow: hidden; /*溢出的时候显示滚动条为 auto */
}

#mainDiv {
	height: 88%;
	background: #f4f4f4;
	border: 0px solid #7FCFF2;
	margin-top: -4px;
	overflow: auto;
}

h3 {
	font-size: 14px;
	font-weight: bold;
}
</style>
</head>
<body>
	<div id="bodyDiv">
		<div id="topDiv">
			<iframe id="topFrame" name="topFrame" width="100%" height="100%" frameborder="0" scrolling="no" src="/index/top"></iframe>
		</div>
		<div id="mainDiv">
			<iframe id="mainFrame" name="mainFrame" width="100%" height="100%" frameborder="0" scrolling="yes" src="/product/list"></iframe>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
function logout() {
	window.location.href = "/index/logout";
	//commonSubmitDynamicForm({formId: 'dataForm', action: '/index/logout'});
}

$(window).bind('beforeunload', function(){
	//if ((window.event.screenX - window.screenLeft) > (document.documentElement.scrollWidth - 20) && window.event.clientY < 0 || window.event.altKey) { 
		$.ajax({
			type : 'POST',
			url : '/index/clear',
			async: false
		});
	//}
});
/*
window.onbeforeunload = function () {
	
	    //clearSession();
	if ((window.event.screenX - window.screenLeft) > (document.documentElement.scrollWidth - 20) && window.event.clientY < 0 || window.event.altKey) { 
		$.ajax({
			type : 'POST',
			url : '/index/clear',
			async: false
		});
	}
};

function clearSession() {
	var userAgent = navigator.userAgent.toLowerCase(); 
	if(userAgent.indexOf("msie")>-1) { 
		$.ajax({
			type : 'POST',
			url : '/index/clear',
			crossDomain: true,
			async: false
		});
	}else {
		$.ajax({
			type : 'POST',
			url : '/index/clear',
			async: false
		});
	}
}

*/
</script>



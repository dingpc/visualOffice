<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/page_header.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>京东方|可视化管理|TOP</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/static/common/meta_base.jsp"%>
<style type="text/css">
body{
	margin:0px;
	font-family:"宋体",Arial, Helvetica, sans-serif;
	background-color:#ffffff;
	background-position: left top;
}
.topBg {
	width: 100%;
	height: 100%;
	
	background: url(/static/images/top_bg.gif);
	/*
	position:absolute;
	background: url(/static/images/bg_top.jpg);
	background-size:cover;
	background-repeat:no-repeat;
	z-index:-1;
	*/
}
.aStyle{
	text-decoration:none;
	color:#000000;
	/*font-weight:bold;*/
}

a:link {
 color: #6597cb;
 TEXT-DECORATION: none
}

a:visited {
 COLOR: #6597cb;
 TEXT-DECORATION: none
}
a:hover {
 COLOR: #6597cb;
 text-decoration: none;
}
a:active {
 COLOR: #6597cb;  
 text-decoration: none;
}
</style>
</head>
<body>
	<div class="topBg" >
	<table width="100%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0" >
		<tr>
    		<td rowspan="2" width="15%" align="left" valign="top"><a href="javascript:void(0)" ><img src="/static/images/logo.png" border="0" /></a></td>
			<td width="45%"><span style="font-size:30px; color:#6597cb; font-family:微软雅黑; font-weight:bold;">新品EN可视化管理系统</span></td>
    		<td align="right" width="40%" valign="top">
    		<a class="aStyle" href="javascript:void(0)" onclick="openProductList()"><img width="16px" height="16px" src="/static/images/home.gif" title="首页" />首页</a>
    		<a class="aStyle"  href="javascript:void(0)" onclick="openUserList()"><img width="16px" height="16px" src="/static/images/user_mgmt.gif" title="用户管理" />用户管理</a>
    		<a class="aStyle"  href="javascript:void(0)" onclick="openLogList()"><img width="16px" height="16px" src="/static/images/system_log.gif" title="日志管理" />日志管理</a>
    		<a class="aStyle" href="javascript:void(0)" onclick="logout()"><img width="16px" height="16px" src="/static/images/close_system.gif" title="退出系统" />退出系统</a>
    		</td>
  		</tr>
  		<tr>
    		<td>&nbsp;</td>
    		<td align="right" valign="top">&nbsp;</td>
  		</tr>
	</table>
	</div>
</body>

<script type="text/javascript">
function logout() {
	window.parent.logout();
}

function openUserList() {
	$("#mainFrame",parent.document.body).attr("src", "/user/list");
}

function openProductList() {
	$("#mainFrame",parent.document.body).attr("src", "/product/list");
}

function openLogList() {
	$("#mainFrame",parent.document.body).attr("src", "/systemlog/list");
}
</script>
</html>
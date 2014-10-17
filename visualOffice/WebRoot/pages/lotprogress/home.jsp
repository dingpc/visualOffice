<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/page_header.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Lot列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/static/common/meta_base.jsp"%>
</head>
<frameset rows="40%,60%" bordercolor="#6597cb">
	<frame id="progressTop" name="progressTop" src="/lotprogress/modify?productId=${productId }" />
	<frameset cols="75%,25%" bordercolor="#6597cb">
		<frame src="/lotmanage/list?productId=${productId }" />
		<frame src="/lot/list?productId=${productId }"/>
	</frameset>
</frameset>
</html>
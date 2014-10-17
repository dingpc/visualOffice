<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/page_header.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>产品列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/static/common/meta_base.jsp"%>
</head>
<body class="MainFrameBox">
<div>
	<div>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="toolbarBottomLine">     
        <tr>  
            <td align="left">
	        	<input type="button" class="btnButton6font" onclick="location_href('/product/list');" value="返回主页面" />
	        </td> 
        </tr>  
    </table>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="toolbarBottomLine">     
        <tr>  
            <td align="left">
            	<span class="boldSpan">1.产品信息</span>
            </td>  
        </tr>  
    </table>
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="listTable">
    	<tr class="listTableHead">
    		<td width="10%">产品</td>
    		<td width="10%">产品描述</td>
    		<td width="10%">EN目的</td>
    		<td width="10%">PM</td>
    		<td width="10%">PL</td>
    		<td width="6%">数量</td>
    		<td width="10%">投入/产出计划</td>
    		<td width="6%">进度</td>
    	</tr>
    	<tr class="listTableLine1">
    		<td align="center"><c:out value="${product.productId}"/></td>
    		<td align="center"><c:out value="${product.productName}"/></td>
    		<td align="center"><c:out value="${product.enAim}"/></td>
    		<td align="center"><c:out value="${product.productManager}"/></td>
    		<td align="center"><c:out value="${product.productLeader}" default="&nbsp;"/></td>
    		<td align="center"><c:out value="${product.productCount}" default="&nbsp;"/></td>
    		<td align="center"><c:out value="${product.productPlan}" default="&nbsp;"/></td>
    		<td align="center"><c:out value="${product.productStatus}" default="&nbsp;"/></td>
   		</tr>
   		<tr class="listTableLine1">
   		<td align="center">今日计划</td>
   		<td align="center" colspan="7"><c:out value="${product.todayPlan}" default="&nbsp;"/></td>
   		</tr>
	</table>
	</div>
	<div>
		<iframe id="splitFrame" name="splitFrame" width="100%" height="300" frameborder="0" scrolling="no" src="/productsplit/list?productId=${product.id}"></iframe>
	</div>
	<div>
		<iframe id="sampleFrame" name="sampleFrame" width="100%" height="300" frameborder="0" scrolling="yes" src="/productsample/list?productId=${product.id}"></iframe>
	</div>
	<div>
		<iframe id="specFrame" name="specFrame" width="100%" height="300" frameborder="0" scrolling="yes" src="/productspec/list?productId=${product.id}"></iframe>
	</div>
</div>
</body>
</html>
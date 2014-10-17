<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/page_header.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>日志列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/static/common/meta_base.jsp"%>
</head>
<body class="MainFrameBox">
	<form id="queryForm" name="queryForm" action="/systemlog/list" method="post">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="SearchBar">
	    <tr>  
	        <td class="whir_td_searchtitle">操作时间：</td>  
	        <td class="whir_td_searchinput">  
	            <input type="text" name="searchCreateTime" id="searchCreateTime" class="Wdate whir_datebox" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,maxDate:'#F{$dp.$D(\'searchCreateTime\',{d:0});}'})" value="${searchCreateTime }"/>
	        </td>
	        <td class="SearchBar_toolbar">  
                <input type="button" class="btnButton4font"  onclick="refreshListForm('queryForm')" value="查找" />  
            </td>  
	    </tr>     
	</table> 
	</form>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="toolbarBottomLine">     
        <tr>  
            <td align="right">&nbsp;</td>  
        </tr>  
    </table> 
         
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="listTable">
    	<tr class="listTableHead">
    		<td width="5%" >序号</td>
    		<td width="10%" >用户名</td>
    		<td width="10%" >操作</td>
    		<td nowrap="nowrap">描述</td>
    		<td width="10%" >操作时间</td>
    	</tr>
    	
    	<c:forEach items="${list}" var="obj"  varStatus="status">
	    	<c:set var="lineClass">listTableLine2</c:set>
	    	<c:if test="${status.index % 2 == 0 }">
	    		<c:set var="lineClass">listTableLine1</c:set>
	    	</c:if>
    		<tr class="${lineClass }">
	    		<td align="center"><c:out value="${status.index + 1}"/></td>
	    		<td align="center"><c:out value="${obj.userName}"/></td>
	    		<td align="center"><c:out value="${obj.operation}"/></td>
	    		<td align="center"><c:out value="${obj.description}"/></td>
	    		<td align="center"><c:out value="${obj.createTime}"/></td>
    		</tr>  
		</c:forEach>
		<c:if test="${list == null || fn:length(list) == 0}">
    		<tr class="listTableLine1">
    			<td colspan="5" align="center">系统中没有数据</td>
    		</tr>
    	</c:if>
	</table>
</body>
</html>
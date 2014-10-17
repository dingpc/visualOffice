<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/page_header.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Split信息列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/static/common/meta_base.jsp"%>
</head>
<body class="MainFrameBox">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="toolbarBottomLine">     
        <tr>  
        	<td align="left"><span class="boldSpan">2.Split信息</span></td>
            <td align="right">
            <c:if test="${LOGIN_USER.isAdmin eq 'Y' || LOGIN_USER.isAdd eq 'Y'}">
                <input type="button" class="btnButton4font" onclick="openNewWin({url:'/productsplit/add?productId=${productId}',width:600,height:220,winName:'addproductsplit'});" value="新增" />
			</c:if>
            <c:if test="${LOGIN_USER.isAdmin eq 'Y' || LOGIN_USER.isDelete eq 'Y'}">
                <input type="button" class="btnButton4font" onclick="ajax_deleteBatch('batch', 'value', '/productsplit/deleteBatch');" value="批量删除" />
			</c:if>
            </td>  
        </tr>  
    </table> 
         
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="listTable">
    	<tr class="listTableHead">
    		<td width="2%" ><input type="checkbox" name="selectAll" onclick="setCheckBoxState('batch', this.checked);"/></td>
    		<td width="4%" >序号</td>
    		<td width="10%" >Lot/Glass信息</td>
    		<td width="10%">数量</td>
    		<td width="10%">Split详细</td>
    		<td width="20%">目的</td>
    		<td width="10%">相关科室1</td>
    		<td width="10%">相关科室2</td>
    		<td width="10%">相关科室3</td>
    		<td nowrap>操作</td>
    	</tr>
    	
    	<c:forEach items="${list}" var="obj"  varStatus="status">
	    	<c:set var="lineClass">listTableLine2</c:set>
	    	<c:if test="${status.index % 2 == 0 }">
	    		<c:set var="lineClass">listTableLine1</c:set>
	    	</c:if>
    		<tr class="${lineClass }">
	    		<td align="center"><input type="checkbox" name="batch" value="${obj.id}"/></td>
	    		<td align="center"><c:out value="${status.index + 1}"/></td>
	    		<td><c:out value="${obj.glassId}"/></td>
	    		<td align="center"><c:out value="${obj.splitCount}"/></td>
	    		<td><c:out value="${obj.splitDetail}" default="&nbsp;"/></td>
	    		<td><c:out value="${obj.splitAim}" default="&nbsp;"/></td>
	    		<td><c:out value="${obj.department1}" default="&nbsp;"/></td>
	    		<td><c:out value="${obj.department2}" default="&nbsp;"/></td>
	    		<td><c:out value="${obj.department3}" default="&nbsp;"/></td>
	    		<td align="center">
	    		<c:if test="${LOGIN_USER.isAdmin eq 'Y' || LOGIN_USER.isModify eq 'Y'}">
	    		<a href="javascript:void(0)" onclick="openNewWin({url:'/productsplit/modify?id=${obj.id}',width:600,height:220,winName:'modifyproductsplit'});">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;
	    		</c:if>
	    		<c:if test="${LOGIN_USER.isAdmin eq 'Y' || LOGIN_USER.isDelete eq 'Y'}">
	    		<a href="javascript:void(0)" onclick="ajax_delete('/productsplit/delete', 'id=${obj.id}');">删除</a>
	    		</c:if>
	    		</td>
    		</tr>  
		</c:forEach>
		<c:if test="${list == null || fn:length(list) == 0}">
    		<tr class="listTableLine1">
    			<td colspan="10" align="center">系统中没有数据</td>
    		</tr>
    	</c:if>
	</table>
</body>
</html>
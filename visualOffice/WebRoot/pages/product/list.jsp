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
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="toolbarBottomLine">     
        <tr>  
            <td align="right">
            <c:if test="${LOGIN_USER.isAdmin eq 'Y' || LOGIN_USER.isAdd eq 'Y'}">
                <input type="button" class="btnButton4font" onclick="openNewWin({url:'/product/add',width:600,height:250,winName:'addProduct'});" value="新增" />
            </c:if>
            <c:if test="${LOGIN_USER.isAdmin eq 'Y' || LOGIN_USER.isDelete eq 'Y'}">
                <input type="button" class="btnButton4font" onclick="ajax_deleteBatch('batch', 'value', '/product/deleteBatch');" value="批量删除" />
            </c:if>
            </td>  
        </tr>  
    </table> 
         
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="listTable">
    	<tr class="listTableHead">
    		<td width="2%" ><input type="checkbox" name="selectAll" onclick="setCheckBoxState('batch', this.checked);"/></td>
    		<td width="4%" >序号</td>
    		<td width="10%">产品</td>
    		<td width="10%">产品描述</td>
    		<td width="10%">EN目的</td>
    		<td width="6%">数量</td>
    		<td width="6%">LOT数量</td>
    		<td width="10%">投入/产出计划</td>
    		<td width="6%">进度</td>
    		<td width="10%">今日计划</td>
    		<td width="6%">产品信息</td>
    		<td width="6%">RCP&EQ</td>
    		<td width="6%">Lot进度</td>
    		<td nowrap="nowrap">操作</td>
    	</tr>
    	
    	<c:forEach items="${list}" var="obj"  varStatus="status">
	    	<c:set var="lineClass">listTableLine2</c:set>
	    	<c:if test="${status.index % 2 == 0 }">
	    		<c:set var="lineClass">listTableLine1</c:set>
	    	</c:if>
    		<tr class="${lineClass }">
	    		<td align="center"><input type="checkbox" name="batch" value="${obj.id}"/></td>
	    		<td align="center"><c:out value="${status.index + 1}"/></td>
	    		<td align="center"><c:out value="${obj.productId}"/></td>
	    		<td align="center"><c:out value="${obj.productName}"/></td>
	    		<td align="center"><c:out value="${obj.enAim}" default="&nbsp;"/></td>
	    		<td align="center"><c:out value="${obj.productCount}" default="&nbsp;"/></td>
	    		<td align="center"><c:out value="${obj.lotCount}" default="&nbsp;"/></td>
	    		<td align="center"><c:out value="${obj.productPlan}" default="&nbsp;"/></td>
	    		<td align="center"><c:out value="${obj.productStatus}" default="&nbsp;"/></td>
	    		<td align="center"><c:out value="${obj.todayPlan}" default="&nbsp;"/></td>
	    		<td align="center"><a href="javascript:void(0)" onclick="location_href('/product/info?id=${obj.id}');">点击进入</a></td>
	    		<td align="center"><a href="javascript:void(0)" onclick="location_href('/processflow/list?productId=${obj.id}');">点击进入</a></td>
	    		<td align="center">
	    		<a href="javascript:void(0)" onclick="location_href('/lotprogress/info?productId=${obj.id}');">详情</a>&nbsp;&nbsp;<a href="javascript:void(0)" onclick="location_href('/lotprogress/home?productId=${obj.id}');">管理</a></td>
	    		<td align="center">
	    		<c:if test="${LOGIN_USER.isAdmin eq 'Y' || LOGIN_USER.isModify eq 'Y'}">
	    			<a href="javascript:void(0)" onclick="openNewWin({url:'/product/modify?id=${obj.id}',width:600,height:250,winName:'modifyProduct'});">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;
	    		</c:if>
	    		<c:if test="${LOGIN_USER.isAdmin eq 'Y' || LOGIN_USER.isDelete eq 'Y'}">
	    			<a href="javascript:void(0)" onclick="ajax_delete('/product/delete', 'id=${obj.id}');">删除</a>
	    		</c:if>
	    		</td>
    		</tr>  
		</c:forEach>
		<c:if test="${list == null || fn:length(list) == 0}">
    		<tr class="listTableLine1">
    			<td colspan="14" align="center">系统中没有数据</td>
    		</tr>
    	</c:if>
	</table>
</body>
</html>
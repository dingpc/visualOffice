<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/page_header.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>工艺Spec列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/static/common/meta_base.jsp"%>
</head>
<body class="MainFrameBox">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="toolbarBottomLine">     
        <tr>  
        <td align="left"><span class="boldSpan">4.工艺Spec</span></td>
            <td align="right">
            <c:if test="${LOGIN_USER.isAdmin eq 'Y' || LOGIN_USER.isAdd eq 'Y'}">
                <input type="button" class="btnButton4font" onclick="openNewWin({url:'/productspec/add?productId=${productId}',width:600,height:220,winName:'addproductspec'});" value="新增" />
			</c:if>
            <c:if test="${LOGIN_USER.isAdmin eq 'Y' || LOGIN_USER.isDelete eq 'Y'}">
                <input type="button" class="btnButton4font" onclick="ajax_deleteBatch('batch', 'value', '/productspec/deleteBatch');" value="批量删除" />
			</c:if>
            </td>  
        </tr>  
    </table> 
         
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="listTable">
    	<tr class="listTableHead">
    		<td width="2%" rowspan="2"><input type="checkbox" name="selectAll" onclick="setCheckBoxState('batch', this.checked);"/></td>
    		<td width="4%" rowspan="2">序号</td>
    		<td rowspan="2">Layer</td>
    		<td colspan="3">CD</td>
    		<td colspan="3">T/F</td>
    		<td rowspan="2" nowrap="nowrap">操作</td>
    	</tr>
    	<tr class="listTableHead">
    		<td width="10%">Mask CD</td>
    		<td width="10%">DICD</td>
    		<td width="10%">FI CD</td>
    		<td width="12%">Material</td>
    		<td width="12%">Thick(Å)</td>
    		<td width="12%">Rs</td>
    	</tr>
    	
    	<c:forEach items="${list}" var="obj"  varStatus="status">
	    	<c:set var="lineClass">listTableLine2</c:set>
	    	<c:if test="${status.index % 2 == 0 }">
	    		<c:set var="lineClass">listTableLine1</c:set>
	    	</c:if>
    		<tr class="${lineClass }">
	    		<td align="center"><input type="checkbox" name="batch" value="${obj.id}"/></td>
	    		<td align="center"><c:out value="${status.index + 1}"/></td>
	    		<td align="center"><c:out value="${obj.layer}"/></td>
	    		<td align="center"><c:out value="${obj.maskcd}"/></td>
	    		<td align="center"><c:out value="${obj.dccd}" default="&nbsp;"/></td>
	    		<td align="center"><c:out value="${obj.ficd}" default="&nbsp;"/></td>
	    		<td align="center"><c:out value="${obj.material}" default="&nbsp;"/></td>
	    		<td align="center"><c:out value="${obj.thick}" default="&nbsp;"/></td>
	    		<td align="center"><c:out value="${obj.rs}" default="&nbsp;"/></td>
	    		<td align="center">
	    		<c:if test="${LOGIN_USER.isAdmin eq 'Y' || LOGIN_USER.isModify eq 'Y'}">
	    		<a href="javascript:void(0)" onclick="openNewWin({url:'/productspec/modify?id=${obj.id}',width:600,height:220,winName:'modifyproductspec'});">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;
	    		</c:if>
	    		<c:if test="${LOGIN_USER.isAdmin eq 'Y' || LOGIN_USER.isDelete eq 'Y'}">
	    		<a href="javascript:void(0)" onclick="ajax_delete('/productspec/delete', 'id=${obj.id}');">删除</a>
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
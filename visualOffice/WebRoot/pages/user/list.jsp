<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/page_header.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/static/common/meta_base.jsp"%>
</head>
<body class="MainFrameBox">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="toolbarBottomLine">     
        <tr>  
            <td align="right">
            <c:if test="${LOGIN_USER.isAdmin eq 'Y'}">
                <input type="button" class="btnButton4font" onclick="openNewWin({url:'/user/add',width:600,height:220,winName:'addUser'});" value="新增" />
                <input type="button" class="btnButton4font" onclick="ajax_deleteBatch('batch', 'value', '/user/deleteBatch');" value="批量删除" />
			</c:if>
            </td>  
        </tr>  
    </table>  
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="listTable">
    	<tr class="listTableHead">
    		<td width="2%" ><input type="checkbox" name="selectAll" onclick="setCheckBoxState('batch', this.checked);"/></td>
    		<td width="4%" >序号</td>
    		<td width="10%" >用户ID</td>
    		<td width="10%" >姓名</td>
    		<td width="10%" >管理员</td>
    		<td width="10%" >新增权限</td>
    		<td width="10%" >修改权限</td>
    		<td width="10%" >删除权限</td>
    		<td width="20%" >描述</td>
    		<td nowrap>操作</td>
    	</tr>
    	<c:forEach items="${userList}" var="user"  varStatus="status">
	    	<c:set var="lineClass">listTableLine2</c:set>
	    	<c:if test="${status.index % 2 == 0 }">
	    		<c:set var="lineClass">listTableLine1</c:set>
	    	</c:if>
    		<tr class="${lineClass }">
	    		<td align="center"><input type="checkbox" name="batch" value="${user.id}"/></td>
	    		<td align="center"><c:out value="${status.index + 1}"/></td>
	    		<td><c:out value="${user.userId}"/></td>
	    		<td><c:out value="${user.userName}"/></td>
	    		<td align="center"><c:out value="${user.isAdmin}" default="N"/></td>
	    		<td align="center"><c:out value="${user.isAdd}" default="N"/></td>
	    		<td align="center"><c:out value="${user.isModify}" default="N"/></td>
	    		<td align="center"><c:out value="${user.isDelete}" default="N"/></td>
	    		<td><c:out value="${user.description}" default="&nbsp;"/></td>
	    		<td align="center">
	    		<c:if test="${LOGIN_USER.isAdmin eq 'Y'}">
	    		<a href="javascript:void(0)" onclick="openNewWin({url:'/user/modify?id=${user.id}',width:600,height:220,winName:'addUser'});">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="ajax_delete('/user/delete', 'id=${user.id}');">删除</a>
	    		</c:if>
	    		</td>
    		</tr>  
		</c:forEach>  
		<c:if test="${userList == null || fn:length(userList) == 0}">
	   		<tr class="listTableLine1">
	   			<td colspan="10" align="center">系统中没有数据</td>
	   		</tr>
	   	</c:if>
	</table>
</body>
</html>
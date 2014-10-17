<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/page_header.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Lot列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/static/common/meta_base.jsp"%>
</head>
<body class="MainFrameBox">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="toolbarBottomLine">     
        <tr>  
            <td align="right">
	            <c:if test="${LOGIN_USER.isAdmin eq 'Y' || LOGIN_USER.isAdd eq 'Y'}">
	                <input type="button" class="btnButton4font" onclick="ajax_update_progress(this);" value="保存" />
	            </c:if>
            </td>  
        </tr>  
    </table>
    <c:set var="tdWidth" value="${80 / lotSize}" />
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="listTable">
    	<tr class="listTableHead">
    		<td rowspan="2">序号</td>
    		<td rowspan="2">工序</td>
    		<c:if test="${lotSize > 0 }">
    		<td colspan="${lotSize }">Lot进度（制造班组长）</td>
    		</c:if>
    	</tr>
    	<tr class="listTableLine2">
		    <c:forEach items="${lotList}" var="lot"  varStatus="status">
		    	<td align="center"><c:out value="${lot.lotName}"/></td>
		    </c:forEach>
    	</tr>
    	<c:forEach items="${list}" var="map"  varStatus="status">
    	<tr class="listTableLine1">
    	<td width="5%" align="center"><c:out value="${status.index + 1}"/></td>
    	<td nowrap="nowrap" align="center"><c:out value="${map['lotManage'].stepId}" /></td>
    		<c:forEach items="${map['progressList']}" var="obj"  varStatus="processStatus">
    			<c:set var="selectId">${map['lotManage'].id}####${obj[0]}</c:set>
    			<td align="center" width="${tdWidth}%">
	    			<c:choose>
	    				<c:when test="${obj[5] eq 'Y'}">
	    				<input type="checkbox" id="selectId${status}${processStatus}" name="selectId" value="${selectId }" checked="checked"/>
	    				</c:when>
	    				<c:otherwise>
	    				<input type="checkbox" id="selectId${status}${processStatus}" name="selectId" value="${selectId }"/>
	    				</c:otherwise>
	    			</c:choose>
    			</td>
    		</c:forEach>
    	</tr>
    	</c:forEach>
	</table>
</body>
<script type="text/javascript">
function ajax_update_progress(obj) {
	var checkedProgress = getCheckBoxData("selectId", "value");
	var notCheckedProgress = getNotCheckBoxData("selectId", "value");
	if(checkedProgress == '' && notCheckedProgress == '') {
		alert("没有需要保存的数据！");
		return false;
	}
	var formValue = "checkedProgress=" + checkedProgress + "&notCheckedProgress=" + notCheckedProgress + "&time=" + new Date();
	$.ajax({
		type : 'POST',
		url : '/lotprogress/save',
		data : formValue,
		dataType : 'json',
		success : function(data) {
			if(data) {
				if(data.success == "true") {
					alert("保存成功！");
				} else if(data.success == "isNotUnique") {
					alert("保存失败！\n数据重复，请重新填写！");
				} else {
					alert("保存失败！");
				}
			} else {
				alert("保存失败！");
			}
		},
		error : function(data) {
			alert("保存失败，请联系管理员！");
		}
	});
}
</script>
</html>

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
        	<td align="left">
	        	<input type="button" class="btnButton6font" onclick="location_href('/product/list');" value="返回主页面" />
	        </td>
            <td align="center">
            <c:if test="${fn:length(lotList) eq 0 && fn:length(list) eq 0}">
            	系统中无相关数据
            </c:if>
            </td>  
        </tr>  
    </table>
    <c:set var="tdWidth" value="${60 / lotSize}" />
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="listTable">
    	<tr class="listTableHead">
    		<td rowspan="2">工序</td>
    		<td rowspan="2">特殊要求</td>
    		<td colspan="4">生技担当确认</td>
    		<c:if test="${lotSize > 0 }">
    		<td colspan="${lotSize }">Lot进度（制造班组长）</td>
    		</c:if>
    	</tr>
    	<tr class="listTableLine2">
	    	<td class="listTableHeadSpecial">Glass抽取</td>
		    <td class="listTableHeadSpecial">Split</td>
		    <td class="listTableHeadSpecial">加测项目</td>
		    <td class="listTableHeadSpecial">签字</td>
		    <c:forEach items="${lotList}" var="lot"  varStatus="status">
		    	<td align="center"><c:out value="${lot.lotName}"/></td>
		    </c:forEach>
    	</tr>
    	<c:forEach items="${list}" var="map"  varStatus="status">
    	<tr class="listTableLine1">
    	<td width="10%" align="center"><c:out value="${map['lotManage'].stepId}" /></td>
    	<td width="10%" align="center"><c:out value="${map['lotManage'].specialRequire}" /></td>
    	<td width="5%" align="center"><c:out value="${map['lotManage'].glassExtra}" /></td>
    	<td width="5%" align="center"><c:out value="${map['lotManage'].splitInfo}" /></td>
    	<td width="5%" align="center"><c:out value="${map['lotManage'].addItem}" /></td>
    	<td width="5%" align="center"><c:out value="${map['lotManage'].signName}" /></td>
    		<c:forEach items="${map['progressList']}" var="obj"  varStatus="status">
    			<td align="center" width="${tdWidth}%">
	    			<c:choose>
	    				<c:when test="${obj[5] eq 'Y'}">★</c:when><c:otherwise>&nbsp;</c:otherwise>
	    			</c:choose>
    			</td>
    		</c:forEach>
    	</tr>
    	</c:forEach>
	</table>
</body>
</html>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/page_header.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>上传附件</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@ include file="/static/common/meta_base.jsp"%>
</head>
<body class="Pupwin">
	<div class="BodyMargin_10">  
		<div class="docBoxNoPanel">
			<form id="dataForm" name="dataForm" action="/file/upload" method="post" enctype="multipart/form-data">
				<input type="hidden" id="productId" name="productId" value="${productId}"/>
				<table width="100%" border="0" cellpadding="2" cellspacing="0" class="Table_bottomline">
					<tr>  
				        <td class="td_lefttitle" width="12%" nowrap="nowrap">请选择附件<span class="MustFillColor">*</span>：</td>  
				        <td width="38%" nowrap="nowrap">
				        	<input type="file" name="uploadFile" />
				       	</td> 
				       	<td class="td_lefttitle" width="12%" nowrap="nowrap">&nbsp;</td>  
				       	<td width="38%" nowrap="nowrap">&nbsp;</td>  
					</tr>
					
				   	<tr>  
				       <td class="td_lefttitle" width="12%" nowrap="nowrap"></td>  
				       <td width="38%" colspan="3" nowrap="nowrap">
				       	<input id="saveAndExit" name="saveAndExit" type="button" onclick="ajax_submit(0, this);" value="上传" class="btnButton4font"/>  
				       	<input id="exit" name="exit" type="button" onclick="window.close();" value="退出" class="btnButton4font"/>  
				        </td> 
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
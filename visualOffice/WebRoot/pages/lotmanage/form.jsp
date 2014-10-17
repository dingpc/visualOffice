<input type="hidden" id="id" name="id" value="${lotManage.id}"/>
<input type="hidden" id="productId" name="productId" value="${productId}"/>
<table width="100%" border="0" cellpadding="2" cellspacing="0" class="Table_bottomline">
	<tr>  
        <td for="工序" class="td_lefttitle" width="12%" nowrap="nowrap">工序<span class="MustFillColor">*</span>：</td>  
        <td width="38%" nowrap="nowrap">
        	<input type="text" id="stepId" name="stepId" class="inputText"  value="${lotManage.stepId}" boe-options="vtype:['notempty',{'maxLength':50}]"/>  
       	</td> 
       	<td for="特殊要求" class="td_lefttitle" width="12%" nowrap="nowrap">特殊要求：</td>  
       	<td width="38%" nowrap="nowrap">  
       		<input type="text" id="specialRequire" name="specialRequire" class="inputText" value="${lotManage.specialRequire}" boe-options="vtype:[{'maxLength':50}]"/>  
       	</td>  
	</tr>
	<tr>  
        <td for="Glass抽取" class="td_lefttitle" width="12%" nowrap="nowrap">Glass抽取：</td>  
        <td width="38%" nowrap="nowrap">
        	<input type="radio" name="glassExtra" value="Y" <c:if test="${lotManage.glassExtra eq 'Y'}">checked="true"</c:if> />是
			<input type="radio" name="glassExtra" value="N" <c:if test="${lotManage.glassExtra eq 'N'}">checked="true"</c:if> />否
       	</td> 
       	<td for="Split" class="td_lefttitle" width="12%" nowrap="nowrap">Split：</td>  
       	<td width="38%" nowrap="nowrap">  
       		<input type="radio" name="splitInfo" value="Y" <c:if test="${lotManage.splitInfo eq 'Y'}">checked="true"</c:if> />是
			<input type="radio" name="splitInfo" value="N" <c:if test="${lotManage.splitInfo eq 'N'}">checked="true"</c:if> />否
       	</td>  
	</tr>
	<tr>  
        <td for="加测项目" class="td_lefttitle" width="12%" nowrap="nowrap">加测项目：</td>  
        <td width="38%" nowrap="nowrap">
        	<input type="text" id="addItem" name="addItem" class="inputText"  value="${lotManage.addItem}" boe-options="vtype:[{'maxLength':20}]"/>  
       	</td> 
       	<td for="签字" class="td_lefttitle" width="12%" nowrap="nowrap">签字：</td>  
       	<td width="38%" nowrap="nowrap">  
       		<input type="text" id="signName" name="signName" class="inputText" value="${lotManage.signName}" boe-options="vtype:[{'maxLength':20}]"/>  
       	</td>  
	</tr>
   	<tr>  
       <td class="td_lefttitle" width="12%" nowrap="nowrap"></td>  
       <td width="38%" colspan="3" nowrap="nowrap">
       	<input id="saveAndExit" name="saveAndExit" type="button" onclick="ajax_submit(1, this);" value="保存" class="btnButton4font"/>  
       	<input id="exit" name="exit" type="button" onclick="window.close();" value="退出" class="btnButton4font"/>  
        </td> 
	</tr>
</table>
			
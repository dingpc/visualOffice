<input type="hidden" id="id" name="id" value="${processFlow.id}"/>
<input type="hidden" id="productId" name="productId" value="${productId}"/>
<table width="100%" border="0" cellpadding="2" cellspacing="0" class="Table_bottomline">
	<tr>  
        <td for="工序" class="td_lefttitle" width="12%" nowrap="nowrap">工序<span class="MustFillColor">*</span>：</td>  
        <td width="38%" nowrap="nowrap">
        	<input type="text" id="stepId" name="stepId" class="inputText"  value="${processFlow.stepId}" boe-options="vtype:['notempty',{'maxLength':50}]"/>  
       	</td> 
       	<td for="EQ" class="td_lefttitle" width="12%" nowrap="nowrap">EQ：</td>  
       	<td width="38%" nowrap="nowrap">  
       		<input type="text" id="equipName" name="equipName" class="inputText" value="${processFlow.equipName}" boe-options="vtype:[{'maxLength':50}]"/>  
       	</td>  
	</tr>
	<tr>  
        <td for="RCP" class="td_lefttitle" width="12%" nowrap="nowrap">RCP：</td>  
        <td width="38%" nowrap="nowrap">
        	<input type="text" id="rcp" name="rcp" class="inputText"  value="${processFlow.rcp}" boe-options="vtype:[{'maxLength':50}]"/>  
       	</td> 
       	<td for="Process Spec" class="td_lefttitle" width="12%" nowrap="nowrap">Process Spec：</td>  
       	<td width="38%" nowrap="nowrap">  
       		<input type="text" id="processSpec" name="processSpec" class="inputText" value="${processFlow.processSpec}" boe-options="vtype:[{'maxLength':50}]"/>  
       	</td>  
	</tr>
   	<tr>  
       <td class="td_lefttitle" width="12%" nowrap="nowrap"></td>  
       <td width="38%" colspan="3" nowrap="nowrap">
       	<input id="saveAndExit" name="saveAndExit" type="button" onclick="ajax_submit(0, this);" value="保存" class="btnButton4font"/>  
       	<input id="exit" name="exit" type="button" onclick="window.close();" value="退出" class="btnButton4font"/>  
        </td> 
	</tr>
</table>
			
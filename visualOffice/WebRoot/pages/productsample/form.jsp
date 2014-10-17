<input type="hidden" id="id" name="id" value="${productSample.id}"/>
<input type="hidden" id="productId" name="productId" value="${productId}"/>
<table width="100%" border="0" cellpadding="2" cellspacing="0" class="Table_bottomline">
	<tr>  
        <td for="工序" class="td_lefttitle" width="12%" nowrap="nowrap">工序<span class="MustFillColor">*</span>：</td>  
        <td width="38%" nowrap="nowrap">
        	<input type="text" id="stepId" name="stepId" class="inputText"  value="${productSample.stepId}" boe-options="vtype:['notempty',{'maxLength':20}]"/>  
       	</td> 
       	<td for="数量" class="td_lefttitle" width="12%" nowrap="nowrap">数量<span class="MustFillColor">*</span>：</td>  
       	<td width="38%" nowrap="nowrap">  
       		<input type="text" id="sampleCount" name="sampleCount" class="inputText" maxlength="20" value="${productSample.sampleCount}" boe-options="vtype:['notempty',{'maxLength':20},'p_integer']"/>  
       	</td>  
	</tr>
	<tr>  
        <td for="Glass信息" class="td_lefttitle" width="12%" nowrap="nowrap">Glass信息：</td>  
        <td width="38%" nowrap="nowrap">
        	<input type="text" id="glassId" name="glassId" class="inputText"  value="${productSample.glassId}" boe-options="vtype:[{'maxLength':20}]"/>  
       	</td> 
       	<td for="目的" class="td_lefttitle" width="12%" nowrap="nowrap">目的<span class="MustFillColor">*</span>：</td>  
       	<td width="38%" nowrap="nowrap">  
       		<input type="text" id="sampleAim" name="sampleAim" class="inputText" maxlength="20" value="${productSample.sampleAim}" boe-options="vtype:['notempty',{'maxLength':20}]"/>  
       	</td>  
	</tr>
	<tr>  
        <td for="相关科室1" class="td_lefttitle" width="12%" nowrap="nowrap">相关科室1：</td>  
        <td width="38%" nowrap="nowrap">
        	<input type="text" id="department1" name="department1" class="inputText"  value="${productSample.department1}" boe-options="vtype:[{'maxLength':20}]"/>  
       	</td> 
       	<td for="相关科室2" class="td_lefttitle" width="12%" nowrap="nowrap">相关科室2：</td>  
       	<td width="38%" nowrap="nowrap">  
       		<input type="text" id="department2" name="department2" class="inputText" maxlength="20" value="${productSample.department2}" boe-options="vtype:[{'maxLength':20}]"/>  
       	</td>  
	</tr>
	<tr>  
        <td for="相关科室3" class="td_lefttitle" width="12%" nowrap="nowrap">相关科室3：</td>  
        <td width="38%" nowrap="nowrap">
        	<input type="text" id="department3" name="department3" class="inputText"  value="${productSample.department3}" boe-options="vtype:[{'maxLength':20}]"/>  
       	</td> 
       	<td width="12%" nowrap="nowrap">&nbsp;</td>  
       	<td width="38%" nowrap="nowrap">  
       		&nbsp;  
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
			
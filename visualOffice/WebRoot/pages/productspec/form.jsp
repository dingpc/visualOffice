<input type="hidden" id="id" name="id" value="${productSpec.id}"/>
<input type="hidden" id="productId" name="productId" value="${productId}"/>
<table width="100%" border="0" cellpadding="2" cellspacing="0" class="Table_bottomline">
	<tr>  
        <td for="Layer" class="td_lefttitle" width="12%" nowrap="nowrap">Layer<span class="MustFillColor">*</span>：</td>  
        <td width="38%" nowrap="nowrap">
        	<input type="text" id="layer" name="layer" class="inputText"  value="${productSpec.layer}" boe-options="vtype:['notempty',{'maxLength':20}]"/>  
       	</td> 
       	<td for="Mask CD" class="td_lefttitle" width="12%" nowrap="nowrap">Mask CD：</td>  
       	<td width="38%" nowrap="nowrap">  
       		<input type="text" id="maskcd" name="maskcd" class="inputText" maxlength="20" value="${productSpec.maskcd}" boe-options="vtype:[{'maxLength':10}]"/>  
       	</td>  
	</tr>
	<tr>  
        <td for="DICD" class="td_lefttitle" width="12%" nowrap="nowrap">DICD：</td>  
        <td width="38%" nowrap="nowrap">
        	<input type="text" id="dccd" name="dccd" class="inputText"  value="${productSpec.dccd}" boe-options="vtype:[{'maxLength':10}]"/>  
       	</td> 
       	<td for="FI CD" class="td_lefttitle" width="12%" nowrap="nowrap">FI CD：</td>  
       	<td width="38%" nowrap="nowrap">  
       		<input type="text" id="ficd" name="ficd" class="inputText" maxlength="20" value="${productSpec.ficd}" boe-options="vtype:[{'maxLength':10}]"/>  
       	</td>  
	</tr>
	<tr>  
        <td for="Material" class="td_lefttitle" width="12%" nowrap="nowrap">Material：</td>  
        <td width="38%" nowrap="nowrap">
        	<input type="text" id="material" name="material" class="inputText"  value="${productSpec.material}" boe-options="vtype:[{'maxLength':20}]"/>  
       	</td> 
       	<td for="Thick" class="td_lefttitle" width="12%" nowrap="nowrap">Thick：</td>  
       	<td width="38%" nowrap="nowrap">  
       		<input type="text" id="thick" name="thick" class="inputText" maxlength="20" value="${productSpec.thick}" boe-options="vtype:[{'maxLength':20}]"/>  
       	</td>  
	</tr>
	<tr>  
        <td for="Rs" class="td_lefttitle" width="12%" nowrap="nowrap">Rs：</td>  
        <td width="38%" nowrap="nowrap">
        	<input type="text" id="rs" name="rs" class="inputText"  value="${productSpec.rs}" boe-options="vtype:[{'maxLength':20}]"/>  
       	</td> 
       	<td class="td_lefttitle" width="12%" nowrap="nowrap">&nbsp;</td>  
       	<td width="38%" nowrap="nowrap">&nbsp;</td>  
	</tr>
	<tr>  
       <td class="td_lefttitle" width="12%" nowrap="nowrap"></td>  
       <td width="38%" colspan="3" nowrap="nowrap">
       	<input id="saveAndExit" name="saveAndExit" type="button" onclick="ajax_submit(0, this);" value="保存" class="btnButton4font"/>  
       	<input id="exit" name="exit" type="button" onclick="window.close();" value="退出" class="btnButton4font"/>  
        </td> 
	</tr>
</table>
			
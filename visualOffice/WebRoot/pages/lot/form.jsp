<input type="hidden" id="id" name="id" value="${lot.id}"/>
<input type="hidden" id="productId" name="productId" value="${productId}"/>
<table width="100%" border="0" cellpadding="2" cellspacing="0" class="Table_bottomline">
	<tr>  
        <td for="Lot名称" class="td_lefttitle" width="12%" nowrap="nowrap">Lot名称<span class="MustFillColor">*</span>：</td>  
        <td width="38%" nowrap="nowrap">
        	<input type="text" id="lotName" name="lotName" class="inputText"  value="${lot.lotName}" boe-options="vtype:['notempty',{'maxLength':50}]"/>  
       	</td> 
       	<td class="td_lefttitle" width="12%" nowrap="nowrap">&nbsp;</td>  
       	<td width="38%" nowrap="nowrap">&nbsp;</td>  
	</tr>
	
   	<tr>  
       <td class="td_lefttitle" width="12%" nowrap="nowrap"></td>  
       <td width="38%" colspan="3" nowrap="nowrap">
       	<input id="saveAndExit" name="saveAndExit" type="button" onclick="ajax_submit(1, this);" value="保存" class="btnButton4font"/>  
       	<input id="exit" name="exit" type="button" onclick="window.close();" value="退出" class="btnButton4font"/>  
        </td> 
	</tr>
</table>
			
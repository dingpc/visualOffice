<input type="hidden" id="id" name="id" value="${product.id}"/>
<table width="100%" border="0" cellpadding="2" cellspacing="0" class="Table_bottomline">
	<tr>  
        <td for="产品ID" class="td_lefttitle" width="12%" nowrap="nowrap">产品ID<span class="MustFillColor">*</span>：</td>  
        <td width="38%" nowrap="nowrap">
        	<input type="text" id="productId" name="productId" class="inputText"  value="${product.productId}" boe-options="vtype:['notempty',{'maxLength':20}]"/>  
       	</td> 
       	<td for="产品名称" class="td_lefttitle" width="12%" nowrap="nowrap">产品名称<span class="MustFillColor">*</span>：</td>  
       	<td width="38%" nowrap="nowrap">  
       		<input type="text" id="productName" name="productName" class="inputText" value="${product.productName}" boe-options="vtype:['notempty',{'maxLength':20}]"/>  
       	</td>  
	</tr>
	<tr>  
        <td for="EN目的" class="td_lefttitle" width="12%" nowrap="nowrap">EN目的：</td>  
        <td width="38%" nowrap="nowrap">
        	<input type="text" id="enAim" name="enAim" class="inputText"  value="${product.enAim}" boe-options="vtype:[{'maxLength':20}]"/>  
       	</td> 
       	<td for="产品数量" class="td_lefttitle" width="12%" nowrap="nowrap">产品数量<span class="MustFillColor">*</span>：</td>  
       	<td width="38%" nowrap="nowrap">  
       		<input type="text" id="productCount" name="productCount" class="inputText" value="${product.productCount}" boe-options="vtype:['notempty',{'maxLength':20},'p_integer']"/>  
       	</td>  
	</tr>
	<tr>  
        <td for="投入/产出计划" class="td_lefttitle" width="12%" nowrap="nowrap">投入/产出计划：</td>  
        <td width="38%" nowrap="nowrap">
        	<input type="text" id="productPlan" name="productPlan" class="inputText"  value="${product.productPlan}" boe-options="vtype:[{'maxLength':20}]"/>  
       	</td> 
       	<td for="今日计划" class="td_lefttitle" width="12%" nowrap="nowrap">今日计划：</td>  
       	<td width="38%" nowrap="nowrap">  
       		<input type="text" id="todayPlan" name="todayPlan" class="inputText" value="${product.todayPlan}" boe-options="vtype:[{'maxLength':20}]"/>  
       	</td>  
	</tr>
	<tr>  
        <td for="产品经理" class="td_lefttitle" width="12%" nowrap="nowrap">产品经理：</td>  
        <td width="38%" nowrap="nowrap">
        	<input type="text" id="productManager" name="productManager" class="inputText"  value="${product.productManager}" boe-options="vtype:[{'maxLength':20}]"/>  
       	</td> 
       	<td for="产品负责人" class="td_lefttitle" width="12%" nowrap="nowrap">产品负责人：</td>  
       	<td width="38%" nowrap="nowrap">  
       		<input type="text" id="productLeader" name="productLeader" class="inputText" value="${product.productLeader}" boe-options="vtype:[{'maxLength':20}]"/>  
       	</td>  
	</tr>
	<tr>  
       	<td for="进度" class="td_lefttitle" width="12%" nowrap="nowrap">进度：</td>  
       	<td width="38%" nowrap="nowrap">  
			<input type="text" id="productStatus" name="productStatus" class="inputText" value="${product.productStatus}" boe-options="vtype:[{'maxLength':20}]"/>  
       	</td>  
       	<td for="LOT数量" class="td_lefttitle" width="12%" nowrap="nowrap">LOT数量</td>  
       	<td width="38%" nowrap="nowrap">
			<input type="text" id="lotCount" name="lotCount" class="inputText" value="${product.lotCount}" boe-options="vtype:['notempty',{'maxLength':20},'p_integer']"/>
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
			
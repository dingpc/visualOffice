
			<input type="hidden" id="id" name="id" value="${user.id}"/>
				<table width="100%" border="0" cellpadding="2" cellspacing="0" class="Table_bottomline">
					<tr>  
				        <td class="td_lefttitle" width="12%" nowrap="nowrap">UserID<span class="MustFillColor">*</span>：</td>  
				        <td width="38%" nowrap="nowrap">
				        	<input type="text" id="userId" name="userId" class="inputText"  maxlength="10" value="${user.userId}"/>  
				        </td> 
				        <td class="td_lefttitle" width="12%" nowrap="nowrap">姓名<span class="MustFillColor">*</span>：</td>  
				        <td width="38%" nowrap="nowrap">  
				        	<input type="text" id="userName" name="userName" class="inputText" maxlength="20" value="${user.userName}"/>  
				        </td>  
				    </tr>
				    <tr>  
				        <td class="td_lefttitle" width="12%" nowrap="nowrap">密码<span class="MustFillColor">*</span>：</td>  
				        <td width="38%" nowrap="nowrap">
				        	<input type="text" id="password" name="password" class="inputText" maxlength="10" value="${user.password}"/>  
				        </td> 
				        <td class="td_lefttitle" width="12%" nowrap="nowrap">备注：</td>  
				        <td width="38%" nowrap="nowrap">  
				        	<input type="text" id="description" name="description" class="inputText"  maxlength="50" value="${user.description}"/>  
				        </td>  
				    </tr>
				    <tr>  
				        <td class="td_lefttitle" width="12%" nowrap="nowrap">管理员：</td>  
				        <td width="38%" nowrap="nowrap">
				        	<input type="radio" name="isAdmin" value="Y" <c:if test="${user.isAdmin eq 'Y'}">checked="true"</c:if> />是
				        	<input type="radio" name="isAdmin" value="N" <c:if test="${user.isAdmin eq 'N'}">checked="true"</c:if> />否
				        </td> 
				        <td class="td_lefttitle" width="12%" nowrap="nowrap">新增权限：</td>  
				        <td width="38%" nowrap="nowrap">
				        	<input type="radio" name="isAdd" value="Y" <c:if test="${user.isAdd eq 'Y'}">checked="true"</c:if> />是
				        	<input type="radio" name="isAdd" value="N" <c:if test="${user.isAdd eq 'N'}">checked="true"</c:if>/>否  
				        </td>  
				    </tr>
				    <tr>  
				        <td class="td_lefttitle" width="12%" nowrap="nowrap">修改权限：</td>  
				        <td width="38%" nowrap="nowrap">
				        	<input type="radio" name="isModify" value="Y"  checked="checked"/>是
				        	<input type="radio" name="isModify" value="N" />否
				        </td> 
				        <td class="td_lefttitle" width="12%" nowrap="nowrap">删除权限：</td>  
				        <td width="38%" nowrap="nowrap">
				        	<input type="radio" name="isDelete" value="Y"  checked="checked"/>是
				        	<input type="radio" name="isDelete" value="N" />否  
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
			
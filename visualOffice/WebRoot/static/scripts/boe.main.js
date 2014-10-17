/**
 * 打开新窗口，请根据自己的需要传入修改后的json串.
 * @param winJson   弹出窗口的属性json串.
 * <p>有以下默认值：</br>
 	{</br>
	    url: '',					// 业务访问的url地址.</br>
		isPost: false,     			// 是否要求post方式打开，true:要求，false:不要求.</br>
		isFull: false,	     		// 是否要求全屏打开，true:要求，false:不要求.</br>
		width:  300,				// 窗口宽度.</br>
		height: 150,				// 窗口高度.</br>
		isScroll: 'yes',			// 是否允许滚动条，'yes':允许，'no':不允许.</br>
		isResizable: 'yes',			// 是否允许调节窗口大小，'yes':允许，'no':不允许.</br>
		winName: 'winName'			// 窗口名称.</br>
	}</br>
 * </p>
 */
function openNewWin(winJson) {
	var winJson_ = {
		url : winJson.url || '',
		isPost : winJson.isPost || true,
		isFull : winJson.isFull || false,
		width : winJson.width || 300,
		height : winJson.height || 150,
		isScroll : winJson.isScroll || 'yes',
		isResizable : winJson.isResizable || 'yes',
		winName : winJson.winName || 'winName'
	};
	if (winJson_.isFull) {
		winJson_.width = screen.availWidth;
		winJson_.height = screen.availHeight;
	}
	var l = (screen.availWidth - winJson_.width) / 2;
	var t = (screen.availHeight - winJson_.height) / 2;
	var s = 'width=' + winJson_.width + ', height=' + winJson_.height
			+ ', top=' + t + ', left=' + l;
	s += ', toolbar=no, scrollbars='
			+ winJson_.isScroll
			+ ', menubar=no, locations=0,location=no, status=no, status=0,resizable='
			+ winJson_.isResizable;
	if (winJson_.isPost) {
		UTFWindowOpen(winJson_.url, winJson_.winName, s);
	} else {
		window.open(encodeURI(winJson_.url), winJson_.winName, s);
	}
}

// 被openWin方法调用
function UTFWindowOpen(sURL, winName, features) {
	var oW;
	oW = window.open('', winName, features);
	oW.document.open();
	var contents = "";
	var mainUrl = "";
	if (sURL.indexOf("?") > 0) {
		// 分解URL,第二的元素为完整的查询字符串
		var arrayParams = sURL.split("?");
		// 分解查询字符串
		var arrayURLParams = arrayParams[1].split("&");
		mainUrl = arrayParams[0];
		// 遍历分解后的键值对
		for (var i = 0; i < arrayURLParams.length; i++) {
			// 分解一个键值对
			var sParam = arrayURLParams[i].split("=");
			if ((sParam[0] != "") && (sParam[1] != "")) {
				contents += " <input  type=\"hidden\"   name=\"" + sParam[0]
						+ "\"  value=\"" + sParam[1] + "\" >";
			}
		}
	} else {
		mainUrl = sURL;
	}
	oW.document.write('<form   name="postform" id="postform"   action="'
			+ mainUrl + '"  method= "post" accept-charset="utf-8" >' + contents
			+ '</form>');
	oW.document.close();
	$("#postform", oW.document).submit();
}

/**
 * 将表单的值转化成JSON方式的字符串
 */
$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name]) {
			if (!o[this.name].push) {
				o[this.name] = [ o[this.name] ];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
};

/**
 * Ajax方式提交表单
 * n=1,针对lotmanage和lot
 */
function ajax_submit(n, obj) {
	var formId = $(obj).parents("form").attr("id");
	var formValue = $.toJSON($("#" + formId).serializeObject());
	var validation = validateForm(formId);
	if(validation) {
	$.ajax({
		headers: { 
	        'Accept': 'application/json',
	        'Content-Type': 'application/json' 
	    },
		type : 'POST',
		contentType : 'application/json; charset=utf-8',
		url : $("#" + formId).attr("action"),
		data : formValue,
		dataType : 'json',
		success : function(data) {
			if(data) {
				if(data.success == "true") {
					alert("保存成功！");
					window.opener.location.reload();
					if(n == 1) {
						window.opener.parent.frames["progressTop"].location.reload();
					}
					window.close();
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
}


/**
 * Ajax方式删除数据
 */
function ajax_delete(url, data_str) {
	if(confirm("确认要删除数据吗？")) {
	$.ajax({
		type : 'POST',
		url : url,
		data : data_str + '&date='+ Math.random(),
		dataType : 'json',
		success : function(data) {
			if(data) {
				if (data.success == "true") {  
					alert("删除成功！");
					window.location.reload();
				} else if (data.success == "false") {
					alert("删除失败！");
				} else if (data.success == "relation") {
					alert("此数据和其他数据具有关联性，无法删除！");
				}
			}
		},
		error : function(data) {
			alert("删除失败，请联系管理员！");
		}
	});
	}
}

/**
 * Ajax方式批量删除数据
 */
function ajax_deleteBatch(checkboxName, attr_name, url) {
	var idStr = getCheckBoxData(checkboxName, attr_name);
	if(idStr == "") {
		alert("请选择需要删除的数据！");
		return false;
	}
	if(confirm("确认要删除数据吗？")) {
	$.ajax({
		type : 'POST',
		url : url,
		data : 'idStr=' + idStr + '&date='+ Math.random(),
		dataType : 'json',
		success : function(data) {
			if(data) {
				if (data.success == "true") {  
					alert("批量删除成功！");
					window.location.reload();
				} else if (data.success == "false") {
					alert("批量删除失败！");
				} else if (data.success == "relation") {
					alert("你所需要删除的数据和其他数据具有关联性，无法删除！");
				}
			}
		},
		error : function(data) {
			alert("批量删除失败，请联系管理员！");
		}
	});
	}
}

function setCheckBoxState(checkboxName, state) {
	$("input[name="+checkboxName+"]").each(function(){
		$(this).prop("checked",state);	
		if(state){
			$(this).parent().addClass("checked");
		}else{
			$(this).parent().removeClass("checked");
		}
	});
}

function getCheckBoxData(checkboxName, attr_name) {
	var r = "" ;
    $("input[name="+checkboxName+"]").each(function(){
		if($(this).prop("checked")==true ){
			r = r + $(this).attr(attr_name)+",";
		}
    });
	if(r.indexOf(",")>0){
		r = r.substring(0, r.length-1);
	}
    return r;
}

function getNotCheckBoxData(checkboxName, attr_name) {
	var r = "" ;
    $("input[name="+checkboxName+"]").each(function(){
		if($(this).prop("checked")==false ){
			r = r + $(this).attr(attr_name)+",";
		}
    });
	if(r.indexOf(",")>0){
		r = r.substring(0, r.length-1);
	}
    return r;
}

/**
 * 跳转地址转为href方式，保证refer存在.
 * @param url  跳转地址.
 */	
function location_href(url){
	if(document.all){
		if ($.browser.msie && ($.browser.version == "6.0") ) { 
              document.write("<a href='javascript:void(0);' id='boe_alink_tttt' onclick='window.location.href=\""+url+"\";return false;' ></a>");         
	          document.close();
			  document.getElementById('boe_alink_tttt').click();
        }else{		  
			var gotoLink = document.createElement('a');  
			gotoLink.href = url;  
			document.body.appendChild(gotoLink);
		    gotoLink.click();		
		} 
	}else{
	    window.location.href=url;
	}
}



/**
* 创建动态form,并提交.
* @param formJson   参数json格式：{formId:'列表页查询表单的ID', action:'提交url地址' }.
*/
function commonSubmitDynamicForm(formJson) {
	//序列化列表元素 返回 JSON 数据结构数据
   var formId = formJson.formId;
   var params = false;
   if(formId!=undefined && formId!=''){
   	params = $("#"+formId).serializeArray();
   }
   var method = formJson.method || 'post';
   var target = formJson.target || '_self';
   var $form = createDynamicForm({id:'exportForm',target:target, action:formJson.action,params:params,method:method});
   if($form) {
       $form.submit();
   }
}


/**
* 创建动态form，参数json格式：{id:'表单ID', action:'提交url地址', method:'提交方式get or post， 默认：get'（可选）, params:参数（可选）, dest:目标对象（可选），默认'body'}
* params说明：
* [ 
*     {name: 'param1', value: 'val1'}, 
*     {name: 'param2', value: 'val2'}
* ]
* @return 返回form对象
*/
function createDynamicForm(formJson) {
   if (formJson == null || formJson.id == undefined) return null;

   var $form = $('#' + formJson.id);
   if ($form.length > 0) {
       $form.remove();
   }
   var id = formJson.id;
   var action = formJson.action;
   var method = formJson.method || 'get';
   var target = formJson.target || '_self';
   
   var  contents = "";
   var  mainUrl = ""; 
   if (action.indexOf("?") > 0){
      //分解URL,第二的元素为完整的查询字符串
      var arrayParams = action.split("?");   
      //分解查询字符串
      	   mainUrl=arrayParams[0];   
      var arrayURLParams = arrayParams[1].split("&");
      //遍历分解后的键值对
      for (var i = 0; i < arrayURLParams.length; i++){       
         //分解一个键值对
         var sParam =  arrayURLParams[i].split("=");  
         //if ((sParam[0] != "") && (sParam[1] != "")){ 
			  contents+=" <input  type=\"hidden\"   name=\""+sParam[0] +"\"  value=\""+sParam[1]+"\" >";	 
         //}
      }  
   }else{
	  	mainUrl=action;
   }
  
   
   $form = $('<form id="' + id + '" name="' + id + '" action="' + mainUrl + '" method="' + method + '" target="' + target + '" enctype="application/x-www-form-urlencoded" />');
	
	$(contents).appendTo($form);
	
   var params = formJson.params || undefined;
   if (params) {
       for (var i = 0; i < params.length; i++) {
           $('<input type="hidden" name="' + params[i].name + '" value="' + (params[i].value ? params[i].value : "") + '"/>').appendTo($form);
       }
   }
  
   

   var dest = formJson.dest || 'body';
   $form.appendTo(dest);
   return $form[0];
}

/**
 * 清空表单中的数据
 * @param formId
 */
function clearForm(formId) {
	$(':input','#' + formId)
		.not(':button, :submit, :reset, :hidden')
		.val('')
		.removeAttr('checked')
		.removeAttr('selected');
}


/**
 * 表单验证.
 * @param formId 需要验证的表单的id.
 * @return       true:验证通过；false:验证不通过.
 */
function validateForm(formId) {
	var validation = true;
	$("#" + formId)
	.find("input[class^='inputText'],textarea[class^='inputText'],.selectlist,.inputTextarea,.Wdate,.htmleditor,select[class^='easyui-combobox']")
	.each(function() {
		if ($(this).attr("boe-options") == undefined) {
			return true;
		}

		var boe_options = eval("({"
				+ $(this).attr("boe-options") + "})");

		var class_ = $(this).attr('class');
		var obj = this;
		var value = $.trim($(this).val());
		if ($(obj).attr("class").indexOf("easyui-combobox") >= 0) {
			value = $(
					"input[name='" + $(obj).attr("comboname")
							+ "']").eq(0).val();
		}
		var promptText = boe_options.promptText;
		if (promptText != undefined && value == promptText) {
			value = "";
		}
		if (class_ == 'htmleditor') {
			value = KindEditor.instances[0].html();
			$(this).val(value);
		}
		var text = $(this).closest("td").prev().attr("for");
		// 添加子表验证查找for规则
		if (text == undefined) {
			var td = $(this).parent();
			var tr = td.parent();
			var table = tr.parent();
			var index = td.index();
			var ftd = table.find("tr:first").find("td").eq(
					index);
			text = $(ftd).attr("for");
		}

		var tip = "";
		var vtype = boe_options.vtype;
		if (vtype != undefined) {
			vtype = eval(vtype);
			for (var i = 0; i < vtype.length; i++) {
				var vtype_ = vtype[i];
				if (vtype_ == 'notempty' || value != '') {
					// if((vtype_+"").indexOf('decimal')>=0 &&
					// value.indexOf('.') < 0){
					// value += ".0";
					// }
					tip = myValidate(value, vtype_);
					if (tip != "" && tip != true) {
						break;
					}
				}
			}
		}
		if (tip === false) {
			validation = false;
			return false;
		} else if (tip != "" && tip != true) {
			text += tip;
		}

		if (tip != "" && tip != true) {
			validation = false;
			alert(text);
		}

	});
	// prompt提交置空
	if (validation) {
		$("input[class^='input'],.inputTextarea").each(function() {
			if ($(this).attr('boe-options') != undefined) {
				var boe_options = eval("({" + $(this).attr("boe-options") + "})");
				var promptText = boe_options.promptText;
				if (promptText != undefined) {
					if ($.trim($(this).val()) == promptText) {
						$(this).val("");
					}
				}
			}
		});
	}

	return validation;
}

/**
 * Ajax 导入Excel
 * @param obj
 */
function ajax_import(obj){
	$("input[type='file']").each(function() {
		var fileName = $(this).val();
		if(fileName == '') {
			alert("请选择一个文件！");
			return false;
		}
	});
	var formId = $(obj).parents("form").attr("id");
	$("#" + formId).ajaxSubmit({
		url : $("#" + formId).attr("action"),
		type : "post",
		enctype : "multipart/form-data",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		dataType: "json",
		success : function(data) {
			if(data) {
				var result = data.success;
				if (result == "true") {
					alert("导入成功！");
					window.opener.location.reload();
					window.close();
				} else if (result == "formatError") {
					alert("导入失败！\n导入的文件不是Excel！");
				} else if (result == "colError") {
					alert("导入失败！\n导入的Excel列数错误，请参照模板！");
				} else if (result == "empty") {
					alert("导入失败！\n文件为空！");
				} else if (result == "rowLimit") {
					alert("导入失败！\nExcel的行数应该在2~100之间！");
				} else if (result == "isNotUnique") {
					alert("导入失败！\n导入的StepId不能和已有数据相同！");
				} else if (result == "excelRepeat") {
					alert("导入失败！\nExcel中的数据的StepId不能重复！");
				} else if (result == "saveError") {
					alert("导入失败！\n批量导入失败，请重试！");
				} else {
					alert("导入失败！");
				}
			} else {
				alert("导入失败！请联系管理员");
			}
		},
		error : function() {
			alert("系统错误！请联系管理员");
		}
	});
}

function refreshListForm(formId){
	$("#"+formId).submit();
}

var regexEnum = 
{
	//数字					
	digit:{regex:"^([+-]?)\\d*\\.?\\d+$",message:comm.v_digit},
	//整数
	integer:{regex:"^-?\\d+$",message:comm.v_integer},
	//正整数		*		
	p_integer:{regex:"^[0-9]*[1-9][0-9]*$",message:comm.v_p_integer},
	//负整数					
	n_integer:{regex:"^^-[1-9]d*$",message:comm.v_n_integer},	
	//正整数 + 0		
	p_integer_e:{regex:"^[1-9]d*|0$",message:comm.v_p_integer_e},	
	//负整数 + 0				
	n_integer_e:{regex:"^^-[1-9]d*|0$",message:comm.v_n_integer_e},
	//浮点数字（必须有小数点）					
	digit_e:{regex:"^([+-]?)\\d*\\.\\d+$",message:comm.v_digit_e},
	//浮点数　 
	decimal:{regex:"^(-?\\d+)(\\.\\d+)?$",message:comm.v_decimal},
	//正浮点数			
	p_decimal:{regex:"^[1-9]d*|0$|^[1-9]d*.d*|0.d*[1-9]d*$",message:comm.v_p_decimal},
	//负浮点数　　
	n_decimal:{regex:"^-([1-9]d*.d*|0.d*[1-9]d*)$",message:comm.v_n_decimal},	
	//非负浮点数（正浮点数 + 0）　 
	p_decimal_e:{regex:"^[1-9]d*$|^[1-9]d*.d*|0.d*[1-9]d*|0?.0+|0$",message:comm.v_p_decimal_e},
	//非正浮点数（负浮点数 + 0）　　 
	n_decimal_e:{regex:"^(-([1-9]d*.d*|0.d*[1-9]d*))|0?.0+|0$",message:comm.v_n_decimal_e},
	//邮件　　
	email:{regex:"^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$",message:comm.v_format},
	//颜色 
	color:{regex:"^[a-fA-F0-9]{6}$",message:comm.v_format},
	//url				
	url:{regex:"^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$",message:comm.v_format},
	//仅中文	
	chinese:{regex:"^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$",message:comm.v_chinese},
	//仅ASCII字符				
	ascii:{regex:"^[\\x00-\\xFF]+$",message:comm.v_ascii},
	//邮编				
	zipcode:{regex:"^\\d{6}$",message:comm.v_format},
	//手机						
	mobile:{regex:"^13[0-9]{9}$|15[012356789][0-9]{8}$|18[0256789][0-9]{8}$|147[0-9]{8}$",message:comm.v_format},
	//ip4地址				
	ip4:{regex:"^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$",message:comm.v_format},
	//非空 'notempty'
	notempty:{regex:notempty,message:comm.v_notempty},
	//图片						
	picture:{regex:"(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$",message:comm.v_format},
	//压缩文件	
	rar:{regex:"(.*)\\.(rar|zip|7zip|tgz)$",message:comm.v_format},
	//日期								
	date:{regex:"^\\d{4}(\\-|\\/|\.)\\d{1,2}\\1\\d{1,2}$",message:comm.v_format},
	//QQ号码					
	qq:{regex:"^[1-9]*[1-9][0-9]*$",message:comm.v_format},
	//电话号码的函数(包括验证国内区号,国际区号,分机号)				
	tel:{regex:"^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$",message:comm.v_format},
	//用来用户注册。匹配由数字、26个英文字母或者下划线组成的字符串
	d_english_u:{regex:"^\\w+$",message:comm.v_d_english_u},
	//字母						
	letter:{regex:"^[A-Za-z]+$",message:comm.v_letter},
	//大写字母				
	letter_u:{regex:"^[A-Z]+$",message:comm.v_letter_u},
	//小写字母				
	letter_l:{regex:"^[a-z]+$",message:comm.v_letter_l},
	//身份证				
	idcard:{regex:function(value,r_value){return isCardID(value);},message:comm.v_format},
	//最大长度  maxLength:50
	maxLength:{regex:maxLength,message:""},
	//最小长度   minLength:5
	minLength:{regex:minLength,message:""},
	//字符串长度 rangeLength:'2-10'
	rangeLength:{regex:rangeLength,message:""},
	//数字范围   range:'2-10'
	range:{regex:range,message:""},
	//特殊字符
	spechar:{regex:spechar,message:""},
	//特殊字符1
	spechar1:{regex:spechar1,message:""},
	//特殊字符2
	spechar2:{regex:spechar2,message:""},
	//特殊字符3
	spechar3:{regex:spechar3,message:""},
	onvalidation:{regex:whirValidation,message:""},
	point:{regex:whirPoint,message:""}

}


function spechar(value,r_value){
	var speCharArr = r_value.split(",");
	for(var i=0;i<speCharArr.length;i++ ){
		if(speCharArr[i]!='' && value.indexOf(speCharArr[i])>=0){
			return comm.v_notinclude + replaceAll(r_value,",","，")+ comm.v_specialchar;
		}
	}
	return "";
}

function whirPoint(value,r_value){
	return "";
}


function spechar1(value,r_value){
	return spechar(value,"#,&,\'");
}

function spechar2(value,r_value){
	return spechar(value,"?,#,&,\'");
}

function spechar3(value,r_value){
	return spechar(value,"\\\,/,?,#,&,\',\"");
}

function whirValidation(value,r_value){
	return r_value.call(r_value,value);
}


function notempty(value,r_value){	
	if($.trim(value) == ""){
		return comm.v_notempty;
	}
	return "";
}

var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"} 

function isCardID(sId){ 
	var iSum=0 ;
	var info="" ;
	if(!/^\d{17}(\d|x)$/i.test(sId)) return comm.v_lengthorformaterror; 
	sId=sId.replace(/x$/i,"a"); 
	if(aCity[parseInt(sId.substr(0,2))]==null) return comm.v_areaillegal; 
	sBirthday=sId.substr(6,4)+"-"+Number(sId.substr(10,2))+"-"+Number(sId.substr(12,2)); 
	var d=new Date(sBirthday.replace(/-/g,"/")) ;
	if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate()))return comm.v_birthillegal; 
	for(var i = 17;i>=0;i --) iSum += (Math.pow(2,i) % 11) * parseInt(sId.charAt(17 - i),11) ;
	if(iSum%11!=1) return comm.v_illegal; 
	return true;//aCity[parseInt(sId.substr(0,2))]+","+sBirthday+","+(sId.substr(16,1)%2?"男":"女") 
}  

//短时间，形如 (13:04:06)
function isTime(str)
{
	var a = str.match(/^(\d{1,2})(:)?(\d{1,2})\2(\d{1,2})$/);
	if (a == null) {return false}
	if (a[1]>24 || a[3]>60 || a[4]>60)
	{
		return false;
	}
	return true;
}

//短日期，形如 (2003-12-05)
function isDate(str)
{
	var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/); 
	if(r==null)return false; 
	var d= new Date(r[1], r[3]-1, r[4]); 
	return (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]);
}

//长时间，形如 (2003-12-05 13:04:06)
function isDateTime(str)
{
	var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/; 
	var r = str.match(reg); 
	if(r==null) return false; 
	var d= new Date(r[1], r[3]-1,r[4],r[5],r[6],r[7]); 
	return (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]&&d.getHours()==r[5]&&d.getMinutes()==r[6]&&d.getSeconds()==r[7]);
}


function maxLength(value,r_value){	
	//alert("value:"+value+",value.length:"+value.length+",r_value:"+(r_value*1+0));
	if( value.length > (r_value*1+0)){
		return comm.v_notexceedmaxlength+r_value;
	}
	return true;
}

function minLength(value,r_value){
	if( value.length < (r_value*1+0)){
		return comm.v_notlessminlength+r_value;
	}
	return true;
}

function rangeLength(value,r_value){
	var min = r_value.substring(0,r_value.indexOf("-"));
	var max = r_value.substring(r_value.indexOf("-")+1,r_value.length);
	if( value.length < (min*1+0) || value.length > (max*1+0) ){
		return comm.v_lengthmustbe+min+"-"+max+comm.v_between;
	}
	return true;
}

function range(value,r_value){
	var min = r_value.substring(0,r_value.indexOf("-"));
	var max = r_value.substring(r_value.indexOf("-")+1,r_value.length);
	if( (value*1+0) < (min*1+0) || (value*1+0) > (max*1+0) ){
		return comm.v_rangemustbe+min+"-"+max+comm.v_between;
	}
	return true;
}


function myValidate(value,vtype){
	var tip = "";
	var value_ = "";
	var vtype_ = vtype;
	if((typeof vtype)!= "string" ){
		$.each(vtype, function (k, v){ 
			 value_ = v;
			 vtype_ = k;
		});
	}
	var regex = eval("regexEnum."+vtype_+".regex");
	if(regex==undefined || regex==""){
        return comm.v_regexnotmatch;
    }
	if((typeof regex)=="string"){
		var isValid = (new RegExp(regex,"g")).test(value);
		if(!isValid){
			tip = eval("regexEnum."+vtype+".message")+"";
		}
	}else{
		tip = regex.call(regex,value,value_);
	}
	return tip;
}




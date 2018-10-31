var path="/EyuShopPortal";
var remotePath="/EyuShop";
var remoteImgServer="http://120.77.82.58/img/";

function isEmpty(obj){
	if(obj== null ||obj==''){
		return true;
	}
	return false;
}

function setUserToSession(data,target){
	$.ajax({
		url:path+"/set_attribute",
		type:"GET",
		data:{
			"userId":data.userId,
			"account":data.account,
			"username":data.username,
			"headIcon":data.headIcon,
			"gender":data.gender,
			"userStatus":data.userStatus,
			"token":data.token,
			"phone":data.phone,
			"email":data.email,
			"role":data.role
		},
	    success:function(){
	    	window.location.href=target;
	    }
	    	
	})	
}

/**
 * 从url中截取最后一个/之后的内容
 * @param url
 * @returns
 */
function getIdFromUrl(url){
	if(isEmpty(url)){
		return null;
	}
	return url.substr(url.lastIndexOf("/")+1);
}

/**
 * 从url中获取请求参数
 * @param name
 * @returns
 */
function getUrlParam(name){
		var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var URL =  decodeURI(window.location.search);
        var r = URL.substr(1).match(reg); //匹配目标参数
        if(r!=null){
            //decodeURI() 函数可对 encodeURI() 函数编码过的 URI 进行解码
            return  decodeURI(r[2]);
        };
		return null; //返回参数值
} 

function isRightPhone(phone){
	if(isEmpty(phone)){
		return false;
	}
	var phoneRegex=new RegExp("^((13[0-9])|(15[^4,\\D])|(14[57])|(17[0])|(17[7])|(18[0,0-9]))\\d{8}$");
	if(phoneRegex.test(phone)){
		return true;
	}else{
		false;
	}
}

function isRightPwd(pwd){
	if(isEmpty(pwd)){
		return false;
	}
	var pwdRegex=new RegExp("^[a-z0-9_-]{6,18}$");
	if(pwdRegex.test(pwd)){
		return true;
	}else{
		false;
	}
}

function isRightEmail(email){
	if(isEmpty(email)){
		return false;
	}
	var emailRegex=new RegExp("^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$//^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$");
	if(emailRegex.test(email)){
		return true;
	}else{
		false;
	}
}

function isRightUserName(username){
	if(isEmpty(username)){
		return false;
	}
	var usernameRegex=new RegExp("^[\\u4E00-\\u9FFFa-zA-Z0-9]+$");
	if(usernameRegex.test(username)){
		return true;
	}else{
		false;
	}
}

function getAnchorFromUrl(url){
	if(isEmpty(url)){
		return null;
	}
	var index=url.lastIndexOf("#");
	console.log("index :" +index);
	if(index <  0){
		return null;
	}
	var anchor=url.substr(index+1);
	if(isEmpty(anchor)){
	}
	return anchor;
}
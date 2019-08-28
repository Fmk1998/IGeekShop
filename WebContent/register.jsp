<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head></head>
<c:set var="path" value="${pageContext.request.contextPath }" ></c:set>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员注册</title>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script src="js/jquery.validate.js" type="text/javascript" charset="utf-8"></script>
<script src="js/messages_zh.js" type="text/javascript" charset="utf-8"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="css/style.css" type="text/css" />

<style>
body {
	margin-top: 20px;
	margin: 0 auto;
}

.carousel-inner .item img {
	width: 100%;
	height: 300px;
}

font {
	color: #3164af;
	font-size: 18px;
	font-weight: normal;
	padding: 0 10px;
}

/* 鼠标放上去变手指 */
	#codeImg:hover{
		cursor: pointer;
	}
	
label.error{
				background:url(img/unchecked.gif) no-repeat 10px 3px;
				padding-left: 30px;
				font-family:georgia;
				font-size: 15px;
				font-style: normal;
				color: red;
			}
			
label.success{
				background:url(img/checked.gif) no-repeat 10px 3px;
				padding-left: 30px;
				color:blue;
			}
			
			
</style>

<script type="text/javascript">
$(function(){
	$("#registForm").validate({
		rules:{
			username:{
				required:true,
				minlength:1,
				maxlength:10,
				remote: {
				    url: "${path}/CheckName",     //后台处理程序
				    type: "post",               //数据发送方式
				    dataType: "json",           //接受数据格式   
				    data: {                     //要传递的数据
				    	username: function() {
				            return $("#username").val();
				        }
				    }
				}
			},
			inputPassword3:{
				required:true,
				minlength:5,
				maxlength:10
			},
			confirmpwd:{
				required:true,
				minlength:5,
				maxlength:10,
				equalTo:"[name='inputPassword3']"
			},
			email:{
				required:true
			},
			inlineRadioOptions:{
				required:true
			},
			yan:{
				required:true,
				remote: {
				    url: "${path}/CheckYan",     //后台处理程序
				    type: "post",               //数据发送方式
				    dataType: "json",           //接受数据格式   
				    data: {                     //要传递的数据
				        yan: function() {
				            return $("#yan").val();
				        }
				    }
				}
			},
		},
		messages:{
			username:{
				required:"用户名必填",
				minlength:"最少长度为5",
				maxlength:"最大长度不超过10",
				remote:"用户已经存在"
			},
			inputPassword3:{
				required:"密码必填",
				minlength:"最少长度为5",
				maxlength:"最大长度不超过10"
			},
			confirmpwd:{
				required:"确认密码必填",
				minlength:"最少长度为5",
				maxlength:"最大长度不超过10",
				equalTo:"密码必须保持一致"
			},
			email:{
				required:"邮箱必填"
			},
			inlineRadioOptions:{
				required:"性别必选"
			},
			yan:{
				required:"验证码必填",
				remote:"验证码错误"
			},
		},
		errorElement:"label",//用来创建错误提示信息标签
		success: function(label) { //验证成功后的执行的回调函数
			//label指向上面那个错误提示信息标签label
			label.text("符合要求") //清空错误提示消息
				.addClass("success"); //加上自定义的success类
		},
		// 验证失败时调用
		showErrors:function(errorMap,errorList){
			// 类似java的增强for
			for(var obj in errorMap) {
				// alert(obj);
				$("[name='"+obj+"']").next().removeClass("success");
			}
			// 继续调用原生方法
			this.defaultShowErrors();
		},
		submitHandler:function(form) {
	       /*  $(form).ajaxSubmit(); */
	       $.ajax({
				type:"post",
				url:"${path}/Register",
				dataType:"json",
				data:{
					username:$("#username").val(),
					password:$("#inputPassword3").val(),
					email:$("#email").val(),
					name:$("#usercaption").val(),
					sex:$('input:radio[name="inlineRadioOptions"]:checked').val(),
					birthday:$("#birthday").val(),
				},
				success:function(data){
					if(data.status==0){
						alert("注册成功!请先去邮箱验证")
						location.href = "login.jsp";
					}else{
						alert("注册失败!");
					}
				},
				error:function(msg){
					alert("服务出错!");
				}
			})
	    }
	});
})
</script>

</head>
<body>

	<!-- 引入header.jsp -->
	<jsp:include page="/header.jsp"></jsp:include>

	<div class="container"
		style="width: 100%; background: url('image/regist_bg.jpg');">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8"
				style="background: #fff; padding: 40px 80px; margin: 30px; border: 7px solid #ccc;">
				<font>会员注册</font>USER REGISTER
				<form action="#" method="get" id="registForm" class="form-horizontal" style="margin-top: 5px;">
					<div class="form-group">
						<label for="username" class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="username" name="username"
								placeholder="请输入用户名">
						</div>
					</div>
					<div class="form-group" >
						<label for="inputPassword3" class="col-sm-2 control-label">密码</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="inputPassword3" name="inputPassword3"
								placeholder="请输入密码">
						</div>
					</div>
					<div class="form-group">
						<label for="confirmpwd" class="col-sm-2 control-label">确认密码</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="confirmpwd" name="confirmpwd"
								placeholder="请输入确认密码">
						</div>
					</div>
					<div class="form-group">
						<label for="email" class="col-sm-2 control-label">Email</label>
						<div class="col-sm-6">
							<input type="email" class="form-control" id="email" name="email"
								placeholder="Email">
						</div>
					</div>
					<div class="form-group">
						<label for="usercaption" class="col-sm-2 control-label">姓名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="usercaption" name="usercaption"
								placeholder="请输入姓名">
						</div>
					</div>
					<div class="form-group opt">
						<label for="inlineRadio1" class="col-sm-2 control-label">性别</label>
						<div class="col-sm-6">
							<label class="radio-inline"> <input type="radio"
								name="inlineRadioOptions" id="inlineRadio1" value="男">
								男
							</label> <label class="radio-inline"> <input type="radio"
								name="inlineRadioOptions" id="inlineRadio2" value="女">
								女
							</label>
							<label for="inlineRadioOptions" class="error" style="display: none;"></label>
						</div>
					</div>
					<div class="form-group">
						<label for="date" class="col-sm-2 control-label">出生日期</label>
						<div class="col-sm-6">
							<input id="birthday" type="date" class="form-control">
						</div>
					</div>

					<div class="form-group">
						<label for="yan" class="col-sm-2 control-label">验证码</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="yan" name="yan">

						</div>
						<div class="col-sm-2">
							<img id="codeImg" onclick="changeImg()" src="VerificationCodeServlet"/>
						</div>

					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="submit" width="100" value="注册" name="submit"
								style="background: url('./images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0); height: 35px; width: 100px; color: white;">
						</div>
					</div>
				</form>
			</div>

			<div class="col-md-2"></div>

		</div>
	</div>

	<!-- 引入footer.jsp -->
	<jsp:include page="/footer.jsp"></jsp:include>

<script>
	function changeImg(){
		var code = document.getElementById("codeImg");
		code.src = "VerificationCodeServlet?timer="+new Date().getTime();
	}
</script>

</body>
</html>





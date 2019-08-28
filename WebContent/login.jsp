<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员登录</title>
<c:set var="path" value="${pageContext.request.contextPath }" ></c:set>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script src="js/jquery.validate.js" type="text/javascript" charset="utf-8"></script>
<script src="js/messages_zh.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			$(function(){
				$("#registForm").validate({
					rules:{
						username:{
							required:true,
							minlength:2,
							maxlength:6
						},
						inputPassword3:{
							required:true,
							minlength:3,
							maxlength:10
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
							minlength:"最少长度为2",
							maxlength:"最大长度不超过6"
						},
						inputPassword3:{
							required:"密码必填",
							minlength:"最少长度为3",
							maxlength:"最大长度不超过10"
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
								url:"${path}/LoginServlet",
								dataType:"json",
								data:{
									username:$("#username").val(),
									password:$("#inputPassword3").val(),
									rememberName:$('input:checkbox[name="rememberName"]:checked').val(),
									autoLogin:$('input:checkbox[name="autoLogin"]:checked').val()
								},
								success:function(data){
									if(data.status==0){
										alert("成功!");
										location.href = "indexServlet";
									}else if(data.status=1){
										alert("请先去邮箱激活账号!");
									}else{
										alert("密码输入错误!");
									}
								},
								error:function(msg){
									alert("服务出错!");
								}
							})
					    }
					//
				});
			})
			
	function selectAll(){
				var autoLogin = document.getElementById("autoLogin");
				if(autoLogin.checked){	
				//获取页面中其他的复选框,返回数组
				var chksArr = document.getElementsByName("rememberName");
				chksArr[0].checked = true;	
			}else{//未选中
				var chksArr = document.getElementsByName("rememberName");
			    //改变复选框的状态,未选中
				chksArr[0].checked = false;
			}
		}
		</script>
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

.container .row div {
	/* position:relative;
				 float:left; */
	
}

font {
	color: #666;
	font-size: 22px;
	font-weight: normal;
	padding-right: 17px;
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
</head>
<body>

	<!-- 引入header.jsp -->
	<jsp:include page="/header.jsp"></jsp:include>


	<div class="container"
		style="width: 100%; height: 460px; background: #FF2C4C url('images/loginbg.jpg') no-repeat;">
		<div class="row">
			<div class="col-md-7">
				<!--<img src="./image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">-->
			</div>

			<div class="col-md-5">
				<div
					style="width: 440px; border: 1px solid #E7E7E7; padding: 20px 0 20px 30px; border-radius: 5px; margin-top: 60px; background: #fff;">
					<font>会员登录</font>USER LOGIN
					<div>&nbsp;</div>
					<form class="form-horizontal" action="#" method="get" id="registForm">
						<div class="form-group">
							<label for="username" class="col-sm-2 control-label">用户名</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="username" name="username" value="${cookie.Cookie_username.value}"
									placeholder="请输入用户名">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">密码</label>
							<div class="col-sm-6">
								<input type="password" class="form-control" id="inputPassword3" name="inputPassword3"
									placeholder="请输入密码">
							</div>
						</div>
						<div class="form-group">
							<label for="yan" class="col-sm-2 control-label">验证码</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="yan" name="yan"
									placeholder="请输入验证码">
							</div>
							<div class="col-sm-3">
								<img id="codeImg" onclick="changeImg()" src="VerificationCodeServlet"/>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<div class="checkbox">
									<label> <input type="checkbox" onclick="selectAll()" id="autoLogin" name="autoLogin" value="autoLogin"> 自动登录
									</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <label> <input
										type="checkbox" name="rememberName" value="rememberName"> 记住用户名
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<input type="submit" width="100" value="登录" name="submit"
									style="background: url('./images/login.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0); height: 35px; width: 100px; color: white;">
							</div>
						</div>
					</form>
				</div>
			</div>
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
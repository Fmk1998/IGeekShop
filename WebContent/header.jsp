<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!-- 登录 注册 购物车... -->
<div class="container-fluid">
	<div class="col-md-4">
		<img src="img/logo.png" />
	</div>
	<div class="col-md-5">
		<img src="img/header.png" />
	</div>
	<div class="col-md-3" style="padding-top:20px">
		<ol class="list-inline">
			<c:if test="${empty user }">
			<li><a href="login.jsp">登录</a></li>
			<li><a href="register.jsp">注册</a></li>
			</c:if>
			<c:if test="${!empty user }">
			<li>欢迎您，${user.username }：</li>
			<li><a href="cart.jsp">购物车</a></li>
			<li><a href="SelectOrderList">我的订单</a></li>
			<li><a href="LogoutServlet">注销</a></li>
			</c:if>
		</ol>
	</div>
</div>

<!-- 导航条 -->
<div class="container-fluid">
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="indexServlet">首页</a>
			</div>

			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav" id="categoryUl">
					<li class="active"><a href="SelectProduct1">手机数码<span class="sr-only">(current)</span></a></li>
					<li><a href="SelectProduct2">电脑办公</a></li>
					<li><a href="#">电脑办公</a></li>
					<li><a href="#">电脑办公</a></li>
				</ul>
				<form class="navbar-form navbar-right" role="search" name="Form1"
		action="${pageContext.request.contextPath}/SearchProductList"
		method="post">
                    <div class="form-group" style="position:relative">
                        <input id="search" name="searchname" type="text" class="form-control" placeholder="Search" onkeyup="searchWord(this)" onchange="searchWord(this)">
                        <div id="showDiv" style="display:none; position:absolute;z-index:1000;background:#fff; width:179px;border:1px solid #ccc;">
                            
                        </div>
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
                <!-- 完成异步搜索 -->
                <script type="text/javascript">
                
                    function overFn(obj){
                        $(obj).css("background","#DBEAF9");
                    }
                    function outFn(obj){
                        $(obj).css("background","#fff");
                    }
                    
                    function clickFn(obj){
                        $("#search").val($(obj).html());
                        $("#showDiv").css("display","none");
                    }
                    
                
                    function searchWord(obj){
                        //1、获得输入框的输入的内容
                        var word = $(obj).val();
                        //2、根据输入框的内容去数据库中模糊查询---List<Product>
                        var content = "";
                        $.post(
                            "${pageContext.request.contextPath}/SearchWordServlet",
                            {"word":word},
                            function(data){
                                //3、将返回的商品的名称 现在showDiv中
                                //[{"pid":"1","pname":"小米 4c 官方版","market_price":8999.0,"shop_price":8999.0,"pimage":"products/1/c_0033.jpg","pdate":"2016-08-14","is_hot":1,"pdesc":"小米 4c 标准版 全网通 白色 移动联通电信4G手机 双卡双待 官方好好","pflag":0,"cid":"1"}]
                                
                                if(data.length>0){
                                    for(var i=0;i<data.length;i++){
                                        content+="<div style='padding:5px;cursor:pointer' onclick='clickFn(this)' onmouseover='overFn(this)' onmouseout='outFn(this)'>"+data[i]+"</div>";
                                    }
                                    $("#showDiv").html(content);
                                    $("#showDiv").css("display","block");
                                }
                                
                            },
                            "json"
                        );
                        
                    }
                </script>
			</div>
		</div>
		
		 <!-- 实现引入header.jsp页面的所有页面都动态获取类别 -->
        <script type="text/javascript">
            //header.jsp加载完毕后 去服务器端获得所有的category数据
            //引入header.jsp的页面本身已经引入的jquery文件，所以不用再次引入
            $(function(){
                var content = "";
                $.post(
                    "CategoryListServlet",
                    function(data){
                        for(var i=0;i<data.length;++i){
                            content += "<li><a href='${pageContext.request.contextPath}/SelectProduct?cid="+data[i].cid+"'>"+data[i].cname+"</a></li>";
                        }                            
                        //将拼接好的li放置到ul中
                        $("#categoryUl").html(content);
                    },
                 "json"    
                );
            });
        </script>
		
		
		
	</nav>
</div>
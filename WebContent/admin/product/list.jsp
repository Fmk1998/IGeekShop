<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/Style1.css"
	rel="stylesheet" type="text/css" />
<script language="javascript"
	src="${pageContext.request.contextPath}/js/public.js"></script>
<script type="text/javascript">
			function addProduct(){
				window.location.href = "${pageContext.request.contextPath}/AdminAddProductUIServlet";
			}
			function delProduct(pid){
                var flag = confirm("你确认要删除吗？");
                /* alert(pid);  */
                /* alert(flag); */
                if(flag){
                    location.href="${pageContext.request.contextPath}/delProduct?pid="+pid;
                }  
            }
			function editProduct(pid){
                /* alert(pid); */
                location.href="${pageContext.request.contextPath}/adminEditProduct?pid="+pid;
            }
			$(function(){
                $("#cid option[value='${condition.cid}']").prop("selected","selected");
                $("#isHot option[value='${condition.isHot}']").prop("selected","selected");
            });
			//批量删除
		    function delAllProduct(){
		        if(!confirm("确定要删除这些商品吗？")){
		            return ;
		        }
		        var cks=document.getElementsByName("select");
		        var str="";
		        //拼接所有的图书id
		        for(var i=0;i<cks.length;i++){
		            if(cks[i].checked){
		                str+=cks[i].value+" ";
		            }
		        }
		        //去掉字符串末尾的‘&’
		        str=str.substring(0, str.length-1);
		        location.href="${pageContext.request.contextPath}/AllDeleteProduct?str="+str;
		    }
			function selectAll(){
		//1.获取全选前面的复选框对象
		var chkAll = document.getElementById("chkAll");
		//2.判断复选框状态
				if(chkAll.checked)//选中状态
				{
					//3.获取页面中其他的所有复选框,返回数组
					var chksArr = document.getElementsByName("select");
					//4.遍历获取到的所有复选框的数组
					for(var i=0;i<chksArr.length;i++)
					{
						//5.改变每一个复选框的状态，选中
						chksArr[i].checked = true;
					}
					
				}else{//未选中
					//6.获取页面中其他的所有复选框,返回数组
					var chksArr = document.getElementsByName("select");
					//7.遍历获取到的所有复选框的数组
					for(var i=0;i<chksArr.length;i++)
					{
						//8.改变每一个复选框的状态,未选中
						chksArr[i].checked = false;
					}
				}

			//3.获取页面中其他的所有复选框,返回数组
			var chksArr = document.getElementsByName("select");
			//4.遍历获取到的所有复选框的数组
			for(var i=0;i<chksArr.length;i++)
			{
				//5.改变每一个复选框的状态，选中
				chksArr[i].checked = chkAll.checked;
			}
	}
		</script>
</HEAD>
<body>
	<br>
	<form id="Form1" name="Form1"
		action="${pageContext.request.contextPath}/adminSearchProductList"
		method="post">
		
		商品名称：<input type="text" name="pname" value="${condition.pname }"/> &nbsp;&nbsp;&nbsp;
        是否热门：<select id="isHot" name="isHot">
                    <option value="">不限</option>
                    <option value="1">是</option>
                    <option value="2">否</option>
               </select>&nbsp;&nbsp;&nbsp;
        商品类别：<select id="cid" name="cid">
                    <option value="">不限</option>
                    <c:forEach items="${categoryList }" var="category">
                    <option value="${category.cid }">${category.cname }</option>
                    </c:forEach>
               </select>&nbsp;&nbsp;&nbsp;
        <input type="submit"  value="搜索"/>
		
		<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>商品列表</strong>
					</TD>
				</tr>
				<tr>
					<td class="ta_01" align="right">
						<button type="button" id="add" name="add" value="添加"
							class="button_add" onclick="addProduct()">
							&#28155;&#21152;</button>
						<button type="button" id="alldelete" name="alldelete" value="批量删除"
							class="button_add" onclick="delAllProduct()">批量删除</button>
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
                                <td align="center" width="5%"><input type="checkbox" onclick="selectAll()" id="chkAll"/>全选/全不选</td>
								<td align="center" width="9%">序号</td>
								<td align="center" width="17%">商品图片</td>
								<td align="center" width="17%">商品名称</td>
								<td align="center" width="17%">商品价格</td>
								<td align="center" width="17%">是否热门</td>
								<td width="7%" align="center">编辑</td>
								<td width="7%" align="center">删除</td>
							</tr>
							<c:forEach items="${productList }" var="product" varStatus="vs">
							<tr onmouseover="this.style.backgroundColor = 'white'"
								onmouseout="this.style.backgroundColor = '#F5FAFE';">
								<td><input type="checkbox" name="select" value="${product.pid}"/></td>
								<td style="CURSOR: hand; HEIGHT: 22px" align="center"
									width="18%">${vs.count }</td>
								<td style="CURSOR: hand; HEIGHT: 22px" align="center"
									width="17%"><img width="40" height="45" src="${pageContext.request.contextPath }/${product.pimage }"></td>
								<td style="CURSOR: hand; HEIGHT: 22px" align="center"
									width="17%">${product.pname }</td>
								<td style="CURSOR: hand; HEIGHT: 22px" align="center"
									width="17%">${product.shop_price }</td>
								<td style="CURSOR: hand; HEIGHT: 22px" align="center"
									width="17%">${product.is_hot==1?"是":"否" }</td>
								<td align="center" style="HEIGHT: 22px"><a
                                        href="javascript:void(0)" onclick="editProduct(${product.pid})">
										<img
										src="${pageContext.request.contextPath}/images/i_edit.gif"
										border="0" style="CURSOR: hand">
								</a></td>

								<td align="center" style="HEIGHT: 22px"><a href="javascript:void(0)" onclick="delProduct(${product.pid})"><img
										src="${pageContext.request.contextPath}/images/i_del.gif"
										width="16" height="16" border="0" style="CURSOR: hand">
								</a></td>
							</tr>
							</c:forEach>
						</table>
					</td>
				</tr>

			</TBODY>
		</table>
	</form>
</body>
</HTML>


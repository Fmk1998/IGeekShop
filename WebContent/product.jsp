<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
   <tr>
       <td>商品名称</td>
       <td>价格</td>
       <td>价格</td>
       <td>图片</td>
   </tr>
<c:forEach items="${products }" var="pro">
   <tr>
       <td>${pro.pname }</td>
       <td>${pro.market_price }</td>
       <td>${pro.shop_price }</td>
       <td>${pro.pimage }</td>
   </tr>
</c:forEach>
</table>
</body>
</html>
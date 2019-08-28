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
       <td>用户id</td>
       <td>用户名</td>
       <td>用户密码</td>
       <td>用户生日</td>
   </tr>
<c:forEach items="${users }" var="pro">
   <tr>
       <td>${pro.uid }</td>
       <td>${pro.username }</td>
       <td>${pro.password }</td>
       <td>${pro.birthday }</td>
   </tr>
</c:forEach>
</table>
</body>
</html>
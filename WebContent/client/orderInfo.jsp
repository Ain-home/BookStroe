<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

		<h1>当前订单详情如下：</h1><br>
		<table border="1px">
        	<tr>
            	<td>书名：</td>
            	<td>单价：</td>
            	<td>数量：</td>
            	<td>总价：</td>
        	</tr>
        	<c:forEach items="${cartItems }" var="item">
            	<tr>
                	<td>${item.book.name }</td>
                	<td>${item.book.price}</td>
                	<td>${item.quantity }</td>
                	<td>${item.cost}</td>
            	</tr>  	
        	</c:forEach>
		</table>

</body>
</html>
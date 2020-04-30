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

	<c:if test="${empty(orders) }">
    	您还没有下过任何订单！！

	</c:if>

	<c:if test="${!empty(orders) }">

    	<table border="1px">

        	<tr>
            	<td>用户姓名：</td>
            	<td>下单日期</td>
            	<td>查看订单</td>
            	<td>订单状态</td>
            	<td>订单总价</td>
            	<td>操作</td>
        	</tr>
        	<c:forEach items="${orders }" var="order">
        		<tr>
            		<td>${user.name}</td>
            		<td>${order.date}</td>
            		<td><a href="${pageContext.request.contextPath }/OrderServlet?method=detail&order_id=${order.id }">订单详情</a></td>
            		<td>${order.state==false?"未发货":"已发货"}</td>
            		<td>${order.cost}</td>
            		<td><a href="${pageContext.request.contextPath }/OrderServlet?method=delete&order_id=${order.id }">${order.state==false?"取消订单":""}</a></td>
        		</tr>
        	</c:forEach>
        	
    	</table>

	</c:if>

</body>
</html>
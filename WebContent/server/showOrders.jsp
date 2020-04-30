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

	<c:if test="${empty(list)}">

    	目前还没有任何订单哦！

	</c:if>

	<c:if test="${!empty(list)}">

    	<table border="1px">

        	<tr>
            	<td>客户：</td>
            	<td>订单日期</td>
            	<td>订单状态</td>
            	<td>订单总价</td>
            	<td>操作</td>
        	</tr>

        	<c:forEach items="${list}" var="order">
            	<tr>
                	<td>${order.user_id}</td>
                	<td>${order.date}</td>
                	<td>${order.state==false?"未发货":"已发货"}</td>
                	<td>${order.cost}</td>
                	<td>
                    	<a href="${pageContext.request.contextPath}/OrderManage?method=detail&order_id=${order.id }&state=${order.state }">订单详情</a>
                    	<a href="${pageContext.request.contextPath}/OrderManage?method=delete&order_id=${order.id }&state=${order.state }">${order.state==false?"删除订单":""}</a>
                	</td>
            	</tr>
        	</c:forEach>
   	 	</table>


</c:if>

</body>
</html>
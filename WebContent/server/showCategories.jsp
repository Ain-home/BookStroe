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

    	暂时还没有分类数据哦,<a href="addCategory.jsp">点击</a>前往添加！
	</c:if>
		
	<c:if test="${!empty(list)}">

    	<table border="1px">
        	<tr>
            	<td>分类名字</td>
            	<td>分类描述</td>
            	<td>操作</td>
        	</tr>

    	<c:forEach items="${list}" var="category">

        	<tr>
            	<td>${category.name}</td>
            	<td>${category.description}</td>
            	<td>
                	<a href="${pageContext.request.contextPath}/CategoryServlet?method=delete&id=${category.id }">删除</a>
                	<a href="${pageContext.request.contextPath}/CategoryTransfer?id=${category.id }">修改</a>
            	</td>
        	</tr>

    	</c:forEach>

    	</table>
    	
	</c:if>

</body>
</html>
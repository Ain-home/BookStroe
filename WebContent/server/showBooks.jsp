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

	<c:if test="${empty(page.list)}">

    	暂时还没有任何书籍

	</c:if>

	<c:if test="${!empty(page.list)}">

   		<table border="1px">
       		<tr>
           		<td>书名</td>
           		<td>作者</td>
           		<td>价钱</td>
           		<td>描述</td>
           		<td>图片</td>
           		<td>操作</td>
       		</tr>

       		<c:forEach var="book" items="${page.list}" >
           		<tr>
               		<td>${book.name}</td>
               		<td>${book.author}</td>
               		<td>${book.price}</td>
               		<td>${book.description}</td>
              		<td><a href="${pageContext.request.contextPath}/images/${book.image }">查看图片</a></td>
               		<td>
                   		<a href="#">删除</a>
                   		<a href="#">修改</a>
               		</td>
           		</tr>
       		</c:forEach>

   		</table>
    	<br>
    	<jsp:include page="page.jsp"/>

	</c:if>

</body>
</html>
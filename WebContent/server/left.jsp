<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<a href="#">分类管理</a><br><br>
	<a href="addCategory.jsp" target="message">&nbsp;&nbsp;添加类别</a><br><br>
	<a href="${pageContext.request.contextPath}/CategoryServlet?method=all" target="message">&nbsp;&nbsp;查看分类</a><br><br>
	
	<a href="#">图书管理</a><br><br>
	<a href="${pageContext.request.contextPath}/BookTransfer?method=addUI" target="message">&nbsp;&nbsp;添加书籍</a><br><br>
	<a href="${pageContext.request.contextPath}/BookServlet?method=all" target="message">&nbsp;&nbsp;查看书籍</a><br><br>
	
	<a href="#">订单管理</a><br><br>
	<a href="${pageContext.request.contextPath}/OrderTransfer?state=false" target="message">&nbsp;&nbsp;待处理订单</a><br><br>
	<a href="${pageContext.request.contextPath}/OrderTransfer?state=true" target="message">&nbsp;&nbsp;已发货订单</a><br><br>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加书籍类别</title>
</head>
<body>

	<form action="${pageContext.request.contextPath}/CategoryServlet?method=add" method="post">
    	分类名称：<input type="text" name="name"><br><br>
    	分类描述：<textarea name="description"></textarea><br><br>
   		<input type="submit" value="提交">
	</form>

</body>
</html>
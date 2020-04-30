<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>欢迎来到网上书城</title>

<style type="text/css">
	#body {
   	 	position: relative;
	}
	#user {
    	position: absolute;
    	margin-top: 80px;
    	margin-left: 800px;
	}
</style>

<script type="text/javascript">

	function login() {
    	//得到输入框的数据
    	var name = document.getElementById("name").value;
    	var password = document.getElementById("password").value;

    	//跳转到相对应的Servlet上
    	window.location.href = "${pageContext.request.contextPath}/UserServlet?method=login&name=" + name + "&password=" + password;
	}

	function register() {

    	//跳转到注册页面
    	window.location.href = "register.jsp";
	}
</script>

</head>
<body style="text-align:center" id="body">

	<h1>欢迎来到网上书城</h1>
	
	<a href="${pageContext.request.contextPath}/BodyServlet" target="all">首页</a>
	<a href="${pageContext.request.contextPath}/CartTransfer" target="all">查看购物车</a>
	<a href="${pageContext.request.contextPath}/OrderServlet?method=find" target="all">查看订单</a>
	
	<c:if test="${user==null}" >
		<div id="users">
    		用户名：<input type="text" id="name">
    		密码：<input type="password" id="password">
    		<button name="login" onclick="login()">登录</button>
    		<button name="register" onclick="register()">注册</button>
		</div>
	</c:if>

	<c:if test="${user!=null}" >
    	<div id="users">
        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;欢迎您：${user.name}&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/UserServlet?method=Logout">注销</a>
    	</div>
	</c:if>

</body>
</html>
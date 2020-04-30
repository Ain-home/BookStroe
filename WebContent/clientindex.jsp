<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>网上书城</title>
</head>

	<frameset rows="20%,*">
		<frame src="client/head.jsp" name="head" />
		<frame src="${pageContext.request.contextPath}/BodyServlet" name="all" />
	</frameset>

</html>
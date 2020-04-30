<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城后台</title>
</head>

	<frameset rows="20%,*">
	
    	<frame src="server/head.jsp" name="head"/>

    	<frameset cols="10%,*">
        	<frame src="server/left.jsp" name="left"/>
        	<frame src="server/message.jsp" name="message"/>
    	</frameset>
    	
	</frameset>

</html>
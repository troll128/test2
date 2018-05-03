<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%request.setCharacterEncoding("UTF-8");%>
<body>
<!-- el을 사용하여 세션값 표시 -->
<%-- 세션값을 가져올 때는 ${sessionScope.세션명}으로 가져온다. --%>
<h3>세션값: ${sessionScope.id}</h3>
<!-- el을 사용하여 리퀘스트값 표시 -->
<%-- 리퀘스트값을 가져올 때는 ${param.리퀘스트명}으로 가져온다. --%>
<h3>name 파라미터값: ${param.name}</h3>
</body>
</html>
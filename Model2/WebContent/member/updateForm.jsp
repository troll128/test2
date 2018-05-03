<%@page import="net.member.db.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
//String id=세션값가져오기
String id=(String)session.getAttribute("id");
//세션값이 없으면 loginForm.jsp 이동
if(id==null){
	response.sendRedirect("./MemberLogin.me");
}
//request 회원정보 가져오기
MemberBean mb=(MemberBean)request.getAttribute("mb");
%>
<h1>WebContent/member/updateForm.jsp</h1>
<h1>회원정보수정</h1>
<form action="./MemberUpdateAction.me" method="post" name="fr">
아이디 : <input type="text" name="id" value="<%=mb.getId() %>" readonly><br>
비밀번호 : <input type="password" name="pass"><br>
이름:<input type="text" name="name" value="<%=mb.getName() %>"><br>
<input type="submit" value="회원수정">
</form>
</body>
</html>

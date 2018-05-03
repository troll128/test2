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
<h1>WebContent/member/info.jsp</h1>
<%
//String id=세션값 가져오기
String id=(String)session.getAttribute("id");
//세션값이 없으면 loginForm.jsp 이동
if(id==null){
	response.sendRedirect("./MemberLogin.me");
}
// 회원정보 값 가져오기
// request.setAttribute("mb", mb);
// 자바빈 mb = request 담기 정보 가져오기
MemberBean mb=(MemberBean)request.getAttribute("mb");
%>
<h1>회원정보조회</h1>
아이디:<%=mb.getId() %><br>
비밀번호:<%=mb.getPass() %><br>
이름:<%=mb.getName() %><br>
가입날짜:<%=mb.getReg_date() %><br>
<a href="./Main.me">main으로이동</a>
</body>
</html>

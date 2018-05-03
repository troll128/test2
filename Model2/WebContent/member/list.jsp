<%@page import="net.member.db.MemberBean"%>
<%@page import="java.util.List"%>
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
//   https://docs.oracle.com/javase/8/docs/
//세션값 가져오기
String id=(String)session.getAttribute("id");
//세션값 없으면  세션값이 admin이 아니면  loginForm.jsp
if(id==null || !(id.equals("admin"))){
	response.sendRedirect("./MemberLogin.me");
}
// 저장된 정보 가져오기
//request.setAttribute("memberList", memberList);
List memberList=(List)request.getAttribute("memberList");
%>
<h1>WebContent/member/list.jsp</h1>
<h1>회원정보목록</h1>
<table border="1">
<tr><td>아이디</td><td>비밀번호</td><td>이름</td><td>가입일</td></tr>
<% //5단계 tr출력
for(int i=0;i<memberList.size();i++){
	MemberBean mb=(MemberBean)memberList.get(i);
	%>
<tr><td><%=mb.getId() %></td>
    <td><%=mb.getPass() %></td>
    <td><%=mb.getName() %></td>
    <td><%=mb.getReg_date() %></td></tr>	
	<%
}
%>
</table>
</body>
</html>




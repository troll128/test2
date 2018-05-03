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
//String id=세션값 가져오기
String id=(String)session.getAttribute("id");
//세션값이 없으면 loginForm.jsp 이동
if(id==null){
	response.sendRedirect("./MemberLogin.me");
}
%>
<h1>WebContent/member/main.jsp</h1>
<%=id %>님이 로그인하셨습니다<br>
<input type="button" value="로그아웃" 
onclick="location.href='./MemberLogoutAction.me'"><br>
<a href="./MemberInfo.me">회원정보조회</a><br>
<a href="./MemberUpdate.me">회원정보수정</a><br>
<a href="./MemberDelete.me">회원정보삭제</a><br>
<%
if(id!=null){
	if(id.equals("admin")){
		%>
      <a href="./MemberList.me">회원목록</a>		
		<%
	}
}
%>
<br>
<a href="./GoodsList.go">상품목록</a><br>
</body>
</html>





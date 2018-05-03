<%@page import="net.board.db.BoardBean"%>
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
//num  , pageNum 파라미터 가져오기 
int num=Integer.parseInt(request.getParameter("num"));
String pageNum=request.getParameter("pageNum");
// request.setAttribute("bb", bb);
BoardBean bb=(BoardBean)request.getAttribute("bb");
%>
<h1>WebContent/board/updateForm.jsp</h1>
<h1>게시판 글수정</h1>
<form action="./BoardUpdateAction.bo?pageNum=<%=pageNum %>" method="post">
<input type="hidden" name="num" value="<%=bb.getNum()%>">
<table border="1">
<tr><td>글쓴이</td><td><input type="text" name="name" value="<%=bb.getName()%>"></td></tr>
<tr><td>비밀번호</td><td><input type="password" name="pass"></td></tr>
<tr><td>제목</td><td><input type="text" name="subject" value="<%=bb.getSubject()%>"></td></tr>
<tr><td>내용</td>
<td><textarea rows="10" cols="20" name="content"><%=bb.getContent()%></textarea></td></tr>
<tr><td colspan="2">
<input type="submit" value="글수정">
<input type="reset" value="다시쓰기"></td></tr>
</table>
</form>
</body>
</html>
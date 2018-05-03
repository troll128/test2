<%@page import="net.board.db.BoardBean"%>
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
//  count , pageNum, boardList, pageCount, pageBlock
//  startPage, endPage 가져오기
		int count=((Integer)request.getAttribute("count")).intValue();
		String pageNum=(String)request.getAttribute("pageNum");
		List boardList=(List)request.getAttribute("boardList");
		int pageCount=((Integer)request.getAttribute("pageCount")).intValue();
		int pageBlock=((Integer)request.getAttribute("pageBlock")).intValue();
		int startPage=((Integer)request.getAttribute("startPage")).intValue();
		int endPage=((Integer)request.getAttribute("endPage")).intValue();
%>
<h1>WebContent/board/list.jsp</h1>
<h1>글목록 [전체글개수 : <%=count %>]</h1>
<h3><a href="./BoardWrite.bo">글쓰기</a></h3>
<table border="1">
<tr><td>번호</td><td>제목</td><td>작성자</td><td>날짜</td><td>조회수</td></tr>
<%
for(int i=0;i<boardList.size();i++){
	BoardBean bb=(BoardBean)boardList.get(i);
	%>
<tr><td><%=bb.getNum() %></td>
<td>
<%
int wid=0;
// 답글이면 
//  width 구하기 레벨1이면 10픽셀  레벨2면 20픽셀
//  ./board/level.gif  width=       ./board/re.gif   
if(bb.getRe_lev()>0){
	wid=bb.getRe_lev()*10;
	%>
	<img src="./board/level.gif" width="<%=wid%>" height="20">
	<img src="./board/re.gif">
	<%
}
%>
<a href="./BoardContent.bo?num=<%=bb.getNum()%>&pageNum=<%=pageNum%>"><%=bb.getSubject() %></a></td>
<td><%=bb.getName() %></td><td><%=bb.getDate() %></td>
<td><%=bb.getReadcount() %></td></tr>	
	<%
}
%>
</table>
<%
if(count!=0){
//[이전]  startPage   pageBlock
if(startPage > pageBlock){
	%><a href="./BoardList.bo?pageNum=<%=startPage-pageBlock%>">[이전]</a><%
}
// 1 ~ 10  11~20
for(int i=startPage;i<=endPage;i++){
	%><a href="./BoardList.bo?pageNum=<%=i%>">[<%=i%>]</a><%
}
//[다음] endPage   pageCount
if(endPage < pageCount){
	%><a href="./BoardList.bo?pageNum=<%=startPage+pageBlock%>">[다음]</a><%
}

}
%>
</body>
</html>




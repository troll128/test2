<%@page import="net.order.db.OrderBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
body {
	text-align: center;			
}
table {
	margin: auto;
	text-align: center;
}
</style>
</head>
<body>
<%
//request.setAttribute("orderDetailList", orderDetailList);
List orderDetailList=(List)request.getAttribute("orderDetailList");
int total=0;
%>
<h1>주문상세보기</h1>
<table border="1">
<tr><td>상품명</td><td>상품사이즈</td><td>상품색상</td>
   <td>주문개수</td><td>주문금액</td></tr>
   <%
   for(int i=0;i<orderDetailList.size();i++){
	   OrderBean orderbean=(OrderBean)orderDetailList.get(i);
	   total=total+orderbean.getO_sum_money();
	   %>
<tr><td><%=orderbean.getO_g_name() %></td>
<td><%=orderbean.getO_g_size() %></td>
<td><%=orderbean.getO_g_color() %></td>
   <td><%=orderbean.getO_g_amount() %></td>
   <td><%=orderbean.getO_sum_money() %>원</td></tr>	   
	   <%
   }
   %>
</table>
총주문금액: <%=total %> 원
</body>
</html>





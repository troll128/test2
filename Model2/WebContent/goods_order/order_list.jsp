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
//request.setAttribute("orderList", orderList);
List orderList=(List)request.getAttribute("orderList");
%>
<h1>주문목록</h1>
<table border="1">
<tr><td>주문번호</td><td>상품명</td><td>결재방법</td>
<td>주문금액</td><td>주문상태</td><td>주문일시</td>
<td>운송장번호</td></tr>
<%
for(int i=0;i<orderList.size();i++){
	OrderBean orderbean=(OrderBean)orderList.get(i);
	%>
<tr><td><a href="./OrderDetail.or?trade_num=<%=orderbean.getO_trade_num()%>"><%=orderbean.getO_trade_num()%></a></td>
<td><%=orderbean.getO_g_name() %>..</td>
<td><%=orderbean.getO_trade_type() %></td>
<td><%=orderbean.getO_sum_money() %></td>
<%
String status="";
//  orderbean.getO_status()  0 이면 "대기중"
//  1이면 발송준비 2 발송완료 3 배송중 4 배송완료 5 주문취소
if(orderbean.getO_status()==0){
	status="대기중";
}else if(orderbean.getO_status()==1){
	status="발송준비";
}else if(orderbean.getO_status()==2){
	status="발송완료";
}else if(orderbean.getO_status()==3){
	status="배송중";
}else if(orderbean.getO_status()==4){
	status="배송완료";
}else if(orderbean.getO_status()==5){
	status="주문취소";
}
%>
<td><%=status %></td>
<td><%=orderbean.getO_date() %></td>
<td><%=orderbean.getO_trans_num() %></td></tr>	
	<%
}
%>

</table>
</body>
</html>






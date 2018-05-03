<%@page import="net.admin.goods.db.GoodsBean"%>
<%@page import="net.basket.db.BasketBean"%>
<%@page import="net.member.db.MemberBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
body {
	text-align: center;			
}
table {
	margin: auto;
	text-align: center;
}
</style>
<title>Insert title here</title>
</head>
<body>
<%
// request.setAttribute("basketList", basketList);
// request.setAttribute("goodsList", goodsList);
// request.setAttribute("memberbean", memberbean);
List basketList=(List)request.getAttribute("basketList");
List goodsList=(List)request.getAttribute("goodsList");
MemberBean memberbean=(MemberBean)request.getAttribute("memberbean");
%>
<h1>주문상세내역</h1>
<table>
<tr><td>사진</td><td>상품명</td><td>수량</td>
    <td>색깔</td><td>사이즈</td><td>가격</td></tr>
    <%
    for(int i=0;i<basketList.size();i++){
    	BasketBean basketbean=(BasketBean)basketList.get(i);
    	GoodsBean goodsbean=(GoodsBean)goodsList.get(i);
    	%>
<tr><td><img src="./upload/<%=goodsbean.getImage().split(",")[0]%>" width="50" height="50"></td>
<td><%=goodsbean.getName() %></td>
<td><%=basketbean.getB_g_amount() %></td>
    <td><%=basketbean.getB_g_color() %></td>
    <td><%=basketbean.getB_g_size() %></td>
    <td><%=goodsbean.getPrice() %></td></tr>    	
    	<%
    }
    %>
</table>
<form action="./OrderAdd.or" method="post">
<h1>주문자정보</h1>
이름:<%=memberbean.getName() %><br>
휴대폰:<br>
이메일주소:<%//=memberbean.getEmail() %><br>
<h1>배송지정보</h1>
받는사람:<input type="text" name="o_receive_name" value="<%=memberbean.getName()%>"><br>
집전화:<input type="text" name="o_receive_phone" value=""><br>
휴대폰:<input type="text" name="o_receive_mobile" value=""><br>
배송지 주소:<input type="text" name="o_receive_addr1" value=""><br>
배송지 나머지주소:<input type="text" name="o_receive_addr2" value=""><br>
기타요구사항:<input type="text" name="o_memo" value=""><br>
<h1>결제정보</h1>
입금자명(온라인입금일경우):<input type="text" name="o_trade_payer" value="<%=memberbean.getName()%>"><br>
<input type="submit" value="주문">
<input type="reset" value="취소">
</form>
</body>
</html>








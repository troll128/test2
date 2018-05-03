<%@page import="net.admin.goods.db.GoodsBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
body {
	text-align: center;
	font-family: 맑은 고딕;	
	background-image: url(./img/background8.jpg);
	background-repeat: no-repeat;
	background-size: cover;
}
table {
	margin: auto;
	text-align: center;
	background: white;
	
}
#category {
	background-color: cyan;
	font-weight: bold;
}
#link {
	text-decoration: none;
	font-weight: bold;
}
#link:HOVER {	
	color: orange;
}
</style>
</head>
<body>
<%
//request.setAttribute("goodsList", goodsList);
List goodsList=(List)request.getAttribute("goodsList");
%>
<h1>상품목록</h1>
<h3><a id="link" href="./BasketList.ba">장바구니</a></h3>
<h3><a id="link" href="./OrderList.or">주문목록</a></h3>
<table border="1">
<tr id="category"><td><a id="link" href="./GoodsList.go">전체</a></td>
<td><a id="link" href="./GoodsList.go?item=best">베스트상품</a></td>
<td><a id="link" href="./GoodsList.go?item=outwear">아웃웨어</a></td>
<td><a id="link" href="./GoodsList.go?item=fulldress">정장/신사복</a></td>
<td><a id="link" href="./GoodsList.go?item=Tshirts">티셔츠</a></td>
<td><a id="link" href="./GoodsList.go?item=shirts">와이셔츠</a></td>
<td><a id="link" href="./GoodsList.go?item=pants">팬츠</a></td>
<td><a id="link" href="./GoodsList.go?item=shoes">슈즈</a></td></tr>
</table>
<hr>
<table border="1">
<tr>
<%
for(int i=0;i<goodsList.size();i++){
	GoodsBean goodsbean=(GoodsBean)goodsList.get(i);
	%>
<td><img src="./upload/<%=goodsbean.getImage().split(",")[0] %>" width="130" height="130"><br>
    <a id="link" href="./GoodsDetail.go?num=<%=goodsbean.getNum()%>"><%=goodsbean.getName() %></a><br>
    <%=goodsbean.getPrice() %>원
</td>	
	<%
}
%>
</tr>
</table>
</body>
</html>




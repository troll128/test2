<%@page import="net.admin.goods.db.GoodsBean"%>
<%@page import="net.basket.db.BasketBean"%>
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
<script type="text/javascript">
function basketDelete(b_num) {
	if(confirm("정말 삭제하시겠습니까?") == true) {	// 삭제 확인
		location.href='./BasketDelete.ba?b_num='+b_num+'';
	} else {	// 삭제 취소
		return;
	}	
}
</script>
</head>
<body>
<%
// request.setAttribute("basketList", basketList);
// request.setAttribute("goodsList", goodsList);
List basketList=(List)request.getAttribute("basketList");
List goodsList=(List)request.getAttribute("goodsList");
%>
<h1>장바구니</h1>
<table border="1">
<tr><td>번호</td><td>사진</td><td>제품명</td>
<td>사이즈</td><td>색상</td><td>수량</td>
<td>가격</td><td>취소</td></tr>
<%
for(int i=0;i<basketList.size();i++){
	BasketBean basketbean=(BasketBean)basketList.get(i);
	GoodsBean goodsbean=(GoodsBean)goodsList.get(i);
	%>
<tr><td><%=basketbean.getB_num() %></td>
<td><img src="./upload/<%=goodsbean.getImage().split(",")[0]%>" width="50" height="50"></td>
<td><%=goodsbean.getName() %></td>
<td><%=basketbean.getB_g_size() %></td>
<td><%=basketbean.getB_g_color() %></td>
<td><%=basketbean.getB_g_amount() %></td>
<td><%=goodsbean.getPrice() %></td>
<td><button type="button" onclick="basketDelete(<%=basketbean.getB_num()%>)">제외</button>
</td></tr>	
	<%
}
%>
</table>
<a href="./OrderStar.or">[구매하기]</a>
<a href="./GoodsList.go">[계속쇼핑하기]</a>
</body>
</html>






<%@page import="net.admin.goods.db.GoodsBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	color: purple;
}
#subject {
	background-color: orange;
	font-weight: bold;
}
#button {
	background-color: yellow;
	font-weight: bold;
}
</style>
<script type="text/javascript">
	function isBasket(){
		if(document.gfr.size.value==""){
			alert("사이즈 선택하세요");
			document.gfr.size.focus();
			return;
		}
		if(document.gfr.color.value==""){
			alert("색상 선택하세요");
			document.gfr.color.focus();
			return;
		}
		var is=confirm("장바구니에 저장하시겠습니까?");
		if(is==true){
		document.gfr.action="./BasketAdd.ba";
		document.gfr.submit();
		}else{
			return;
		}
	}
</script>
</head>
<body>
<%
//request.setAttribute("gBean", gBean);
GoodsBean goodsbean=(GoodsBean)request.getAttribute("gBean");
%>
<h1>상품상세보기</h1>
<form action="" method="post" name="gfr">
<input type="hidden" name="num" value="<%=goodsbean.getNum()%>">
<table border="1">
<tr><td rowspan="8"><img src="./upload/<%=goodsbean.getImage().split(",")[0] %>" width="300" height="300"></td>
<tr><td id="subject">상품이름</td><td><%=goodsbean.getName()%></td></tr>
<tr><td id="subject">판매가격</td><td><%=goodsbean.getPrice() %>원</td></tr>
<tr><td id="subject">수량</td><td><input type="text" name="amount" value="1" size="4" style="text-align:right; padding-right: 3px;"></td></tr>
<tr><td id="subject">남은수량</td><td><%=goodsbean.getAmount() %>개</td></tr>
 <tr><td id="subject">크기</td><td><select name="size">
 	<option value="">크기를 선택하세요</option>
 	<c:forTokens var="size" items="${gBean.size }" delims=",">
 		<option value="${size}">${size}</option>
 	</c:forTokens>
 </select>
 </td></tr>
<tr><td id="subject">색상</td><td><select name="color">
 	<option value="">색상를 선택하세요</option>
 	<c:forTokens var="color" items="${gBean.color }" delims=",">
 		<option value="${color}">${color}</option>
 	</c:forTokens>
 </select>
 </td></tr>
<tr id="button"><td colspan="2"><a id="link" href="javascript:isBasket()">[장바구니담기]</a> || <a id="link" href="javascript:isBuy()">[구매하기]</a></td></tr>

<%	// 상품 설명 이미지가 없다면 "이미지가 없습니다" 문자열만 출력
	if (goodsbean.getImage().split(",")[1].equals("null")) {
%>
	<tr><td colspan="3">이미지가 없습니다</td></tr>
<%		
	} else {
%>		
	<tr><td colspan="3"><img src="./upload/<%=goodsbean.getImage().split(",")[1] %>" width="300" height="300"></td></tr>
<%	
	}
%>
<%
	if (goodsbean.getImage().split(",")[2].equals("null")) {
%>
	<tr><td colspan="3">이미지가 없습니다</td></tr>
<%		
	} else {	
%>		
	<tr><td colspan="3"><img src="./upload/<%=goodsbean.getImage().split(",")[2] %>" width="300" height="300"></td></tr>	
<%	
	}
%>
<%
	if (goodsbean.getImage().split(",")[3].equals("null")) {
%>
	<tr><td colspan="3">이미지가 없습니다</td></tr>
<%		
	} else {
%>			
	<tr><td colspan="3"><img src="./upload/<%=goodsbean.getImage().split(",")[3] %>" width="300" height="300"></td></tr>
<%			
	}
%>
</table>
</form>
</body>
</html>





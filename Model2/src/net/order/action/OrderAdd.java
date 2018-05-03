package net.order.action;

import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.basket.db.BasketDAO;
import net.goods.db.GoodsDAO;
import net.order.action.ActionForward;
import net.order.db.*;

public class OrderAdd implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("OrderAdd execute()");
		//한글처리
		request.setCharacterEncoding("utf-8");
		// 세션 객체 생성
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");		
		// 장바구니 객체 생성
		BasketDAO bdao = new BasketDAO();
		// Vector: 대용량의 데이터를 효과적으로 다룰 수 있고, 여러 개의 배열 값을 담을 수 있는 클래스이다. 
		// v.get(0)은 벡터 배열의 첫번째 배열을 가져온다는 의미이다. 
		// Vector 변수 v에 장바구니 리스트를 담는다.
		Vector v = bdao.getBasketList(id);
		List basketList = (List) v.get(0);
		List goodsList = (List) v.get(1);
		// OrderBean orderbean 객체 생성
		OrderBean orderbean = new OrderBean();
		// 자바빈 <- o_receive_name, o_receive_phone, o_receive_mobile, 
		// o_receive_addr1, o_receive_addr2, o_memo, o_trade_payer
		orderbean.setO_receive_name(request.getParameter("o_receiver_name"));
		orderbean.setO_receive_phone(request.getParameter("o_receiver_phone"));
		orderbean.setO_receive_mobile(request.getParameter("o_receiver_mobile"));
		orderbean.setO_receive_addr1(request.getParameter("o_receiver_addr1"));
		orderbean.setO_receive_addr2(request.getParameter("o_receiver_addr2"));
		orderbean.setO_memo(request.getParameter("o__memo"));
		orderbean.setO_trade_payer(request.getParameter("o_trade_payer"));
		orderbean.setO_m_id(id);		
		// OrderDAO odao 객체 생성
		OrderDAO odao = new OrderDAO();
		// addOrder(orderbean, basketList, goodsList) 메소드 호출
		odao.addOrder(orderbean, basketList, goodsList);
		// 해당 회원(id)의 장바구니 비우기		
		bdao.deleteBasket(id);
		// 해당 상품의 개수를 장바구니에 담았던 물품 개수만큼 차감
		GoodsDAO gdao = new GoodsDAO();
		gdao.updateAmount(basketList);
		// 주문 목록으로 이동 
		ActionForward forward = new ActionForward();
		forward.setPath("./OrderList.or");
		forward.setRedirect(true);
		return forward;	
	}

}

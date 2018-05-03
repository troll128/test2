package net.order.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.order.action.ActionForward;
import net.order.db.*;

public class OrderDetail implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("OrderDetail execute()");
		String trade_num = request.getParameter("trade_num");
		OrderDAO odao = new OrderDAO();
		List orderDetailList = odao.orderDetail(trade_num);
		// 자바빈 값을 리퀘스트에 저장
		request.setAttribute("orderDetailList", orderDetailList);
		// 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./goods_order/order_detail.jsp");
		forward.setRedirect(false);
		return forward;		
	}

}

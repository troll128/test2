package net.admin.order.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.order.db.AdminOrderDAO;

public class AdminOrderDetail implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("AdminOrderDetail execute()");
		// 필요한 변수인 trade_num 값을 가져온다.
		String trade_num = request.getParameter("trade_num");
		// 필요한 객체 생성 후 주문 목록 값을 배열 변수에 담는다.
		AdminOrderDAO aodao = new AdminOrderDAO();
		@SuppressWarnings("rawtypes")
		List adminOrderDetail = aodao.getAdminOrderDetail(trade_num);
		// 주문 목록 정보값을 리퀘스트에 담는다.
		request.setAttribute("adminOrderDetail", adminOrderDetail);
		// 이동
		ActionForward forward=new ActionForward();
		forward.setPath("./adminorder/admin_order_modify.jsp");
		forward.setRedirect(false);
		return forward;
		
	}

}

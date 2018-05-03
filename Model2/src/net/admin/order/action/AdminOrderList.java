package net.admin.order.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.admin.order.db.AdminOrderDAO;

public class AdminOrderList implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminOrderList execute()");
		// 필요한 객체 생성 후 주문 목록 값을 배열 변수에 담는다.
		AdminOrderDAO aodao = new AdminOrderDAO();
		@SuppressWarnings("rawtypes")
		List adminOrderList = aodao.getAdminOrderList();
		// 주문 목록 값을 리퀘스트에 담는다.
		request.setAttribute("adminOrderList", adminOrderList);
		// 이동
		ActionForward forward=new ActionForward();
		forward.setPath("./adminorder/admin_order_list.jsp");
		forward.setRedirect(false);
		return forward;
	}
}

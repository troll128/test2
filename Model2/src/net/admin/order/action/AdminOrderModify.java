package net.admin.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.admin.order.db.AdminOrderDAO;
import net.order.db.OrderBean;

public class AdminOrderModify implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("AdminOrderModify execute()");
		// 자바빈 OrderBean 객체 생성
		OrderBean orderbean = new OrderBean();
		// 수정 작업에 필요한 리퀘스트 값들을 변수에 담는다.
		String trans_num = request.getParameter("trans_num");
		int status = Integer.parseInt(request.getParameter("status"));
		String trade_num = request.getParameter("trade_num");
		// 자바빈에 수정된 값들을 저장
		orderbean.setO_trade_num(trade_num);
		orderbean.setO_status(status);
		orderbean.setO_trans_num(trans_num);
		// DB 작업 객체 생성 후 자바빈에 담긴 값들로 modify 작업 실행
		AdminOrderDAO aodao = new AdminOrderDAO();
		aodao.updateOrder(orderbean);		
		// 주문 목록으로 이동		
		ActionForward forward=new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./AdminOrderList.ao");
		return forward;
	}
}

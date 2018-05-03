package net.basket.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.basket.db.BasketBean;
import net.basket.db.BasketDAO;

public class BasketDelete implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BasketDelete execute()");
		//한글처리
		request.setCharacterEncoding("utf-8");
		// 장바구니 번호값을 가져온다.
		int b_num = Integer.parseInt(request.getParameter("b_num"));
		// BasketDAO bdao 객체 생성
		BasketDAO bdao = new BasketDAO();
		// basketDelete(장바구니 번호값) 메소드 호출
		bdao.basketDelete(b_num);
		// 장바구니 리스트로 이동 
		ActionForward forward = new ActionForward();
		forward.setPath("./BasketList.ba");
		forward.setRedirect(true);
		return forward;			
	}
}

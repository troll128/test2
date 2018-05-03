package net.basket.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.basket.db.*;

public class BasketAdd implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BasketAdd execute()");
		//한글처리
		request.setCharacterEncoding("utf-8");
		// 현재 접속한 유저의 아이디가 담긴 세션값을 사용하기 위한 객체 생성
		HttpSession session=request.getSession();
		String id = (String)session.getAttribute("id");
		if (id==null) {	// 현재 로그인하지 않았다면?	
			response.setContentType("text/html; charset=UTF-8");
			// 출력할 객체 생성
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('장바구니를 이용하시려면 로그인을 해야합니다!');");
			out.println("location.href='./MemberLogin.me';");
			out.println("</script>");
			out.close();
			return null;
		}
		// 상품 정보에서 장바구니를 만드는데 필요한 리퀘스트 값들을 호출하여 변수에 담는다.
		int num = Integer.parseInt(request.getParameter("num"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		String size = request.getParameter("size");
		String color = request.getParameter("color");
		// 자바빈 객체 생성
		BasketBean basketbean = new BasketBean();
		// 자바빈 멤버변수 <= 파라미터 가져와서 저장
		basketbean.setB_m_id(id);
		basketbean.setB_g_num(num);
		basketbean.setB_g_amount(amount);
		basketbean.setB_g_size(size);
		basketbean.setB_g_color(color);
		System.out.println("ID: "+id+", 물품번호: "+num+", 개수: "+amount+", 사이즈: "+size+", 색상: "+color);
		// 디비작업 객체생성
		BasketDAO bdao = new BasketDAO();
		// int check = checkGoods(basketbean) 같은 물품인 경우 주문 개수만 update하고 새로운 항목으로 추가하지 않는다.
		int check = bdao.checkGoods(basketbean);
		// check == 0  	basketAdd(basketbean) 호출
		if (check==0) {	// 중복되지 않는 물품이라면 해당 물품을 장바구니에 추가
			bdao.basketAdd(basketbean);			
		}
		// 메서드호출() 		
		bdao.basketAdd(basketbean);
		// 게시판 목록 이동 
		// ActionForward 객체 생성  // "./BasketList.ba" 이동
		ActionForward forward=new ActionForward();
		forward.setPath("./BasketList.ba");
		forward.setRedirect(true);
		return forward;		
	}	
}

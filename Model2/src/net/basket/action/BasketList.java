package net.basket.action;

import java.util.List;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.basket.db.*;

public class BasketList implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BasketList execute()");
		// 세션값(회원 아이디) 가져오기
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
		// 장바구니 리스트에 필요한 request 값 저장		
		request.setAttribute("basketList", basketList);
		request.setAttribute("goodsList", goodsList);		
		// 장바구니 리스트로 이동 
		ActionForward forward = new ActionForward();
		forward.setPath("./goods_order/goods_basket.jsp");
		forward.setRedirect(false);
		return forward;		
	}	
}

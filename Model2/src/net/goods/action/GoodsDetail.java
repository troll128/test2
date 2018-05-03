package net.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.goods.db.GoodsBean;
import net.goods.db.GoodsDAO;

public class GoodsDetail implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("GoodsDetail execute()");
		// 상품 번호에 대한 리퀘스트 값을 받아온다.
		int num = Integer.parseInt(request.getParameter("num"));		
		// 상품 정보를 불러오기 위해 GoodsDAO 객체를 생성하고 getGoods 메소드를 가져온다.
		GoodsDAO gdao = new GoodsDAO();
		GoodsBean gBean = gdao.getGoods(num);
		// request 자바빈 gBean 저장
		request.setAttribute("gBean", gBean);
		// 이동 ./goods/goods_detail.jsp
		ActionForward forward = new ActionForward();
		forward.setPath("./goods/goods_detail.jsp");
		forward.setRedirect(false);
		return forward;		
	}
}

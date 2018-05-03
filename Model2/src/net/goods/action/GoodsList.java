package net.goods.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.goods.db.GoodsDAO;

public class GoodsList implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("GoodsList execute()");
		// 리퀘스트 item 값을 변수에 담는다.
		String item = request.getParameter("item");
		// item이 없으면 "all" 설정
		if (item==null) {
			item="all";
		}
		// GoodsDAO 객체생성
		GoodsDAO gdao = new GoodsDAO();
		//List goodsList= getGoodsList()호출
		List goodsList = gdao.getGoodsList(item);
		// request "goodsList" 저장
		request.setAttribute("goodsList", goodsList);
		// 이동 ./goods/goods_list.jsp
		ActionForward forward = new ActionForward();
		forward.setPath("./goods/goods_list.jsp");
		forward.setRedirect(false);
		return forward;
	}
}

package net.admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.goods.db.AdminGoodsDAO;

public class GoodsDelete implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("GoodsDelete execute()");
		// int num =   num파라미터 가져오기
		int num=Integer.parseInt(request.getParameter("num"));
		//  AdminGoodsDAO agdao 객체 생성
		AdminGoodsDAO agdao=new AdminGoodsDAO();
		//  deleteGoods(int num) 메서드호출
		agdao.deleteGoods(num);
		//  이동 ./GoodsList.ag
		ActionForward forward=new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./GoodsList.ag");
		return forward;
	}
}

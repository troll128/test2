package net.admin.goods.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.goods.db.AdminGoodsDAO;

public class GoodsList implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("GoodsList execute()");
		
		// AdminGoodsDAO agdao 객체생성
		AdminGoodsDAO agdao=new AdminGoodsDAO();
		// List goodsList= 메서드호출 getGoodsList()
		List goodsList=agdao.getGoodsList();
		// request에  goodsList 저장
		request.setAttribute("goodsList", goodsList);
		// 이동  ./admingoods/admin_goods_list.jsp
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./admingoods/admin_goods_list.jsp");
		return forward;
	}
}

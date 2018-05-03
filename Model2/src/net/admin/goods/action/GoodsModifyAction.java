package net.admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.goods.db.AdminGoodsDAO;
import net.admin.goods.db.GoodsBean;

public class GoodsModifyAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("GoodsModifyAction execute()");
		//request 한글처리
		request.setCharacterEncoding("utf-8");
		// 자바빈 goodsbean 객체 생성
		GoodsBean goodsbean=new GoodsBean();
		goodsbean.setAmount(Integer.parseInt(request.getParameter("amount")));
		goodsbean.setBest(Integer.parseInt(request.getParameter("best")));
		goodsbean.setNum(Integer.parseInt(request.getParameter("num")));
		goodsbean.setPrice(Integer.parseInt(request.getParameter("price")));
		
		goodsbean.setCategory(request.getParameter("category"));
		goodsbean.setColor(request.getParameter("color"));
		goodsbean.setContent(request.getParameter("content"));
		goodsbean.setName(request.getParameter("name"));
		goodsbean.setSize(request.getParameter("size"));
		
		//  자비빈 변수 <= 파라미터 가져와서 저장
		// AdminGoodsDAO agdao 객체 생성
		AdminGoodsDAO agdao=new AdminGoodsDAO();
		//  modifyGoods(GoodsBean goodsbean) 메서드호출
		agdao.modifyGoods(goodsbean);
		// 이동 ./GoodsList.ag
		ActionForward forward=new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./GoodsList.ag");
		return forward;
	}
}

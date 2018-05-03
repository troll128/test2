package net.admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.admin.goods.db.AdminGoodsDAO;
import net.admin.goods.db.GoodsBean;

public class GoodsAddAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("GoodsAddAction execute()");
		
		// cos.jar 설치
		//  폴더  /upload 만들기
		// 업로드  MultipartRequest 객체 생성
		String realPath=request.getRealPath("/upload");
		int maxSize=5*1024*1024;
		System.out.println(realPath);
		MultipartRequest multi=
		new MultipartRequest(request, realPath,maxSize,"utf-8",new DefaultFileRenamePolicy());
		
		// 자바빈 파일 만들기 net.admin.goods.db 파일 GoodsBean
		//자바빈 파일 생성 GoodsBean gBean
		GoodsBean gBean=new GoodsBean();
		// 폼 => 자바빈 저장
		gBean.setCategory(multi.getParameter("category"));
		gBean.setName(multi.getParameter("name"));
		gBean.setPrice(Integer.parseInt(multi.getParameter("price")));
		gBean.setColor(multi.getParameter("color"));
		gBean.setAmount(Integer.parseInt(multi.getParameter("amount")));
		gBean.setSize(multi.getParameter("size"));
		gBean.setContent(multi.getParameter("content"));
		gBean.setImage(multi.getFilesystemName("file1")+","+multi.getFilesystemName("file2")+","+multi.getFilesystemName("file3")+","+multi.getFilesystemName("file4"));
		
		// 디비 파일 만들기 net.admin.goods.db 파일 AdminGoodsDAO
		// 객체 생성 AdminGoodsDAO agdao
		AdminGoodsDAO agdao=new AdminGoodsDAO();
		//객체 생성 메서드호출 insertGoods(gBean)
		agdao.insertGoods(gBean);
		// 이동     ./GoodsList.ag
		ActionForward forward=new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./GoodsList.ag");
		return forward;
	}
}

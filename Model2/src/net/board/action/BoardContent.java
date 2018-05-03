package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardContent implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardContent execute()");
		//int num, String pageNum 파라미터 가져오기
		int num=Integer.parseInt(request.getParameter("num"));
		String pageNum=request.getParameter("pageNum");
		// BoardDAO bdao 객체생성
		BoardDAO bdao=new BoardDAO();
		// 자바빈   bb = getBoard(num)메서드호출()
		BoardBean bb=bdao.getBoard(num);
		// request 자바빈 bb 저장
		request.setAttribute("bb", bb);
		// 이동 ./board/content.jsp
		ActionForward forward=new ActionForward();
		forward.setPath("./board/content.jsp");
		forward.setRedirect(false);
		return forward;
	}
}

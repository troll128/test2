package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardReWriteAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardReWriteAction execute()");
		//한글처리
		request.setCharacterEncoding("utf-8");
		// 자바빈 객체 생성 BoardBean bb
		BoardBean bb=new BoardBean();
		// 자바빈 멤버변수 <= 파라미터 가져와서 저장
		bb.setContent(request.getParameter("content"));
		bb.setName(request.getParameter("name"));
		bb.setPass(request.getParameter("pass"));
		bb.setSubject(request.getParameter("subject"));
		bb.setRe_ref(Integer.parseInt(request.getParameter("re_ref")));
		bb.setRe_lev(Integer.parseInt(request.getParameter("re_lev")));
		bb.setRe_seq(Integer.parseInt(request.getParameter("re_seq")));
		// 디비작업 객체생성 BoardDAO bdao
		BoardDAO bdao=new BoardDAO();
		// 메서드호출() reInsertBoard(bb)
		bdao.reInsertBoard(bb);
		// 게시판 목록 이동 
		// ActionForward 객체 생성  // "./BoardList.bo" 이동
		ActionForward forward=new ActionForward();
		forward.setPath("./BoardList.bo");
		forward.setRedirect(true);
		return forward;
	}
}

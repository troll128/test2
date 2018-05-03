package net.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardUpdateAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 한글처리
		request.setCharacterEncoding("utf-8");
		// pageNum 파라미터 가져오기
		String pageNum=request.getParameter("pageNum");
		// BoardBean bb 객체 생성
		// bb 멤버변수 <= 파라미터 가져와서 저장 
		BoardBean bb=new BoardBean();
		bb.setNum(Integer.parseInt(request.getParameter("num")));
		bb.setName(request.getParameter("name"));
		bb.setPass(request.getParameter("pass"));
		bb.setSubject(request.getParameter("subject"));
		bb.setContent(request.getParameter("content"));
		// BoardDAO bdao 객체생성
		BoardDAO bdao=new BoardDAO();
		// int check=updateBoard(bb) 메서드호출
		int check=bdao.updateBoard(bb);
		// check==1  "수정성공" ./BoardList.bo?pageNum=
		// check==0  "비밀번호틀림" 뒤로이동
		//            "글번호없음" 뒤로이동   
		if(check==1){
			response.setContentType("text/html; charset=UTF-8");
			// 출력할 객체 생성
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('수정성공');");
			out.println("location.href='./BoardList.bo?pageNum="+pageNum+"';");
			out.println("</script>");
			out.close();
			return null;
		}else if(check==0){
			response.setContentType("text/html; charset=UTF-8");
			// 출력할 객체 생성
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호틀림');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}else{
			response.setContentType("text/html; charset=UTF-8");
			// 출력할 객체 생성
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('글번호없음');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
	}
}

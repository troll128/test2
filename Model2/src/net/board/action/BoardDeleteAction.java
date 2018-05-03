package net.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardDAO;

public class BoardDeleteAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardDeleteAction execute()");
		// pageNum  num pass 파라미터 가져오기
		String pageNum=request.getParameter("pageNum");
		String pass=request.getParameter("pass");
		int num=Integer.parseInt(request.getParameter("num"));
		// BoardDAO bdao 객체 생성
		BoardDAO bdao=new BoardDAO();
		// int check= deleteBoard(num,pass) 메서드호출
		int check=bdao.deleteBoard(num,pass);
		// check==1 "삭제성공" ./BoardList.bo?pageNum=
		// check==0 "비밀번호틀림" 뒤로이동
		//          "글번호없음" 뒤로이동
		if(check==1){
			response.setContentType("text/html; charset=UTF-8");
			// 출력할 객체 생성
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('삭제성공');");
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

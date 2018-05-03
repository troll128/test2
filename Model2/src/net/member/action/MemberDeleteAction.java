package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberDAO;

public class MemberDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Action 상속  메서드 오버라이딩 
		// id,pass 파라미터 가져오기
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		// MemberDAO mdao 객체생성
		MemberDAO mdao=new MemberDAO();
		// int check = deleteMember(id,pass)
		int check=mdao.deleteMember(id, pass);
		// check==1  세션초기화 삭제성공 ./Main.me
		// check==0  비밀번호틀림 뒤로이동
		// check==-1 아이디없음 뒤로이동
		if(check==1){
			//세션 초기화
			HttpSession session=request.getSession();
			session.invalidate();
			response.setContentType("text/html; charset=UTF-8");
			// 출력할 객체 생성
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('삭제성공');");
			out.println("location.href='./Main.me';");
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
			out.println("alert('아이디없음');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
	}

}

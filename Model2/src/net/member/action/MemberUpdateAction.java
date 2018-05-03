package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberUpdateAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberUpdateAction execute()");
		// 한글처리
		request.setCharacterEncoding("utf-8");
		// MemberBean mb 객체생성
		MemberBean mb=new MemberBean();
		// mb set메서드 <= 파라미터 값 가져와서 저장
		mb.setId(request.getParameter("id"));
		mb.setPass(request.getParameter("pass"));
		mb.setName(request.getParameter("name"));
		// MemberDAO mdao 객체생성
		MemberDAO mdao=new MemberDAO();
		// int check = updateMember(mb)
		int check=mdao.updateMember(mb);
		// check==1  "수정성공" ./Main.me
		// check==0   "비밀번호틀림" 뒤로이동
		// check==-1  "아이디없음" 뒤로이동
		if(check==1){
			response.setContentType("text/html; charset=UTF-8");
			// 출력할 객체 생성
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('수정성공');");
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

package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberDAO;

public class MemberLoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	System.out.println("MemberLoginAction execute");
//  id pass 파라미터 가져오기
	String id=request.getParameter("id");
	String pass=request.getParameter("pass");
	// 디비 객체 생성 MemberDAO mdao 
	MemberDAO mdao=new MemberDAO();
//  메서드호출   int check = userCheck(id,pass)
	int check=mdao.userCheck(id, pass);
	// check==1 아이디,비밀번호 일치 로그인인증 
//  세션값생성"id",id    ./Main.me 이동 
//check==0 "비밀번호틀림" 뒤로이동
//check==-1 "아이디없음" 뒤로이동
	if(check==1){
		//세션 객체 생성
		HttpSession session=request.getSession();
	//  세션값생성"id",id    ./Main.me 이동 
		session.setAttribute("id",id);
		// 이동 ./Main.me
		ActionForward forward=new ActionForward();
		forward.setPath("./Main.me");
		forward.setRedirect(true);
		return forward;
	}else if(check==0){
		// 자바스크립트 사용
		// 자바 => text/html 변경
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

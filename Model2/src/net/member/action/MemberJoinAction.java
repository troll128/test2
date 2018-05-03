package net.member.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberJoinAction implements Action{
	// alt shift s  => v
	//메서드오버라이딩 : 부모의 함수 재정의
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberJoinAction execute()");
		//한글처리
		request.setCharacterEncoding("utf-8");
		//자바빈  MemberBean mb 객체 생성
		MemberBean mb=new MemberBean();
		// set호출 <= 폼 파라미터 가져와서 저장 
		mb.setId(request.getParameter("id"));
		mb.setName(request.getParameter("name"));
		mb.setPass(request.getParameter("pass"));
		mb.setReg_date(new Timestamp(System.currentTimeMillis()));
		//MemberDAO mdao 객체 생성
		MemberDAO mdao=new MemberDAO();
		// insertMember(자바빈 변수) 호출
		mdao.insertMember(mb);
		
		// ActionForward forward 객체 생성
		ActionForward forward=new ActionForward();
		//이동 정보저장   ./MemberLogin.me  
		// localhost:8080/Model2/MemberLogin.me  
		forward.setPath("./MemberLogin.me");
		forward.setRedirect(true);
		return forward;
	}
}

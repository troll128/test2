package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberInfo implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberInfo execute()");
		// 세션값 가져오기
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		// MemberDAO mdao 객체 생성
		MemberDAO mdao=new MemberDAO();
		// 자바빈 mb = getMember(세션값)
		MemberBean mb=mdao.getMember(id);
		
		// 자바빈 mb 데이터를 info.jsp 들고 이동
		// request <= 자바빈 mb 저장
		// request.setAttribute("이름",값)
		request.setAttribute("mb", mb);
		// ./member/info.jsp 이동 , 이동방식 forward()
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./member/info.jsp");
		return forward;
	}

}

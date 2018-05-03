package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberUpdate implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberUpdate execute()");
		// 세션값 가져오기
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		// MemberDAO mdao 객체 생성
		MemberDAO mdao=new MemberDAO();
		// 자바빈 mb = getMember(세션값)
		MemberBean mb=mdao.getMember(id);
		// 자바빈 mb 데이터를 updateForm.jsp 들고 이동
		request.setAttribute("mb", mb);
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./member/updateForm.jsp");
		return forward;
	}

}

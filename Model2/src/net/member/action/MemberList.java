package net.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberDAO;

public class MemberList implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// MemberDAO mdao 객체 생성
		MemberDAO mdao=new MemberDAO();
		// List memberList = getMemberList()
		List memberList=mdao.getMemberList();
		//저장 memberList 
		request.setAttribute("memberList", memberList);
		// 이동 ./member/list.jsp
		ActionForward forward=new ActionForward();
		forward.setPath("./member/list.jsp");
		forward.setRedirect(false);
		return forward;
	}
}

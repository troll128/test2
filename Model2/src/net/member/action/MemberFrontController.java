package net.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 자바파일 => 서블릿파일 만듬(서블릿 상속)
public class MemberFrontController extends HttpServlet{
	//메서드 오버라이딩(상속한 부모의 메서드를 재정의)
	// alt shift s   v 
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
System.out.println("MemberFrontController doProcess()");
//가상주소  http://localhost:8080/Model2/MemberJoin.me
// 뽑아오기  /MemberJoin.me
// URI 주소 뽑아오기 /Model2/MemberJoin.me
	String requestURI=request.getRequestURI();
	System.out.println("URI주소 : "+requestURI);
	//  /Model2  뽑아오기 
	String contextPath=request.getContextPath();
	System.out.println("프로젝트 경로 : "+contextPath);
	System.out.println("프로젝트 길이 : "+contextPath.length());
	//  /MemberJoin.me 뽑아오기
String command
     =requestURI.substring(contextPath.length());
System.out.println("뽑아낸 가상주소 : "+command);
//가상주소  http://localhost:8080/Model2/MemberJoin.me
	//가상주소 비교하기
ActionForward forward=null;
Action action=null;
	if(command.equals("/MemberJoin.me")){
		//   ./member/insertForm.jsp 이동
		// response.sendRedirect("./member/insertForm.jsp");
		// forward 이동  RequestDispatcher forward()
//		RequestDispatcher dispatcher
//		=request.getRequestDispatcher("./member/insertForm.jsp");
//		dispatcher.forward(request, response);
		// ActionForward 객체 생성
		forward=new ActionForward();
		forward.setPath("./member/insertForm.jsp");
		forward.setRedirect(false);
	}else if(command.equals("/MemberJoinAction.me")){
		// 회원가입 처리작업  
		// 처리작업틀을 받아서 만든 자바파일 메서드 호출
		// Action 틀 상속받은 자바(MemberJoinAction.java)파일 만들기
		// MemberJoinAction 객체 생성
		//MemberJoinAction mja=new MemberJoinAction();
		action=new MemberJoinAction();
		// execute()메서호출
		try {
			forward=action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}else if(command.equals("/MemberLogin.me")){
		//  ./member/loginForm.jsp 이동
		forward=new ActionForward();
		forward.setPath("./member/loginForm.jsp");
		forward.setRedirect(false);
	}else if(command.equals("/MemberLoginAction.me")){
		// MemberLoginAction 파일 만들기
		// Action상속 메서드 오버라이딩
		// MemberLoginAction 객체생성
		action=new  MemberLoginAction();
		// execute() 메서드 호출
		try {
		forward=action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}else if(command.equals("/Main.me")){
		// ./member/main.jsp 
		forward=new ActionForward();
		forward.setPath("./member/main.jsp");
		forward.setRedirect(false);
	}else if(command.equals("/MemberLogoutAction.me")){
		action=new  MemberLogoutAction();
		// execute() 메서드 호출
		try {
		forward=action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}else if(command.equals("/MemberInfo.me")){
		// MemberInfo  execute() 호출
		action=new  MemberInfo();
		// execute() 메서드 호출
		try {
		forward=action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}else if(command.equals("/MemberUpdate.me")){
		action=new  MemberUpdate();
		// execute() 메서드 호출
		try {
		forward=action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}else if(command.equals("/MemberUpdateAction.me")){
		action=new  MemberUpdateAction();
		// execute() 메서드 호출
		try {
		forward=action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}else if(command.equals("/MemberDelete.me")){
		//   ./member/deleteForm.jsp
		forward=new ActionForward();
		forward.setPath("./member/deleteForm.jsp");
		forward.setRedirect(false);
	}else if(command.equals("/MemberDeleteAction.me")){
		//   MemberDeleteAction  execute() 호출
		action=new  MemberDeleteAction();
		// execute() 메서드 호출
		try {
		forward=action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}else if(command.equals("/MemberList.me")){
		//  MemberList 객체생성  execute 메서드호출()
		action=new  MemberList();
		// execute() 메서드 호출
		try {
		forward=action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//이동
	// forward 이동정보가 있으면 
	if(forward!=null){
		if(forward.isRedirect()){
			//이동방식이 true 
			response.sendRedirect(forward.getPath());
		}else{
			//이동방식이 false
			RequestDispatcher dispatcher
			=request.getRequestDispatcher(forward.getPath());
			dispatcher.forward(request, response);
		}
	}

	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
System.out.println("MemberFrontController doGet()");
doProcess(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
System.out.println("MemberFrontController doPost()");
doProcess(request, response);
	}
}

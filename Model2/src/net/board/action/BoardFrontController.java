package net.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardFrontController extends HttpServlet{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardFrontController doProcess()");
		// 가상의 주소 http://localhost:8080/Model2/BoardWrite.bo
		// 가상주소 뽑아오기
		String requestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=requestURI.substring(contextPath.length());
	    //  /BoardWrite.bo
		System.out.println("뽑아온 주소 : "+command); 
		// 뽑아온 가상주소 비교
		ActionForward forward=null;
		Action action=null;
		if(command.equals("/BoardWrite.bo")){
			// forward 객체생성
			forward=new ActionForward();
			// 이동경로 ./board/writeForm.jsp
			forward.setPath("./board/writeForm.jsp");
			// 이동방식  저장
			forward.setRedirect(false);
		}else if(command.equals("/BoardWriteAction.bo")){
			// BoardWriteAction 만들기  // Action 인터페이스 상속
			// BoardWriteAction 객체 생성 // execute() 메서드호출
			action=new BoardWriteAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/BoardList.bo")){
			// BoardList 만들기 인터페이스 상속
			// 객체 생성 // 메서드호출
			action=new BoardList();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/BoardContent.bo")){
			action=new BoardContent();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/BoardUpdate.bo")){
			action=new BoardUpdate();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/BoardUpdateAction.bo")){
			action=new BoardUpdateAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/BoardDelete.bo")){
			//  ./board/deleteForm.jsp
			// forward 객체생성
			forward=new ActionForward();
			// 이동경로   ./board/deleteForm.jsp
			forward.setPath("./board/deleteForm.jsp");
			// 이동방식  저장
			forward.setRedirect(false);
		}else if(command.equals("/BoardDeleteAction.bo")){
			action=new BoardDeleteAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/BoardReWrite.bo")){
			//  ./board/reWriteForm.jsp 이동
			// forward 객체생성
			forward=new ActionForward();
			// 이동경로   ./board/reWriteForm.jsp
			forward.setPath("./board/reWriteForm.jsp");
			// 이동방식  저장
			forward.setRedirect(false);
		}else if(command.equals("/BoardReWriteAction.bo")){
			//  BoardReWriteAction
			action=new BoardReWriteAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 이동
		if(forward!=null){
			if(forward.isRedirect()){
				// sendRedirect()
				response.sendRedirect(forward.getPath());
			}else{
				// forward()
				RequestDispatcher dispatcher
				  =request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardFrontController doGet()");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardFrontController doPost()");
		doProcess(request, response);
	}

}

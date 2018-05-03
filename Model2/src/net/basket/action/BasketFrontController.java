package net.basket.action;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.action.BoardList;

public class BasketFrontController extends HttpServlet{
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BasketFrontController doProcess()");
		// 가상의 주소 http://localhost:8080/Model2/BoardWrite.bo
		// 가상주소 뽑아오기
		String requestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=requestURI.substring(contextPath.length());	    
		System.out.println("뽑아온 주소 : "+command); 
		// 뽑아온 가상주소 비교
		ActionForward forward=null;
		Action action=null;
		if(command.equals("/BasketAdd.ba")){	// 장바구니 추가
			action=new BasketAdd();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/BasketList.ba")){	// 장바구니 리스트로 이동
			action=new BasketList();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/BasketDelete.ba")){	// 장바구니 삭제
			action=new BasketDelete();
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

package net.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardDAO;

public class BoardList implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardList execute()");
		
		//BoardDAO bdao 객체 생성
		BoardDAO bdao=new BoardDAO();
		// int count= getBoardCount()메서드호출   count(*)
		int count=bdao.getBoardCount();
		//한페이지에 보여줄 글개수 설정
		int pageSize=10;
		//페이지번호 가져오기 페이지번호가 없으면 "1"페이지로 설정
		String pageNum=request.getParameter("pageNum");
		if(pageNum==null){
			pageNum="1";
		}
		// 10개로 나누어서 2번째페이지 시작하는 행번호 구하기 
		//int startRow=(현페이지-1)*한화면에 보여줄글개수+1;
		int currentPage=Integer.parseInt(pageNum);
		int startRow=(currentPage-1)*pageSize+1;
		// 10개로 나누어서 2번째페이지 끝나는 행번호 구하기
		//int endRow= 현페이지번호   한화면에 보여줄글개수
		int endRow= currentPage *  pageSize;
		List boardList=null;
		if(count!=0){
			//boardList =getBoardList(startRow,pageSize);메서드호출
			boardList=bdao.getBoardList(startRow, pageSize);
			// sql 전체 게시판 글가져오기  정렬 num 내림차순  limit 첫행-1,글개수
		}
		
		// 전체 페이지수 구하기    
		//게시판 전체 글개수  50개  한화면에 글개수10개씩보이기 
		// => 50/10 = 5+나머지없으면 0=5페이지
		//게시판 전체 글개수  55개  한화면에 글개수10개씩보이기 
		// => 55/10 = 5+나머지 있으면 1=6페이지
		int pageCount=count/pageSize + (count%pageSize==0?0:1);
		// 한화면에 보여줄 페이지수 설정
		int pageBlock=10;
		//한화면에 보여줄 첫페이지번호 구하기  현페이지 1~10 => 1  11~20 => 11
		int startPage=((currentPage-1)/pageBlock)*pageBlock+1 ;
		//한화면에 보여줄 끝페이지번호 구하기  1~10 => 10  11~20=>20
		int endPage=startPage+pageBlock-1;
		if(pageCount < endPage){
			endPage=pageCount;
		}
		
		// list.jsp 필요로하는 request 값 저장
		//  count , pageNum, boardList, pageCount, pageBlock
		//  startPage, endPage
		request.setAttribute("count", count);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("boardList", boardList);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		// 이동  list.jsp 
		ActionForward forward=new ActionForward();
		forward.setPath("./board/list.jsp");
		forward.setRedirect(false);
		return forward;
	}
}

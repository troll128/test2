package net.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class BoardDAO {
	//디비연결 메서드
	private Connection getConnection() throws Exception{
		Connection con=null;
		Context init=new InitialContext();
		DataSource ds=(DataSource)init.lookup("java:comp/env/jdbc/Mysql");
		con=ds.getConnection();
		return con;
	}
	//getBoardCount() 함수만들기
	public int getBoardCount(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		int count=0;
		try {
			//1,2 디비연결
			con=getConnection();
			//3 sql board모든 데이터 가져와서 개수 카운트
			sql="select count(*) from board";
			pstmt=con.prepareStatement(sql);
			//4 rs <= 실행
			rs=pstmt.executeQuery();
			//5 rs 첫행이동 데이터 있으면 count=가져온개수 저장
			if(rs.next()){
				count=rs.getInt(1); //1열 데이터 가져오기
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)
				try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)
				try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)
				try{con.close();}catch(SQLException ex){}
		}
		return count;
	}
	
	public int getBoardCount(String search){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		int count=0;
		try {
			//1,2 디비연결
			con=getConnection();
			//3 sql board모든 데이터 가져와서 개수 카운트   '%검색어%'
			sql="select count(*) from board where subject like ?";
			pstmt=con.prepareStatement(sql);
			// '%검색어%'
			pstmt.setString(1, "%"+search+"%");
			//4 rs <= 실행
			rs=pstmt.executeQuery();
			//5 rs 첫행이동 데이터 있으면 count=가져온개수 저장
			if(rs.next()){
				count=rs.getInt(1); //1열 데이터 가져오기
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)
				try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)
				try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)
				try{con.close();}catch(SQLException ex){}
		}
		return count;
	}
	
	//getBoardList(시작행번호, 가져올글개수)
	public List getBoardList(int startRow, int pageSize){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		List boardList=new ArrayList();
		try {
			//1,2 디비연결
			con=getConnection();
			//3 sql
			sql="select * from board order by re_ref desc,re_seq asc limit ?,?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow-1);
			pstmt.setInt(2, pageSize);
			//4 rs <= 실행결과저장
			rs=pstmt.executeQuery();
			//5 rs 첫행이동 데이터 있으면
			//  패키지 board 파일이름 BoardBean
			// 자바빈 객체 생성  BoardBean bb
			// 자바빈 멤버변수 <= rs 가져온 열 저장
			// boardList 배열 한칸에 저장
			while(rs.next()){
				BoardBean bb=new BoardBean();
				bb.setNum(rs.getInt("num"));
				bb.setContent(rs.getString("content"));
				bb.setDate(rs.getDate("date"));
				bb.setFile(rs.getString("file"));
				bb.setName(rs.getString("name"));
				bb.setPass(rs.getString("pass"));
				bb.setRe_lev(rs.getInt("re_lev"));
				bb.setRe_ref(rs.getInt("re_ref"));
				bb.setRe_seq(rs.getInt("re_seq"));
				bb.setReadcount(rs.getInt("readcount"));
				bb.setSubject(rs.getString("subject"));
				boardList.add(bb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)
				try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)
				try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)
				try{con.close();}catch(SQLException ex){}
		}
		return boardList;
	}
	
	//getBoardList(시작행번호, 가져올글개수)
		public List getBoardList(int startRow, int pageSize, String search){
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String sql="";
			List boardList=new ArrayList();
			try {
				//1,2 디비연결
				con=getConnection();
				//3 sql
				sql="select * from board where subject like ? order by re_ref desc,re_seq asc limit ?,?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, "%"+search+"%");
				pstmt.setInt(2, startRow-1);
				pstmt.setInt(3, pageSize);
				//4 rs <= 실행결과저장
				rs=pstmt.executeQuery();
				//5 rs 첫행이동 데이터 있으면
				//  패키지 board 파일이름 BoardBean
				// 자바빈 객체 생성  BoardBean bb
				// 자바빈 멤버변수 <= rs 가져온 열 저장
				// boardList 배열 한칸에 저장
				while(rs.next()){
					BoardBean bb=new BoardBean();
					bb.setNum(rs.getInt("num"));
					bb.setContent(rs.getString("content"));
					bb.setDate(rs.getDate("date"));
					bb.setFile(rs.getString("file"));
					bb.setName(rs.getString("name"));
					bb.setPass(rs.getString("pass"));
					bb.setRe_lev(rs.getInt("re_lev"));
					bb.setRe_ref(rs.getInt("re_ref"));
					bb.setRe_seq(rs.getInt("re_seq"));
					bb.setReadcount(rs.getInt("readcount"));
					bb.setSubject(rs.getString("subject"));
					boardList.add(bb);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(rs!=null)
					try{rs.close();}catch(SQLException ex){}
				if(pstmt!=null)
					try{pstmt.close();}catch(SQLException ex){}
				if(con!=null)
					try{con.close();}catch(SQLException ex){}
			}
			return boardList;
		}
		
		//getBoard(num)
		public BoardBean getBoard(int num){
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String sql="";
			BoardBean bb=null;
			try {
				//1,2 디비연결
				con=getConnection();
				//3 sql board 모든 정보 가져오기 조건 num=?
				sql="select * from board where num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, num);
				//4 rs = 실행 저장
				rs=pstmt.executeQuery();
				//5  rs 첫행이동 데이터 있으면 저장된 내용을=> 자바빈 저장 
				//   자바빈 객체 생성
				//   자바빈 변수 <= rs 
				if(rs.next()){
					bb=new BoardBean();
					bb.setNum(rs.getInt("num"));
					bb.setContent(rs.getString("content"));
					bb.setDate(rs.getDate("date"));
					bb.setFile(rs.getString("file"));
					bb.setName(rs.getString("name"));
					bb.setPass(rs.getString("pass"));
					bb.setRe_lev(rs.getInt("re_lev"));
					bb.setRe_ref(rs.getInt("re_ref"));
					bb.setRe_seq(rs.getInt("re_seq"));
					bb.setReadcount(rs.getInt("readcount"));
					bb.setSubject(rs.getString("subject"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(rs!=null)
					try{rs.close();}catch(SQLException ex){}
				if(pstmt!=null)
					try{pstmt.close();}catch(SQLException ex){}
				if(con!=null)
					try{con.close();}catch(SQLException ex){}
			}
			return bb;
		}
		
		//insertBoard(자바빈 주소)
		public void insertBoard(BoardBean bb){
			Connection con=null;
			PreparedStatement pstmt=null;
			String sql="";
			ResultSet rs=null;
			int n=0;
			try {
				//1단계 드라이버로더
				//2단계 디비연결
				con=getConnection();
				//게시판 글번호 구하기  max(num)
				//3단계 sql  게시판 글중에 가장큰 게시판 번호구하기
				sql="select max(num) from board";
				pstmt=con.prepareStatement(sql);
				//4단계 결과 저장 <= 실행
				rs=pstmt.executeQuery();
				//5단계 첫행 이동 데이터 있으면  결과 +1
				if(rs.next()){
				   n=rs.getInt(1)+1;
				}
				//3단계 sql  insert 
				sql="insert into board(num,name,pass,subject,content,readcount,re_ref,re_lev,re_seq,file,date) values(?,?,?,?,?,?,?,?,?,?,now())";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, n);//글번호
				pstmt.setString(2, bb.getName()); //글쓴이
				pstmt.setString(3, bb.getPass());//비밀번호
				pstmt.setString(4, bb.getSubject());//제목
				pstmt.setString(5, bb.getContent());//내용
				pstmt.setInt(6, 0); //조회수 0
				pstmt.setInt(7, n); //re_ref  num = re_ref 같에 입력
				pstmt.setInt(8, 0); //re_lev   0
				pstmt.setInt(9, 0); //re_seq   0
				pstmt.setString(10, bb.getFile());
				//4단계 실행
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				//닫기
				if(rs!=null)
					try{rs.close();}catch(SQLException ex){}
				if(pstmt!=null)
					try{pstmt.close();}catch(SQLException ex){}
				if(con!=null)
					try{con.close();}catch(SQLException ex){}
			}
		}
		
		//reInsertBoard(자바빈 주소)
				public void reInsertBoard(BoardBean bb){
					Connection con=null;
					PreparedStatement pstmt=null;
					String sql="";
					ResultSet rs=null;
					int n=0;
					try {
						//1단계 드라이버로더
						//2단계 디비연결
						con=getConnection();
						//게시판 글번호 구하기  max(num)
						//3단계 sql  게시판 글중에 가장큰 게시판 번호구하기
						sql="select max(num) from board";
						pstmt=con.prepareStatement(sql);
						//4단계 결과 저장 <= 실행
						rs=pstmt.executeQuery();
						//5단계 첫행 이동 데이터 있으면  결과 +1
						if(rs.next()){
						   n=rs.getInt(1)+1;
						}
						// 3단계 sql  update  re_seq+1 조건 같은그룹 이고 답글있으면
						// 답글을 달고자하는 글밑에 답글이 있으면 답글순서 재배치
						sql="update board set re_seq=re_seq+1 where re_ref=? and re_seq>?";
						pstmt=con.prepareStatement(sql);
						pstmt.setInt(1, bb.getRe_ref()); //re_ref 같은 그룹
						pstmt.setInt(2, bb.getRe_seq()); //re_seq 순서값이 나보다 큰경우
						// 4단계 실행
						pstmt.executeUpdate();
						//3단계 sql  insert 
						sql="insert into board(num,name,pass,subject,content,readcount,re_ref,re_lev,re_seq,date) values(?,?,?,?,?,?,?,?,?,now())";
						pstmt=con.prepareStatement(sql);
						pstmt.setInt(1, n);//글번호
						pstmt.setString(2, bb.getName()); //글쓴이
						pstmt.setString(3, bb.getPass());//비밀번호
						pstmt.setString(4, bb.getSubject());//제목
						pstmt.setString(5, bb.getContent());//내용
						pstmt.setInt(6, 0); //조회수 0
						pstmt.setInt(7, bb.getRe_ref()); //re_ref  re_ref 가져온값 그대로
						pstmt.setInt(8, bb.getRe_lev()+1); //re_lev   re_lev 가져온값 +1
						pstmt.setInt(9, bb.getRe_seq()+1); //re_seq   re_seq 가져온값 +1
						//4단계 실행
						pstmt.executeUpdate();
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						//닫기
						if(rs!=null)
							try{rs.close();}catch(SQLException ex){}
						if(pstmt!=null)
							try{pstmt.close();}catch(SQLException ex){}
						if(con!=null)
							try{con.close();}catch(SQLException ex){}
					}
				}
				//updateBoard(bb)
				public int updateBoard(BoardBean bb){
					int check=0;
					Connection con=null;
					PreparedStatement pstmt=null;
					String sql="";
					ResultSet rs=null;
					try {
						//1,2 디비연결
						con=getConnection();
						//3  sql 게시판글 가져오기 조건 num=?
						sql="select * from board where num=?";
						pstmt=con.prepareStatement(sql);
						pstmt.setInt(1, bb.getNum());
						//4 rs = 실행해서 결과 저장
						rs=pstmt.executeQuery();
						//5 rs 첫행이동 데이터있으면 게시판 글있음 
						//    폼비밀번호  디비비밀번호 비교 맞으면 check=1 
						//          //3 sql update //4 실행
						//    비밀번호 틀리면 check=0
						if(rs.next()){
							if(bb.getPass().equals(rs.getString("pass"))){
								check=1;
								//3
						sql="update board set name=?,subject=?,content=?  where num=?";
								pstmt=con.prepareStatement(sql);
								pstmt.setString(1, bb.getName());
								pstmt.setString(2, bb.getSubject());
								pstmt.setString(3, bb.getContent());
								pstmt.setInt(4, bb.getNum());
								//4
								pstmt.executeUpdate();
							}else{
								check=0;
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						//닫기
						if(rs!=null)
							try{rs.close();}catch(SQLException ex){}
						if(pstmt!=null)
							try{pstmt.close();}catch(SQLException ex){}
						if(con!=null)
							try{con.close();}catch(SQLException ex){}
					}
					return check;
				}	
				
				//deleteBoard(bb)
				public int deleteBoard(int num,String pass){
					int check=0;
					Connection con=null;
					PreparedStatement pstmt=null;
					String sql="";
					ResultSet rs=null;
					try {
						//1,2 디비연결
						con=getConnection();
						//3  sql 게시판글 가져오기 조건 num=?
						sql="select * from board where num=?";
						pstmt=con.prepareStatement(sql);
						pstmt.setInt(1, num);
						//4 rs = 실행해서 결과 저장
						rs=pstmt.executeQuery();
						//5 rs 첫행이동 데이터있으면 게시판 글있음 
						//    폼비밀번호  디비비밀번호 비교 맞으면 check=1 
						//          //3 sql update //4 실행
						//    비밀번호 틀리면 check=0
						if(rs.next()){
							if(pass.equals(rs.getString("pass"))){
								check=1;
								//3
						sql="delete from board  where num=?";
								pstmt=con.prepareStatement(sql);
								pstmt.setInt(1, num);
								//4
								pstmt.executeUpdate();
							}else{
								check=0;
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						//닫기
						if(rs!=null)
							try{rs.close();}catch(SQLException ex){}
						if(pstmt!=null)
							try{pstmt.close();}catch(SQLException ex){}
						if(con!=null)
							try{con.close();}catch(SQLException ex){}
					}
					return check;
				}	
		
}//클래스

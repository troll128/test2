package net.member.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	//디비연결 1,2단계 메서드 정의
	private Connection getConnection() throws Exception{
//		 //디비주소
//		 String dbUrl="jdbc:mysql://localhost:3306/jspdb";
//		 //디비유저
//		 String dbUser="jspid";
//		 //디비비밀번호
//		 String dbPass="jsppass";
//		 Connection con=null;
//		//1단계 드라이버로더
//		Class.forName("com.mysql.jdbc.Driver");
//		//2단계 디비연결 => 연결정보를 저장하는 객체
//		con=DriverManager.getConnection(dbUrl, dbUser, dbPass);
//		return con;
		
		Connection con=null;
		Context init=new InitialContext();
		DataSource ds=(DataSource)init.lookup("java:comp/env/jdbc/Mysql");
		con=ds.getConnection();
		return con;
		
	}
	
	//메서드(멤버함수)
	//모두접근가능  리턴할형없음  insertMember(자바빈 변수) 메서드 만들기
	public void insertMember(MemberBean mb){
		//자바 예외처리 코드
		 Connection con=null;
		 String sql="";
		 PreparedStatement pstmt=null;
		try {
			//예외가 발생할것같은 코드-> 디비연결
			//1단계 드라이버로더	//2단계 디비연결 //연결정보를 저장하는 내장객체
			con=getConnection();
			 //3단계 sql insert 만들어서 실행 내장객체
			 //Statement PreparedStatement CallableStatement
			 sql="insert into member(id,pass,name,reg_date) values(?,?,?,?)";
			 pstmt=con.prepareStatement(sql);
			 //?값 넣기
			 pstmt.setString(1,mb.getId()); //1 첫번째 물음표, id변수
			 pstmt.setString(2,mb.getPass()); //2 두번째 물음표, pass변수
			 pstmt.setString(3,mb.getName()); //3 세번째 물음표, name변수
			 pstmt.setTimestamp(4,mb.getReg_date());//4 네번째 물음표, reg_date변수
			 //4단계 sql 실행   insert,update,delete
			 pstmt.executeUpdate();
			 	//"회원가입성공"   loginForm.jsp 이동
		} catch (Exception e) {
			// Exception이 예외를 잡아서 처리->예외발생 메시지 출력
			e.printStackTrace();
		}finally{
			//예외가 발생하던지 안하던지 상관없이 마무리작업수행
			//객체생성 -> 종료  닫기 기억장소 정리
			if(pstmt!=null)
				try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)
				try{con.close();}catch(SQLException ex){}
		}
		 
	}//insertMember(MemberBean mb)메서드
	
	//userCheck(id,pass)
	public int userCheck(String id,String pass){
		int check=-1;
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		ResultSet rs=null;
		try {
			//1단계 드라이버로더
			//2단계 디비연결
			con=getConnection();
			//3단계 sql 조건 id에 해당하는 회원정보를 가져오기
			sql="select * from member where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,id);
			//4단계 결과저장 <= 실행
			rs=pstmt.executeQuery();
			//5단계 결과를 첫행으로 이동 데이터 있으면
			//     비밀번호 비교 맞으면 check=1
			//               틀리면 check=0
			//                   없으면 check=-1 "아이디없음"
			if(rs.next()){
				//아이디있음
				if(pass.equals(rs.getString("pass"))){
					check=1;//비밀번호 맞음 세션값생성
				}else{
					check=0;//비밀번호 틀림
				}
			}else{
				check=-1;//아이디없음
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//마무리  //기억장소 회수
			if(rs!=null)
				try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)
				try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)
				try{con.close();}catch(SQLException ex){}
		}
		return check;
	}
	
	//getMember(id)
	public MemberBean getMember(String id){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		ResultSet rs=null;
		//MemberBean형의  mb 변수 선언
				MemberBean mb=null;
		try {
			//1단계 드라이버 로더
			//2단계 디비연결
			con=getConnection();
			//3단계 sql id조건에 해당하는 회원정보가져오기
			sql="select * from member where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			//4단계 결과저장 =실행
			rs=pstmt.executeQuery();
			//5단계 첫행에 이동 데이터 있으면  
			
			//     mb가 참조하는 id변수에 rs에서 가져온 "id"값 저장
			//     mb가 참조하는 pass변수에 rs에서 가져온 "pass"값 저장
			//     mb가 참조하는 name변수에 rs에서 가져온 "name"값 저장
			//     mb가 참조하는 reg_date변수에 rs에서 가져온 "reg_date"값 저장
			if(rs.next()){
//		      mb 객체생성
				mb=new MemberBean();
				mb.setId(rs.getString("id"));
				mb.setPass(rs.getString("pass"));
				mb.setName(rs.getString("name"));
				mb.setReg_date(rs.getTimestamp("reg_date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//마무리
			if(rs!=null)
				try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)
				try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)
				try{con.close();}catch(SQLException ex){}
		}
		return mb;
	}
	
	//updateMember(mb)메서드
	public int updateMember(MemberBean mb){
				Connection con=null;
				PreparedStatement pstmt=null;
				String sql="";
				ResultSet rs=null;
		int check=-1;
		try{
			//1단계 드라이버 로더
			//2단계 디비연결
			con=getConnection();
			//3단계 sql 조건 id에 해당하는 회원정보 가져오기
			sql="select * from member where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mb.getId());
			//4단계 rs = 실행결과저장 
			rs=pstmt.executeQuery();
			//5단계 rs첫행이동 데이터 있으면 아이디있음
			//           비밀번호비교 맞으면 check=1
			//           3단계 update name수정  조건 id이면  4단계 실행
			//                     틀리면 check=0
			//             데이터 없으면 아이디없음 check=-1
			if(rs.next()){
				if(mb.getPass().equals(rs.getString("pass"))){
					check=1;
					//3 update
					sql="update member set name=? where id=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, mb.getName());
					pstmt.setString(2, mb.getId());
					//4 실행
					pstmt.executeUpdate();
				}else{
					//틀리면 check=0
					check=0;
				}
			}else{
				//데이터 없으면 아이디없음 check=-1
				check=-1;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
	
	
	// deleteMember(id,pass)
	public int deleteMember(String id,String pass){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		ResultSet rs=null;
int check=-1;
try{
	//1단계 드라이버 로더
	//2단계 디비연결
	con=getConnection();
	//3단계 sql 조건 id에 해당하는 회원정보 가져오기
	sql="select * from member where id=?";
	pstmt=con.prepareStatement(sql);
	pstmt.setString(1, id);
	//4단계 rs = 실행결과저장 
	rs=pstmt.executeQuery();
	//5단계 rs첫행이동 데이터 있으면 아이디있음
	//           비밀번호비교 맞으면 check=1
	//           3단계 update name수정  조건 id이면  4단계 실행
	//                     틀리면 check=0
	//             데이터 없으면 아이디없음 check=-1
	if(rs.next()){
		if(pass.equals(rs.getString("pass"))){
			check=1;
			//3 delete
			sql="delete from member where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			//4 실행
			pstmt.executeUpdate();
		}else{
			//틀리면 check=0
			check=0;
		}
	}else{
		//데이터 없으면 아이디없음 check=-1
		check=-1;
	}
}catch(Exception e){
	e.printStackTrace();
}finally {
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
	
	//getMemberList()
	public List getMemberList(){
				Connection con=null;
				PreparedStatement pstmt=null;
				String sql="";
				ResultSet rs=null;
		List memberList=new ArrayList();
		try {
			//1단계 드라이버로더
			//2단계 디비연결
			con=getConnection();
			//3단계 sql
			sql="select * from member";
			pstmt=con.prepareStatement(sql);
			//4단계 결과저장 <= 실행 
			rs=pstmt.executeQuery();
			//5단계 첫행(다음행)이동 데이터 있으면
			//  MemberBean mb 객체 생성
			//  mb id 변수 저장 set메서드 호출  rs 에 문자형 "id"열 가져와서 저장
			// memberList 한칸 저장  .add(자바빈주소)
			while(rs.next()){
				MemberBean mb=new MemberBean();
				mb.setId(rs.getString("id"));
				mb.setPass(rs.getString("pass"));
				mb.setName(rs.getString("name"));
				mb.setReg_date(rs.getTimestamp("reg_date"));
				memberList.add(mb);
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
		return memberList;
	}
	
}//클래스

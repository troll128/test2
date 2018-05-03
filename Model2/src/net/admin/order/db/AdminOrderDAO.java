package net.admin.order.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.order.db.OrderBean;

public class AdminOrderDAO {
	//디비연결
		private Connection getConnection() throws Exception{
			Connection con=null;
//			String url="jdbc:mysql://localhost:3306/jspdb";
//			String dbuser="jspid";
//			String dbpass="jsppass";
			//1단계 드라이버로더
//			Class.forName("com.mysql.jdbc.Driver");
			//2단계 디비연결
//			con=DriverManager.getConnection(url,dbuser,dbpass);
			
			//커넥션풀 : 데이터베이스와 연결된 Connection 객체를 미리 생성하여
			//풀속에 저장해 두고 필요할때마다 이 풀에 접근하여 Connection객체 사용
			Context init=new InitialContext();
			DataSource ds=(DataSource)init.lookup("java:comp/env/jdbc/Mysql");
			con=ds.getConnection();
			return con;
		}
		
		//getAdminOrderList()
		public List getAdminOrderList(){
			Connection con=null;
			PreparedStatement pstmt=null;
			String sql="";
			ResultSet rs=null;
			List adminOrderList=new ArrayList();
			try {
				//1,2 디비연결
				con=getConnection();
				//3 sql 테이블 g_order 
				// sum(o_sum_money) as o_sum_money
				// 그룹 o_trade_num 정렬 o_trade_num 내림차순
				sql="select o_trade_num,o_g_name,o_g_amount,o_g_size,o_g_color,sum(o_sum_money) as o_sum_money,o_trans_num , o_date, o_status,o_trade_type,o_m_id from g_order group by o_trade_num order by o_trade_num desc";
				pstmt=con.prepareStatement(sql);
				//4 rs 실행 결과 저장
				rs=pstmt.executeQuery();
				//5 rs 데이터 있으면 자바빈 OrderBean orderbean객체생성
			// rs => orderbean멤버변수 저장 => adminOrderList한칸저장
				while(rs.next()){
					OrderBean orderbean=new OrderBean();
					orderbean.setO_date(rs.getDate("o_date"));
					orderbean.setO_g_amount(rs.getInt("o_g_amount"));
					orderbean.setO_g_color(rs.getString("o_g_color"));
					orderbean.setO_g_name(rs.getString("o_g_name"));
					orderbean.setO_g_size(rs.getString("o_g_size"));
					orderbean.setO_trade_num(rs.getString("o_trade_num"));
					orderbean.setO_trans_num(rs.getString("o_trans_num"));
					orderbean.setO_sum_money(rs.getInt("o_sum_money"));
					orderbean.setO_status(rs.getInt("o_status"));
					orderbean.setO_trade_type(rs.getString("o_trade_type"));
					orderbean.setO_m_id(rs.getString("o_m_id"));
					adminOrderList.add(orderbean);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(rs!=null)try{rs.close();}catch(SQLException ex){}
				if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
				if(con!=null)try{con.close();}catch(SQLException ex){}
			}
			return adminOrderList;
		}
		//getAdminOrderDetail(trade_num)
		public List getAdminOrderDetail(String trade_num){
			Connection con=null;
			PreparedStatement pstmt=null;
			String sql="";
			ResultSet rs=null;
			List adminOrderDetail=new ArrayList();
			try {
				//1,2 디비연결
				con=getConnection();
				//3 sql 테이블 g_order 조건 o_trade_num
				sql="select * from g_order where o_trade_num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, trade_num);
				//4 rs실행 저장
				rs=pstmt.executeQuery();
				//5 rs데이터있으면 OrderBean orderbean객체생성
				//rs => orderbean멤버변수저장 
				//=> adminOrderDetail 한칸 저장
				while(rs.next()){
					OrderBean orderbean=new OrderBean();
					orderbean.setO_date(rs.getDate("o_date"));
					orderbean.setO_g_amount(rs.getInt("o_g_amount"));
					orderbean.setO_g_color(rs.getString("o_g_color"));
					orderbean.setO_g_name(rs.getString("o_g_name"));
					orderbean.setO_g_size(rs.getString("o_g_size"));
					orderbean.setO_trade_num(rs.getString("o_trade_num"));
					orderbean.setO_trans_num(rs.getString("o_trans_num"));
					orderbean.setO_sum_money(rs.getInt("o_sum_money"));
					orderbean.setO_status(rs.getInt("o_status"));
					orderbean.setO_trade_type(rs.getString("o_trade_type"));
					adminOrderDetail.add(orderbean);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(rs!=null)try{rs.close();}catch(SQLException ex){}
				if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
				if(con!=null)try{con.close();}catch(SQLException ex){}
			}
			return adminOrderDetail;
		}
	public void updateOrder(OrderBean orderbean){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		ResultSet rs=null;
		try{
			con=getConnection();
			sql="update g_order set o_status=? , o_trans_num=? where o_trade_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, orderbean.getO_status());
			pstmt.setString(2,orderbean.getO_trans_num() );
			pstmt.setString(3,orderbean.getO_trade_num() );
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		
		
	}	
}//클래스

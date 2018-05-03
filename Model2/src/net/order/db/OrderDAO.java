package net.order.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.admin.goods.db.GoodsBean;
import net.basket.db.BasketBean;

public class OrderDAO {
	//디비연결
	private Connection getConnection() throws Exception{
		Connection con=null;
//		String url="jdbc:mysql://localhost:3306/jspdb";
//		String dbuser="jspid";
//		String dbpass="jsppass";
		//1단계 드라이버로더
//		Class.forName("com.mysql.jdbc.Driver");
		//2단계 디비연결
//		con=DriverManager.getConnection(url,dbuser,dbpass);
		
		//커넥션풀 : 데이터베이스와 연결된 Connection 객체를 미리 생성하여
		//풀속에 저장해 두고 필요할때마다 이 풀에 접근하여 Connection객체 사용
		Context init=new InitialContext();
		DataSource ds=(DataSource)init.lookup("java:comp/env/jdbc/Mysql");
		con=ds.getConnection();
		return con;
	}
	
	//addOrder(orderbean,basketList,goodsList)
	public void addOrder(OrderBean orderbean,List basketList,List goodsList){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		ResultSet rs=null;
		int o_num=0;  //일련번호
		int trade_num=0; //주문번호
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		try {
			//1,2 디비연결
			con=getConnection();
			// o_num 일련번호구하기
			sql="select max(o_num) from g_order";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				o_num=rs.getInt(1)+1;
			}else{
				o_num=1;
			}
			trade_num=o_num;
			// for basketList goodsList
			//3 sql insert  
			//4 실행
			for(int i=0;i<basketList.size();i++){
		BasketBean basketbean=(BasketBean)basketList.get(i);
		GoodsBean goodsbean=(GoodsBean)goodsList.get(i);
		 sql="insert into g_order values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),?,now(),?)";
		 pstmt=con.prepareStatement(sql);
		 pstmt.setInt(1, o_num);
		 pstmt.setString(2, sdf.format(cal.getTime()).toString()+"-"+trade_num);//주문번호
		 pstmt.setInt(3, basketbean.getB_g_num());
		 pstmt.setString(4, goodsbean.getName());
		 pstmt.setInt(5, basketbean.getB_g_amount());
		 pstmt.setString(6, basketbean.getB_g_size());
		 pstmt.setString(7, basketbean.getB_g_color());
		 pstmt.setString(8, orderbean.getO_m_id());
		 pstmt.setString(9, orderbean.getO_receive_name());
		 pstmt.setString(10, orderbean.getO_receive_addr1());
		 pstmt.setString(11, orderbean.getO_receive_addr2());
		 pstmt.setString(12, orderbean.getO_receive_phone());
		 pstmt.setString(13, orderbean.getO_receive_mobile());
		 pstmt.setString(14, orderbean.getO_memo());
		 pstmt.setInt(15, basketbean.getB_g_amount()*goodsbean.getPrice());
		 pstmt.setString(16, orderbean.getO_trade_type());
		 pstmt.setString(17, orderbean.getO_trade_payer());
		 pstmt.setString(18, "");//운송장번호
		 pstmt.setInt(19, 0); //주문상태
		 pstmt.executeUpdate();
		 o_num++; //일련번호증가
			}//for
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
	}//
	
	//getOrderList(id)
	public List getOrderList(String id){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		ResultSet rs=null;
		List orderList=new ArrayList();
		try {
			//1,2 디비연결
			con =getConnection();
			//3 sql o_trade_num ,o_g_name,o_g_amount
			// o_g_size, o_g_color,  
			// sum(o_sum_money) as o_sum_money
			// o_trans_num , o_date, o_status
			// o_trade_type
			// g_order테이블 조건 o_m_id 만족하면 
			//  그룹 o_trade_num 정렬 o_trade_num 내림차순
			sql="select o_trade_num,o_g_name,o_g_amount,o_g_size,o_g_color,sum(o_sum_money) as o_sum_money,o_trans_num , o_date, o_status,o_trade_type from g_order where o_m_id=? group by o_trade_num order by o_trade_num desc";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			//4 rs 실행 저장 
			rs=pstmt.executeQuery();
			//5 rs데이터 있으면 OrderBean orderbean객체 생성
			// rs=>자바빈 멤버변수 =>orderList 한칸 저장
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
				orderList.add(orderbean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		return orderList;
	}
	
	//orderDetail(trade_num)
	public List orderDetail(String trade_num){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		ResultSet rs=null;
		List orderDetailList=new ArrayList();
		try {
			//1,2 디비연결
			con =getConnection();
			//3 sql 테이블 g_order 조건 o_trade_num
			sql="select * from g_order where o_trade_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, trade_num);
			//4 rs 실행저장
			rs=pstmt.executeQuery();
			//5 rs에 데이터있으면 OrderBean orderbean객체 생성
	// rs => orderbean변수 저장 => orderDetailList한칸저장
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
				orderDetailList.add(orderbean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		return orderDetailList;
	}
	
}//클래스

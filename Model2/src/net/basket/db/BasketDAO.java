package net.basket.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.admin.goods.db.GoodsBean;

public class BasketDAO {
	//디비연결
			private Connection getConnection() throws Exception{
				Connection con=null;
//				String url="jdbc:mysql://localhost:3306/jspdb";
//				String dbuser="jspid";
//				String dbpass="jsppass";
				//1단계 드라이버로더
//				Class.forName("com.mysql.jdbc.Driver");
				//2단계 디비연결
//				con=DriverManager.getConnection(url,dbuser,dbpass);
				
				//커넥션풀 : 데이터베이스와 연결된 Connection 객체를 미리 생성하여
				//풀속에 저장해 두고 필요할때마다 이 풀에 접근하여 Connection객체 사용
				Context init=new InitialContext();
				DataSource ds=(DataSource)init.lookup("java:comp/env/jdbc/Mysql");
				con=ds.getConnection();
				return con;
			}
			
			//basketAdd(basketbean)
			public void basketAdd(BasketBean basketbean){
				Connection con=null;
				PreparedStatement pstmt=null;
				ResultSet rs=null;
				String sql="";
				int b_num=0;
				try {
					//1,2 디비연결
					con=getConnection();
					// num 구하기  max(num)+1
					sql="select max(b_num) from basket";
					pstmt=con.prepareStatement(sql);
					rs=pstmt.executeQuery();
					if(rs.next()){
						b_num=rs.getInt(1)+1;
					}else{
						b_num=1;
					}
					//3 sql insert  now()
					sql="insert into basket values(?,?,?,?,?,?,now())";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, b_num);
					pstmt.setString(2, basketbean.getB_m_id());
					pstmt.setInt(3, basketbean.getB_g_num());
					pstmt.setInt(4, basketbean.getB_g_amount());
					pstmt.setString(5, basketbean.getB_g_size());
					pstmt.setString(6, basketbean.getB_g_color());
					//4 실행
					pstmt.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					if(rs!=null)try{rs.close();}catch(SQLException ex){}
					if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
					if(con!=null)try{con.close();}catch(SQLException ex){}
				}
			}
			
			//checkGoods(basketbean)
			public int checkGoods(BasketBean basketbean){
				Connection con=null;
				PreparedStatement pstmt=null;
				ResultSet rs=null;
				String sql="";
				int check=0;
				try{
					//1,2 디비연결
					con=getConnection();
					// 3 select
					// 조건 b_m_id b_g_num b_g_size b_g_color
					sql="select * from basket where b_m_id=? and b_g_num=? and b_g_size=? and b_g_color=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, basketbean.getB_m_id());
					pstmt.setInt(2, basketbean.getB_g_num());
					pstmt.setString(3, basketbean.getB_g_size());
					pstmt.setString(4, basketbean.getB_g_color());
					// 4 rs 실행저장
					rs=pstmt.executeQuery();
					// 5 rs 데이터 있으면 check=1
					//    3 sql update 
					//    조건 b_m_id b_g_num b_g_size b_g_color
					//    수정 b_g_amount
					//    4 실행
					if(rs.next()){
						check=1;
						sql="update basket set b_g_amount=b_g_amount+? where b_m_id=? and b_g_num=? and b_g_size=? and b_g_color=?";
						pstmt=con.prepareStatement(sql);
						pstmt.setInt(1, basketbean.getB_g_amount());
						pstmt.setString(2, basketbean.getB_m_id());
						pstmt.setInt(3, basketbean.getB_g_num());
						pstmt.setString(4, basketbean.getB_g_size());
						pstmt.setString(5, basketbean.getB_g_color());
						pstmt.executeUpdate();
					}
				}catch(Exception e){
					e.printStackTrace();
				}finally {
					if(rs!=null)try{rs.close();}catch(SQLException ex){}
					if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
					if(con!=null)try{con.close();}catch(SQLException ex){}
				}
				
				return check;
			}
			
			//getBasketList(id)
			public Vector getBasketList(String id){
				Connection con=null;
				PreparedStatement pstmt=null;
				ResultSet rs=null;
				PreparedStatement pstmt2=null;
				ResultSet rs2=null;
				String sql="";
				Vector vector=new Vector();
				List basketList=new ArrayList();
				List goodsList=new ArrayList();
				try {
					//1,2 디비연결
					con=getConnection();
					//3 sql id에 해당하는 장바구니 정보 가져오기
					sql="select * from basket where b_m_id=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, id);
					//4 rs 실행 저장
					rs=pstmt.executeQuery();
					//5 rs 데이터 있으면 장바구니 자바빈 객체 생성
					//  rs => 자바빈 저장 =>basketList 배열한칸 저장
					//  rs => b_g_num 
					//  3 sql b_g_num 해당하는 상품정보가져오기
					//  4 rs2  pstmt2 실행저장   
					//  5 rs2데이터 있으면 상품 자바빈 객체 생성
					//  rs2=>자바빈 저장 => goodsList한칸 저장
					while(rs.next()){
						BasketBean bb=new BasketBean();
						bb.setB_date(rs.getDate("b_date"));
						bb.setB_g_amount(rs.getInt("b_g_amount"));
						bb.setB_g_color(rs.getString("b_g_color"));
						bb.setB_g_num(rs.getInt("b_g_num"));
						bb.setB_g_size(rs.getString("b_g_size"));
						bb.setB_m_id(rs.getString("b_m_id"));
						bb.setB_num(rs.getInt("b_num"));
						basketList.add(bb);
						sql="select * from goods where num=?";
						pstmt2=con.prepareStatement(sql);
						pstmt2.setInt(1, bb.getB_g_num());
						rs2=pstmt2.executeQuery();
						if(rs2.next()){
							GoodsBean gb=new GoodsBean();
							gb.setPrice(rs2.getInt("price"));
							gb.setName(rs2.getString("name"));
							gb.setImage(rs2.getString("image"));
							goodsList.add(gb);
						}
					}
					// vector 첫번째 칸 basketList 저장
					// vector 두번째 칸 goodsList 저장
					vector.add(basketList);
					vector.add(goodsList);
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					if(rs!=null)try{rs.close();}catch(SQLException ex){}
					if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
					if(con!=null)try{con.close();}catch(SQLException ex){}
				}
				return vector;
			}
			
			
			//basketDelete(b_num);
			public void basketDelete(int b_num){
				Connection con=null;
				PreparedStatement pstmt=null;
				ResultSet rs=null;
				String sql="";
				try {
					//1,2 디비연결
					con=getConnection();
					//3 sql b_num 에 해당하는 장바구니 삭제
					sql="delete from basket where b_num=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, b_num);
					//4 실행
					pstmt.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					if(rs!=null)try{rs.close();}catch(SQLException ex){}
					if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
					if(con!=null)try{con.close();}catch(SQLException ex){}
				}
			}
			
			//deleteBasket(String id)
			public void deleteBasket(String id){
				Connection con=null;
				PreparedStatement pstmt=null;
				ResultSet rs=null;
				String sql="";
				try {
					//1,2 디비연결
					con=getConnection();
					//3 sql delete  b_m_id해당하는 장바구니 삭제
					sql="delete from basket where b_m_id=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, id);
					//4 실행
					pstmt.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					if(rs!=null)try{rs.close();}catch(SQLException ex){}
					if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
					if(con!=null)try{con.close();}catch(SQLException ex){}
				}
			}
			
}//클래스





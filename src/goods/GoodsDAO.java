package goods;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class GoodsDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	public GoodsDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 등록 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 등록 실패");
			e.printStackTrace();
		}
	}
	public Connection getConnect() {
		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "C##dbexam";
		String password = "m1234";

		try {
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("접속 성공");
		} catch (SQLException e) {
			System.out.println("접속 실패");
			e.printStackTrace();
		}
		return conn;
	}
	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertArticle(GoodsDTO dto) {
		conn=getConnect();
		int row=-1;
		String sql = "INSERT INTO goodsDB VALUES(?,?,?,?,?,?,?,sysdate)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,dto.getSection());
			pstmt.setInt(2,dto.getCode());
			pstmt.setString(3,dto.getBrand());
			pstmt.setString(4,dto.getName());
			pstmt.setInt(5,dto.getCost());
			pstmt.setDouble(6,dto.getDc());
			pstmt.setDouble(7,dto.getFinalCost());
			row = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			System.out.println(row);
			close();
		}
		if(row>0) {
			
			System.out.println("저장 성공");			
		}
	}
	
	public void deleteArticle(int code) {
		conn=getConnect();
		String sql = "delete from goodsdb where code = ? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, code);
			int row = pstmt.executeUpdate();
			if(row>0) {
				System.out.println("삭제 성공");
			}else {
				System.out.println("검색된 데이터가 없습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	public List<GoodsDTO> searcheArticle(int key,String value) {
		conn=getConnect();
		List<GoodsDTO> list = new ArrayList<>();
		
		try {
			if(key==1) {
				String sql = "select * from goodsdb";
				pstmt = conn.prepareStatement(sql);
			}else if(key==2) {
				String sql = "select * from goodsdb where code  = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,Integer.parseInt(value));
			}else if(key==3) {
				String sql = "select * from goodsdb where brand = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,value);
			}else{
				String sql = "select * from goodsdb where name = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,value);
			}
			rs = pstmt.executeQuery();
			if(rs.next()) {
				do {
					GoodsDTO dto = new GoodsDTO();
					dto.setSection(rs.getInt(1));
					dto.setCode(rs.getInt(2));
					dto.setBrand(rs.getString(3));
					dto.setName(rs.getString(4));
					dto.setCost(rs.getInt(5));
					dto.setDc(rs.getDouble(6));
					dto.setFinalCost(rs.getDouble(7));
					dto.setLogtime(rs.getString(8));
					list.add(dto);
				}while(rs.next());
			}else {
				System.out.println("검색된 데이터가 없습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}
	
	
	
	
}

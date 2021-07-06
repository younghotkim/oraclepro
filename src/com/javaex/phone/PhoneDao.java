package com.javaex.phone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneDao {

	// field

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "phonedb";
	private String pw = "phonedb";

	// constructor

	// Method GS

	/////////////// General Methods//////////////

	// Connecting DB

	private void getConnection() {

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("[접속성공]");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 -" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}
	
	private void close() {
		
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
		
	//DELETE
	
	public int personDelete(int personId) {
		
		int count = -1;
		
		this.getConnection();
	
		try {
			
			String query = "";
			query += " delete from person ";
			query += " where person_id = ?";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, personId);
			
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건이 삭제되었습니다.");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.close();
		
		return count;
		
	}
	
	//UPDATE
	
	public int personUpdate(PhoneVo phoneVo) {
		
		int count = -1;
		
		this.getConnection();
		
		try {
			
			String query = "";
			query += " update person ";
			query += " set name = ?, ";
			query += "     hp = ?, ";
			query += "     company = ? ";
			query += " where person_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, phoneVo.getName());
			pstmt.setString(2, phoneVo.getHp());
			pstmt.setString(3, phoneVo.getCompany());
			pstmt.setInt(4, phoneVo.getPersonId());
			
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건 수정");
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.close();
		
		return count;
		
	}
	
	//INSERT
	
	public int personInsert(PhoneVo phoneVo) {
		
		int count = -1;
		
		this.getConnection();
			
		try {
			
			String query = "";
			query += " insert into person ";
			query += " values(seq_person_id.nextval, ?, ?, ?) ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, phoneVo.getName());
			pstmt.setString(2, phoneVo.getHp());
			pstmt.setString(3, phoneVo.getCompany());
			
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건 등록");
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.close();
		
		return count;
	
	}
	
	//SEARCH
	
	public List<PhoneVo> personSearch(String keyword) {
		
		List<PhoneVo> personList = new ArrayList<PhoneVo>();
		
		this.getConnection();
		
		try {
			
			String query = "";
			query += " select person_id, ";
			query += "        name, ";
			query += "        hp, ";
			query += "        company ";
			query += " from person ";
			query += " where name like ? ";
			query += " or hp like ? ";
			query += " or company like ? ";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");
			pstmt.setString(3, "%" + keyword + "%");
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");
				
				PhoneVo phoneVo = new PhoneVo(personId, name, hp, company);
							
				personList.add(phoneVo);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.close();
		
		return personList;
		
	
		
	}

	//LIST
	
	public List<PhoneVo> getPersonList() {
		
		List<PhoneVo> personList = new ArrayList<PhoneVo>();
		
		this.getConnection();
		
		try {
			
			String query = "";
			query += " select person_id, ";
			query += "        name, ";
			query += "        hp, ";
			query += "        company ";
			query += " from person ";
			
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");
				
				PhoneVo phoneVo = new PhoneVo(personId, name, hp, company);
							
				personList.add(phoneVo);
	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.close();
		
		return personList;
		
	}

		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

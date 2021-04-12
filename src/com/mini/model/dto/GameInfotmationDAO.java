package com.mini.model.dto;

import static com.mini.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GameInfotmationDAO {

	private Properties prop = new Properties();

	public GameInfotmationDAO() {

		try {
			prop.loadFromXML(new FileInputStream("mapper/name-query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	 DB의 data에 접근하기 위한 객체 // 이름을 넣기위함.
	public int saveUser(Connection con, String userName) {

		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("insertuser");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userName);
			pstmt.setInt(2, 0);
			pstmt.setInt(3, 50);
			result = pstmt.executeUpdate();
			

		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
	
	// 이름을 갖고오기위함.
	public String selecetUser(Connection con) {

//		Statement // 해당 객체가 조건이 들어가면 인젝션 공격(?) 값이 그대로 표현하고조작이 가능 
//      -> pre는 위치홀더를넣고 필요한값을넣어줘서사용

		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rset = null;
		ResultSet selectRset = null;
		String selectIdResult = null;
		String result = null;

		// 사용자 추가 시 시퀀스 번호 조회용 쿼리
		String selectUserIdQuery = prop.getProperty("selectUserId");
		// 조회한 시퀀스 번호를 이용해서 사용자 조회
		String selectUserquery = prop.getProperty("selectuser");
		
		try {

			pstmt = con.prepareStatement(selectUserIdQuery);
			selectRset = pstmt.executeQuery();

			if (selectRset.next()) {
				selectIdResult = selectRset.getString("CURRVAL");
			}
			
			if(!selectIdResult.isEmpty()) {
				pstmt1 = con.prepareStatement(selectUserquery);
				pstmt1.setInt(1, Integer.parseInt(selectIdResult));
				rset = pstmt1.executeQuery();
				
				if(rset.next()) {
					result = rset.getString("USERNAME");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt1);
			close(pstmt);
		}

		return result;
	}

	public List<GameInfotmationDTO> selectAllUser(Connection con) {
		

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		List<GameInfotmationDTO> selectalluser = null;
		
		String query = prop.getProperty("selecealluser");
		
		try {
			pstmt = con.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			selectalluser = new ArrayList<>();
			
			while(rset.next()) {
				GameInfotmationDTO gameinformationdto = new GameInfotmationDTO();
				
				gameinformationdto.setUsername(rset.getString("USERNAME"));
				gameinformationdto.setPlaydate(rset.getDate("PLAYDATE"));
				gameinformationdto.setHeart(rset.getInt("HEART"));
				gameinformationdto.setNumberid(rset.getInt("NUMBER_ID"));
				
				selectalluser.add(gameinformationdto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
	
		
		return selectalluser;
	}

	public int savaStage(Connection con, String name, int stage, int lovePoint) {
	
		PreparedStatement pstmt = null;
		int result = 0;
		
		Properties prop = new Properties();
		
		String query = prop.getProperty("updateuser");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, stage);
			pstmt.setInt(2, lovePoint);
			pstmt.setString(3,name);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);

		}
		
		
		return result;
	}


}

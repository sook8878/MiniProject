package com.mini.service;

import java.sql.Connection;
import java.util.List;

import javax.accessibility.AccessibleContext;

import static com.mini.common.JDBCTemplate.getConnection;
import com.mini.model.dto.GameInfotmationDAO;
import com.mini.model.dto.GameInfotmationDTO;

import static com.mini.common.JDBCTemplate.commit;
import static com.mini.common.JDBCTemplate.rollback;
import static com.mini.common.JDBCTemplate.close;

public class UserService {
	
	// dao로 넘길려고 만듦.
	private GameInfotmationDAO gameinformationdao = new GameInfotmationDAO();

	// user를 insert하고 , select하려고 만듦. 
	public String saveUser(String userName) {

		String selectResult = null;
		Connection con = getConnection();

		// 1 번 db로 회원정보 저장 dao로 넘긴다.
		int insertResult = gameinformationdao.saveUser(con, userName);

		// insertResult가 null이 아니면 dao에 넘긴다.
		if (insertResult > 0) {
			selectResult = gameinformationdao.selecetUser(con);
		}
		
		if(insertResult > 0 && selectResult != null) {
			commit(con);
		} else {
			rollback(con);
		}
		
		
		close(con);
		
		return selectResult;
	}


	public List<GameInfotmationDTO> rankAllUser() {
		
		Connection con = getConnection();
		
		
		List<GameInfotmationDTO> selectalluser = gameinformationdao.selectAllUser(con);
		
		close(con);
		
		return selectalluser;
	}


	public int savaStage(String name, int stage, int lovePoint) {
		
		Connection con = getConnection();
		
		int savaStageResult = gameinformationdao.savaStage(con,name,stage,lovePoint);
		
		if(savaStageResult > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return savaStageResult;
		
	}
}

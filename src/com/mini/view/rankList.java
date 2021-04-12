package com.mini.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mini.controller.UserController;
import com.mini.model.dto.GameInfotmationDTO;

public class rankList extends JFrame {
	
//	String [] columns = {"번호", "닉네임", "호감도", "날짜"};
//	String [][] data = {{"1", "user01", "10", "21/04/07"},
//			{"2", "user02", "20", "21/04/09"},
//			{"1", "user01", "10", "21/04/07"},
//			{"1", "user01", "10", "21/04/07"},
//			{"1", "user01", "10", "21/04/07"},
//			{"1", "user01", "10", "21/04/07"},
//			{"1", "user01", "10", "21/04/07"},
//			{"1", "user01", "10", "21/04/07"},
//			{"1", "user01", "10", "21/04/07"},
//			{"1", "user01", "10", "21/04/07"}};
	
	String [] columns = {"번호", "닉네임", "호감도", "날짜"};
	String [][] data = {{"1", "user01", "10", "21/04/07"},
			{"2", "user02", "20", "21/04/09"}};

	public rankList() {

		// 프레임 설정
		this.setSize(1000, 680);
		this.setTitle("시뮬레이션");
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		// 배경값 설정을 위한 컬러 생성
		Color b = new Color(255, 225, 235);

		// Panel 설정
		JPanel pan = new JPanel();
		pan.setBounds(10, 10, 965, 620);
		pan.setLayout(null);
		pan.setBackground(b);
		
		// 상단 홈 버튼
		Image homeImg = new ImageIcon("image/home.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		JLabel home = new JLabel(new ImageIcon(homeImg));

		// 상단 홈 버튼 크기, 위치 조정
		home.setBounds(10, 0, 50, 50);

		// 상단 오른쪽 프로그램 종료 버튼
		Image closeImg = new ImageIcon("image/close.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		JLabel closewords = new JLabel(new ImageIcon(closeImg));

		// 상단 종료 버튼 크기, 위치 조정
		closewords.setBounds(900, 0, 50, 50);
		
		// 상단 제목 테두리
		Image rankTitleGroundImg = new ImageIcon("image/bottom.png").getImage();
		rankTitleGroundImg.getScaledInstance(10, 10, Image.SCALE_SMOOTH);
		JLabel rankTitleGround = new JLabel(new ImageIcon(rankTitleGroundImg));

		// 상단 제목 테두리 크기, 위치 조정
		rankTitleGround.setBounds(230, 60, 500, 80);
		
		// 상단 제목
		JLabel rankTitle = new JLabel("★RANK★");
		
		// 제목의 크기, 위치, 폰트 지정
		rankTitle.setBounds(145, 10, 220, 50);
		rankTitle.setFont(new Font("배달의민족 주아", Font.PLAIN, 50));
		
		
		// 랭킹 allselect  하기 위함. // 주영이가 추가함 랭킹순위를 불러오기위함.
	      
	      List<GameInfotmationDTO> receviedUser = new UserController().rankAllUser();
	      String [][] receviedData = new String [receviedUser.size()][4];
	      for (int i =0; i<receviedUser.size(); i++) {
	    	  receviedData[i][0] = String.valueOf(i+1);
	    	  receviedData[i][1] = String.valueOf(receviedUser.get(i).getUsername());
	    	  receviedData[i][2] = String.valueOf(receviedUser.get(i).getHeart());
	    	  receviedData[i][3] = String.valueOf(receviedUser.get(i).getPlaydate());
	      }
	
		
		
		// 랭킹의 출력 부분
		DefaultTableModel model = new DefaultTableModel(receviedData, columns);
		JTable table = new JTable(model);
		table.setRowHeight(40);
		
		// 랭킹의 data의 배경색, 폰트 설정
		table.setBackground(new Color(255, 255, 240));
		table.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
		
		// 랭킹의 스크롤 부분
		JScrollPane scroll = new JScrollPane(table);
		
		// 스크롤이 들어간 랭킹의 위치,크기 설정
		scroll.setBounds(230, 160, 500, 400);
		scroll.setPreferredSize(new Dimension(500, 500));

		// 제목 테두리에 제목 추가
		rankTitleGround.add(rankTitle);
		
		// panel에 추가
		pan.add(home);
		pan.add(closewords);
		pan.add(rankTitleGround);
		pan.add(scroll);

		// 프레임 판넬 추가
		this.add(pan, "Center");

		// 닫기 버튼 이벤트 -> 클릭하면 경고 창 출력
		closewords.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new warning();
			}
		});
		
		// 홈 버튼 이벤트 -> 화면 전환
		home.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rankList.this.dispose();
				new FristMain();
			}
		});

		// 프레임 설정
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

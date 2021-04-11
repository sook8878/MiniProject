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

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class chap01Talk4 extends JFrame {
	
	private int stage = 0;
	private int lovePoint = 50;
	
	public chap01Talk4() {

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

		// 상단에 사람 이미지
		Image personImg = new ImageIcon("image/김선호.png").getImage();
		personImg.getScaledInstance(10, 10, Image.SCALE_SMOOTH);
		JLabel person = new JLabel(new ImageIcon(personImg));

		// 상단의 이미지 크기, 위치 조정
		person.setBounds(5, 20, 1000, 300);

		String img = "";

		if (lovePoint >= 80) {
			
			img = "image/loveBar3.png";

		} else if (lovePoint >= 50) {

			img = "image/loveBar2.png";
		} else {
			
			img = "image/loveBar1.png";
		}
		
		// 상단 호감도 표시
		Image loveBarImg = new ImageIcon(img).getImage();
		loveBarImg.getScaledInstance(130, 330, Image.SCALE_SMOOTH);
		JLabel loveBar = new JLabel(new ImageIcon(loveBarImg));

		// 상단 호감도 표시 크기, 위치 조정
		loveBar.setBounds(10, 1, 130, 330);

		// 상단 오른쪽 프로그램 종료 버튼
		Image closeImg = new ImageIcon("image/close.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		JLabel closewords = new JLabel(new ImageIcon(closeImg));

		// 상단 종료 버튼 크기, 위치 조정
		closewords.setBounds(900, 0, 50, 50);

		// 하단에 대화 테두리
		Image talkBackGroundImg = new ImageIcon("image/bottom.png").getImage();
		talkBackGroundImg.getScaledInstance(10, 10, Image.SCALE_SMOOTH);
		JLabel talkBackGround = new JLabel(new ImageIcon(talkBackGroundImg));

		// 하단에 대화 테두리의 크기, 위치 조정
		talkBackGround.setBounds(20, 340, 920, 270);

		// 하단 대화 내용
		JLabel talk = new JLabel("<html>직원 : 저희 카페에서 음료 이벤트 진행중인데 게임 참여해 보시겠어요?</html>");
		talk.setBounds(50, -50, 1000, 350);
		talk.setFont(new Font("배달의민족 주아", Font.PLAIN, 30));

		// 하단 대화 테두리의 대화라벨 추가
		talkBackGround.add(talk);

		// 하단 다음 이미지
		Image nextImg = new ImageIcon("image/next.png").getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
		JLabel next = new JLabel(new ImageIcon(nextImg));

		// 하단 다음 이미지의 크기, 위치 조정
		next.setBounds(750, 150, 150, 80);

		// panel에 추가
		pan.add(person);
		pan.add(loveBar);
		pan.add(closewords);
		pan.add(talkBackGround);

		// 하단 대화 테두리에 다음 이미지 추가
		talkBackGround.add(next);

		// 프레임 판넬 추가
		this.add(pan, "Center");

		// 닫기 버튼 이벤트 -> 클릭하면 경고 창 출력
		closewords.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new warning();
			}
		});
		
		// 다음 버튼 이벤트 -> 화면 전환
		next.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chap01Talk4.this.dispose();
				new chap01Choice1();
			}
		});

		// 프레임 설정
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

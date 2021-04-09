package com.mini.view;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FristMain extends JFrame {

	// 메인화면
	public FristMain() {

		this.setSize(1000, 680);
		this.setTitle("시뮬레이션");
		// 배치관리자 설정 안함
		this.setLayout(null);
		this.setResizable(false);
		// setloaction안해도 모니터 중간에 오도록 하는 것 
		this.setLocationRelativeTo(null);

		JPanel pan = new JPanel();
		pan.setBounds(10, 10, 965, 620);
		pan.setLayout(null);
		Color b = new Color(255, 225, 235);
		pan.setBackground(b);

		// 남자 주인공
		Image sunhoImg = new ImageIcon("image/sunho1.jpg").getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
		JLabel sunhoWords = new JLabel(new ImageIcon(sunhoImg));
		
		sunhoWords.setSize(400, 500);
		sunhoWords.setLocation(550, 170);
		
		// 제목 화면
		Image titleImg = new ImageIcon("image/제목.png").getImage().getScaledInstance(400, 100, Image.SCALE_SMOOTH);
		JLabel titleWords = new JLabel(new ImageIcon(titleImg));
		
		titleWords.setBounds(100, 100, 400, 100);
		titleWords.setLocation(250, 0);
		
		// 준비하기
		Image startImg = new ImageIcon("image/준비하기.png").getImage().getScaledInstance(300, 100, Image.SCALE_SMOOTH);
		JLabel startWords = new JLabel(new ImageIcon(startImg));
		
		startWords.setSize(300, 300);
		startWords.setLocation(80, 170);
		
		// 다음 프레임으로 넘어가기 
		startWords.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				setVisible(false);
				new SecondMain();
			}
		
		});
		
		// 이어하기
		Image secondImg = new ImageIcon("image/이어하기.png").getImage().getScaledInstance(300, 100, Image.SCALE_SMOOTH);
		JLabel seondWords = new JLabel(new ImageIcon(secondImg));
		seondWords.setSize(300, 300);
		seondWords.setLocation(80, 280);

		// 순위보기
		Image rankImg = new ImageIcon("image/순위보기.png").getImage().getScaledInstance(300, 100, Image.SCALE_SMOOTH);
		JLabel rankWords = new JLabel(new ImageIcon(rankImg));
		rankWords.setSize(300, 300);
		rankWords.setLocation(80, 390);

		// 닫기
		Image closeImg = new ImageIcon("image/close.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		JLabel closewords = new JLabel(new ImageIcon(closeImg));

		// 닫기 버튼 이벤트 -> 클릭하면 경고 창 출력
		closewords.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new warning();
			}
		});
		
		closewords.setSize(50, 50);
		closewords.setLocation(900, 0);

		pan.add(closewords);
		pan.add(sunhoWords);
		pan.add(titleWords);
		pan.add(startWords);
		pan.add(seondWords);
		pan.add(rankWords);

		this.add(pan, "Center");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}

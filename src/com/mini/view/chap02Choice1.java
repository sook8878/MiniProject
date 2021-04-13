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

import gamePlay.NumberGame;

public class chap02Choice1 extends JFrame {

	public chap02Choice1() {

	}

	public chap02Choice1(String name, int stage, int lovePoint) {

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
		Image personImg = new ImageIcon("image/나레이션1.png").getImage();
		personImg.getScaledInstance(780, 300, Image.SCALE_SMOOTH);
		JLabel person = new JLabel(new ImageIcon(personImg));

		// 상단의 이미지 크기, 위치 조정
		person.setBounds(87, 20, 780, 300);

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
		loveBar.setBounds(895, 50, 60, 150);

		// 상단 오른쪽 프로그램 종료 버튼
		Image closeImg = new ImageIcon("image/close.png").getImage().getScaledInstance(50, 50, 0);
		JLabel closewords = new JLabel(new ImageIcon(closeImg));

		// 상단 종료 버튼 크기, 위치 조정
		closewords.setBounds(900, 0, 50, 50);

		// 하단에 선택지 테두리
		Image talkBackGroundImg = new ImageIcon("image/bottom.png").getImage();
		talkBackGroundImg.getScaledInstance(10, 10, Image.SCALE_SMOOTH);
		JLabel talkBackGround = new JLabel(new ImageIcon(talkBackGroundImg));

		// 하단에 대화 테두리의 크기, 위치 조정
		talkBackGround.setBounds(20, 335, 920, 270);

		// 매개변수로 전달받은 스테이지 번호를 넘겨주기 위한 작업
		String stageNum = String.valueOf(stage);
		JLabel test = new JLabel(stageNum);
		test.setVisible(false);
		
		// 매개변수로 전달받은 호감도 넘겨주기 위한 작업
		String lovePointNum = String.valueOf(lovePoint);
		JLabel lovePointLabel = new JLabel(lovePointNum);
		lovePointLabel.setVisible(false);

		// 선택지 버튼
		JButton choice1_btn = new JButton();
		JButton choice2_btn = new JButton();

		// 선택지 버튼의 라벨
		JLabel choiec1_lb = new JLabel("<html>(벌떡 일어나서) 부장님!<br><br>신입사원인 제가 한잔 부장님 마지막 술 받아도 되겠습니까!?<br><br>저랑 한잔 하시죠!!!</html>");
		JLabel choiec2_lb = new JLabel("<html>(나도 이미 많이 마셔서..)<br><br>선호를 안쓰럽게 쳐다보고 부장님이랑 눈을 마주치지 않는다.</html>");

		// 선택지 버튼의 크기, 위치 조정
		choice1_btn.setBounds(50, 50, 380, 180);
		choice2_btn.setBounds(480, 50, 380, 180);

		// 선택지 버튼의 배경 색
		choice1_btn.setBackground(new Color(255, 255, 240));
		choice2_btn.setBackground(new Color(255, 255, 240));

		// 선택지 버튼에 대한 이벤트 -> 마우스에 대한 색깔 변경 이벤트 & 창 닫기
		choice1_btn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				choice1_btn.setBackground(Color.pink);
			}

			public void mouseExited(MouseEvent e) {
				choice1_btn.setBackground(new Color(255, 255, 240));
			}

			public void mouseClicked(MouseEvent e) {
				chap02Choice1.this.dispose();
				
				String num = test.getText();
				int stage2 = Integer.parseInt(num);
				stage2++;
				
				String lovePointnum = lovePointLabel.getText();
				int lovePoint = Integer.parseInt(lovePointnum);
				lovePoint += 10; //호감도 증가하는 값 일단 임의로 그냥 넣음 나중에 수정해야함
				lovePoint = lovePoint + 10;
				// 현우오빠 게임 부르기
				new NumberGame(name, stage2, lovePoint);
			}

		});

		choice2_btn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				choice2_btn.setBackground(Color.pink);
			}

			public void mouseExited(MouseEvent e) {
				choice2_btn.setBackground(new Color(255, 255, 240));
			}

			public void mouseClicked(MouseEvent e) {
				chap02Choice1.this.dispose();
				
				String num = test.getText();
				int stage2 = Integer.parseInt(num);
				stage2++;
				
				String lovePointnum = lovePointLabel.getText();
				int lovePoint = Integer.parseInt(lovePointnum);
				lovePoint -= 20; //호감도 증가하는 값 일단 임의로 그냥 넣음 나중에 수정해야함
				System.out.println(lovePoint);
				
				new chap03Narration1(name, stage2, lovePoint);
			}

		});

		// 선택지 버튼의 라벨에 폰트 적용
		choiec1_lb.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
		choiec2_lb.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));

		// 버튼에 라벨 추가
		choice1_btn.add(choiec1_lb);
		choice2_btn.add(choiec2_lb);

		// 하단 선택지 테두리에 버튼 추가
		talkBackGround.add(choice1_btn);
		talkBackGround.add(choice2_btn);

		// panel에 추가
		pan.add(person);
		pan.add(loveBar);
		pan.add(closewords);
		pan.add(talkBackGround);
		pan.add(test);
		pan.add(lovePointLabel);

		// 프레임 판넬 추가
		this.add(pan, "Center");

		// 닫기 버튼 이벤트 -> 클릭하면 경고 창 출력
		closewords.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String num = test.getText();
				int stage1 = Integer.parseInt(num);
				
				new warning(name, stage1, lovePoint);
			}
		});

		// 프레임 설정
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}

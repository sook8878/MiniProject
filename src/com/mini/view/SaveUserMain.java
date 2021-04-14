
package com.mini.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mini.controller.UserController;
import com.mini.model.dto.GameInfotmationDTO;

public class SaveUserMain extends JFrame {

	private int stage = 0;
	private int lovePoint = 50;
	private String name = "";

	// 주영이가 추가함. 컨트롤러 넘기기 위함.
	private UserController usercontrller = new UserController();

	// 두번째 화면
	public SaveUserMain() {

		this.setSize(1000, 680);
		this.setTitle("시뮬레이션");
		// 배치관리자 설정 안함
		this.setLayout(null);
		// 창 고정
		this.setResizable(false);
		// setloaction안해도 모니터 중간에 오도록 하는 것
		this.setLocationRelativeTo(null);

		// 패널
		JPanel pan = new JPanel();
		pan.setBounds(10, 10, 965, 620);
		pan.setLayout(null);
		Color b = new Color(255, 225, 235);
		pan.setBackground(b);

		// 이름 글씨
		JLabel lb = new JLabel("이 름 : ");
		lb.setLocation(270, 420);
		lb.setSize(200, 50);
		lb.setFont(new Font("배달의민족 주아", Font.PLAIN,20));

		// 이름 적는 필드
		JTextField tf = new JTextField(20);
		tf.setLocation(335, 420);
		tf.setSize(300, 50);
		tf.setFont(new Font("배달의민족 주아", Font.PLAIN,20));
		
		// 이름 입력해주세요 필드 
		JLabel text = new JLabel("기존의 이름을 입력해주세요");
		text.setLocation(375, 360);
		text.setSize(300,300);
		text.setFont(new Font("배달의민족 주아", Font.PLAIN,25));
		
		// 버튼 확인
		JButton btn = new JButton("확 인");
		btn.setLocation(660, 420);
		btn.setSize(100, 50);
		btn.setFont(new Font("배달의민족 주아", Font.PLAIN,20));
		btn.setBackground(Color.pink);

		// 확인 버튼 누르면 -> chap1 나레이션으로 넘어감.
		btn.addMouseListener(new MouseAdapter() {

			// 이름 입력하지 않으면 안넘어감 , 입력하면 다음 화면으로 넘어감.
			@SuppressWarnings("unused")
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (tf.getText().length() != 0) {
					// 존재하냐 ? 묻는 것. 처음은 false 
					boolean isExistenceUser = false;
					// rankAllUser() 에서 이름 찾기 
					List<GameInfotmationDTO> rankUsers = new UserController().rankAllUser();
					for (int i = 0; i < rankUsers.size(); i++) {
						// 동일한 이름 찾기.
						// list에 있는걸 한 개씩 빼오는 것 = rankUsers.get(0번째) for문 
						GameInfotmationDTO gameInfotmationDTO = rankUsers.get(i);
						if (tf.getText().equals(gameInfotmationDTO.getUsername())) {
							// 동일한 이름이 있다 ? 존재하냐 ? 존재한다. 그럼 갖고있던 stage 불러오기.
							stage = gameInfotmationDTO.getStage();
							isExistenceUser = true;
						}
					}
					// 존재할 시 실행됨. 이어하기로 넘어간다. 
					if (isExistenceUser) {
						setVisible(false);

						name = tf.getText();

						switch (stage) {
						case 0:
						case 1:
							new chap01Narration1(name, stage, lovePoint);
							break;
						case 2:
							new chap01Talk1(name, stage, lovePoint);
							break;
						case 3:
							new chap01Talk2(name, stage, lovePoint);
							break;
						case 4:
							new chap01Talk3(name, stage, lovePoint);
							break;
						case 5:
							new chap01Talk4(name, stage, lovePoint);
							break;
						case 6:
							new chap01Choice1(name, stage, lovePoint);
							break;
						case 7:
							new chap02Narration1(name, stage, lovePoint);
							break;
						case 8:
							new chap02Talk1(name, stage, lovePoint);
							break;
						case 9:
							new chap02Talk2(name, stage, lovePoint);
							break;
						case 10:
							new chap02Talk3(name, stage, lovePoint);
							break;
						case 11:
							new chap02Choice1(name, stage, lovePoint);
							break;
						case 12:
							new chap03Narration1(name, stage, lovePoint);
							break;
						case 13:
							new chap03Narration2(name, stage, lovePoint);
							break;
						case 14:
							new chap03Talk1(name, stage, lovePoint);
							break;
						case 15:
							new chap03Talk2(name, stage, lovePoint);
							break;
						case 16:
							new chap03Talk3(name, stage, lovePoint);
							break;
						case 17:
							new chap03Talk4(name, stage, lovePoint);
							break;
						case 18:
							new chap03Talk5(name, stage, lovePoint);
							break;
						case 19:
							new chap03Talk6(name, stage, lovePoint);
							break;
						case 20:
							new chap03Talk7(name, stage, lovePoint);
							break;
						case 21:
							new chap03Talk8(name, stage, lovePoint);
							break;
						case 22:
							new chap04Narration1(name, stage, lovePoint);
							break;
						case 23:
							new chap04Narration2(name, stage, lovePoint);
							break;
						case 24:
							new chap04Talk1(name, stage, lovePoint);
							break;
						case 25:
							new chap04Talk2(name, stage, lovePoint);
							break;
						case 26:
							new chap04Talk3(name, stage, lovePoint);
							break;
						case 27:
							new chap04Talk4(name, stage, lovePoint);
							break;
						case 28:
							new chap04Talk5(name, stage, lovePoint);
							break;

						}
					} else {
						// 존재하지 않을 시 경고창.
						JOptionPane.showMessageDialog(null, "존재하지 않는 이름입니다.");
					}

				} else {
					JOptionPane.showMessageDialog(null, "이름을 입력하세요");
				}

			}

		});

		// 남자 주인공 사진
		Image sunhoImg = new ImageIcon("image/선호꽃2.png").getImage().getScaledInstance(783, 680, 0);
		JLabel sunhowords = new JLabel(new ImageIcon(sunhoImg));
		sunhowords.setLocation(90, 0);
		sunhowords.setSize(783, 680);

		// 닫기 버튼
		Image closeImg = new ImageIcon("image/close.png").getImage().getScaledInstance(50, 50, 0);
		JLabel closewords = new JLabel(new ImageIcon(closeImg));
		closewords.setSize(50, 50);
		closewords.setLocation(900, 0);

		// 닫기 버튼 이벤트 -> 클릭하면 경고 창 출력
		closewords.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new warning(name, stage, lovePoint);
			}
		});

		pan.add(text);
		pan.add(tf);
		pan.add(lb);
		pan.add(btn);
		pan.add(sunhowords);
		pan.add(closewords);

		this.add(pan, "Center");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}


package com.mini.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

public class SecondMain extends JFrame {

	private int stage = 0;
	private int lovePoint = 50;
	private String name = "";

	// 주영이가 추가함. 컨트롤러 넘기기 위함.
	private UserController usercontrller = new UserController();

	// 두번째 화면
	public SecondMain() {

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
		lb.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));

		// 이름 적는 필드
		JTextField tf = new JTextField(20);
		tf.setLocation(335, 420);
		tf.setSize(300, 50);
		tf.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));

		// 이름 입력하고 엔터로 다음 클래스 호출하는 기능
		tf.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			// 이름 입력하지 않으면 안넘어감 , 입력하면 다음 화면으로 넘어감.
			@SuppressWarnings("unused")
			@Override
			public void keyPressed(KeyEvent e) {

				if (tf.getText().length() != 0) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						// super.keyPressed(e);
						new UserController().saveUser(tf.getText());
						new chap01Narration1(tf.getText(), stage, lovePoint);
						setVisible(false);
					}

				} else {
					JOptionPane.showMessageDialog(null, "이름을 입력하세요");
				}
			}
		});

		// 이름 입력해주세요 필드
		JLabel text = new JLabel("이름을 입력해주세요");
		text.setLocation(400, 410);
		text.setSize(200, 200);
		text.setFont(new Font("배달의민족 주아", Font.PLAIN, 25));

		// 버튼 확인
		JButton btn = new JButton("확 인");
		btn.setLocation(660, 420);
		btn.setSize(100, 50);
		btn.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
		btn.setBackground(Color.pink);

		// 확인 버튼 누르면 -> chap1 나레이션으로 넘어감.
		btn.addMouseListener(new MouseAdapter() {

			// 이름 입력하지 않으면 안넘어감 , 입력하면 다음 화면으로 넘어감.
			@SuppressWarnings("unused")
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tf.getText().length() != 0) {
					super.mouseClicked(e);
					new UserController().saveUser(tf.getText());
					new chap01Narration1(tf.getText(), stage, lovePoint);
					setVisible(false);

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

package gamePlay;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.mini.view.chap03Narration1;
import com.mini.view.warning;

public class NumberGame extends JFrame {
	
	JButton button = new JButton(); // 게임 패널의 버튼 변수
	
	int time = 30; // Timer 변수 선언 및 초기화
	int startNum = 1; // 버튼 숫자 대조변수 선언 및 초기화

	
	public NumberGame(String name, int stage, int lovePoint) {
		
	    /* 컬러 생성 */
	    Color pink = new Color(255, 225, 235); // 팀 배경 컬러
	    Color red2 = new Color(223, 111, 141); // 버튼 컬러
	    
		/* 시작 팝업 */
	    JLabel gameInfo = new JLabel("<HTML><body>숫자 맞추기 게임 <br><br> 제한 시간 30초 내에 임의로 배치되는 1부터 25까지 숫자를 차례대로 누르세요. <br> 1번이라도 잘못된 번호를 누를 시 게임 실패입니다.");
	    gameInfo.setFont(new Font("배달의민족 주아", Font.PLAIN, 15));
	    
	    JOptionPane.showMessageDialog(null, gameInfo);
	    
		/* 팀 메인 프레임 설정*/
	    this.setBounds(0, 0, 1000, 680);
	    this.setTitle("숫자 게임");
	    this.setLayout(null);
	    this.setResizable(false);
	    this.setLocationRelativeTo(null);
	    
	    /* 팀 배경 패널 */
	    JPanel pan = new JPanel();
	    pan.setBounds(10, 10, 965, 620);
	    pan.setLayout(null);
	    pan.setBackground(pink); // 핑크 지정
	    
	    
	    /* Title 라벨 */
	    JLabel titleLable = new JLabel("숫자 맞추기 게임");
	    titleLable.setBounds(355, 20, 350, 50);
	    //titleLable.setOpaque(false); // true시 타이틀 배경색 지정 가능
	    titleLable.setBackground(pink);
	    titleLable.setFont(new Font("배달의민족 주아", Font.BOLD, 35)); // bold, plain으로 두께 조절
	    pan.add(titleLable, "North");
	    
		// 매개변수로 전달받은 스테이지 번호를 넘겨주기 위한 작업
		String stageNum = String.valueOf(stage);
		JLabel test = new JLabel(stageNum);
		test.setVisible(false);
		//pan.add(test);
	 	
		// 매개변수로 전달받은 호감도 넘겨주기 위한 작업
		String lovePointNum = String.valueOf(lovePoint);
		JLabel lovePointLabel = new JLabel(lovePointNum);
		lovePointLabel.setVisible(false);		
	 	//pan.add(lovePointLabel);
		
	    /* 타이머 */
	    // 타이머 라벨 및 이미지 추가 
	    Image timerImage = new ImageIcon("image/Timer.png").getImage();
	    //Image timerImage = new ImageIcon(NumGame.class.getResource("image/Timer.png")).getImage();
	    timerImage.getScaledInstance(10, 10, Image.SCALE_DEFAULT);
	    
	    JLabel timerLabel = new JLabel(new ImageIcon(timerImage));
	    timerLabel.setSize(100, 100);
	    timerLabel.setLocation(20, 15);
	    pan.add(timerLabel);
	    
	    // 타이머 글씨 라벨(초)
	    JLabel timerLtLabel = new JLabel();
	    timerLtLabel.setBounds(110, 33, 70, 70);
	    timerLtLabel.setFont(new Font("배달의민족 주아", Font.PLAIN, 28));
	    timerLtLabel.setForeground(Color.black);;
	    
	    timerLtLabel.setText(time + " 초");
	    pan.add(timerLtLabel);
	    
	    // 타이머 기능
	    Timer timer = new Timer();
	    TimerTask timerTask = new TimerTask() {

			@Override
			public void run() {
				
				if (time <= 30) { // 20초(시작 값) 이하 1초씩 count-down
					
					time--;
					timerLtLabel.setText(time + " 초");
					
					/* 게임 실패 시 (시간 초과) */
					if (time == 0) { // 시간 초과
						
						timerLtLabel.setText(time + " 초");
						timer.cancel(); // 타이머 취소
					    
						JLabel timeOut = new JLabel("<HTML><body> 게임 실패! <br><br>시간이 초과되었습니다.");
					    timeOut.setFont(new Font("배달의민족 주아", Font.PLAIN, 16));
					    
		                // stage 값은 변경하지 않는다.
//		                String num = test.getText();
//		                int stage2 = Integer.parseInt(num);
//		                stage2++;
		    				
		                // 호감도 20 감소
		                String lovePointnum = lovePointLabel.getText();
		                int lovePoint = Integer.parseInt(lovePointnum);
		                lovePoint -= 20;
					    
					    JOptionPane.showMessageDialog(null, timeOut);
					    dispose();

	                    /* 다음 스테이지 호출 */
	                    new chap03Narration1(name, stage, lovePoint);					    
					}
				} 
			}
	    };
	    
	    // 타이머에 task 추가
	    timer.schedule(timerTask, 0, 1000); // 매 1초 마다 동작    
	    
	    
	    /* 게임 패널 */
	    JPanel gamePan = new JPanel();
	    gamePan.setBounds(242, 105, 510, 510);
	    gamePan.setLayout(new GridLayout(5, 5, 3, 3));
	    gamePan.setVisible(true);
	    
	    /* 게임 패널 및 이미지 패널 */
	    ImageIcon sunho = new ImageIcon("image/kshcutypie.jpg");
	    JPanel gameBackground = new JPanel() {
	    	public void paintComponent(Graphics g) {
	    		
	    		g.drawImage(sunho.getImage(), 0, 0, null);
	    		setOpaque(false);
	    		super.paintComponents(g);
	    	}
	    };
	    gameBackground.setBounds(242, 105, 510, 510);
	    gameBackground.setVisible(true);
	    //gamePan.add(gameBackground);
	    
		/* 숫자를 Set에 담기 */
		Set set = new LinkedHashSet();
		
		while(set.size() < 25) {
			
			set.add(new Random().nextInt(25) + 1);
		}
		
		/* Object에 숫자를 담아서 버튼에 담기 */
		Object[] obj = set.toArray();
		
		for(int i = 0; i < obj.length; i++) {
			
			String strNum = new Integer((int) obj[i]).toString();
			
	    	button = new JButton(strNum);
	    	button.setBackground(red2); // 버튼 컬러
	    	button.setBorderPainted(false); // false = 테두리 없음
	    	button.setFont(new Font("배달의민족 주아", Font.BOLD, 24));
	    	
	    	button.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
		            
					System.out.println(e);
					
		            JButton jbutton = (JButton)e.getSource();
		            System.out.println(jbutton.getText());
		            int checkNum = Integer.parseInt(jbutton.getText());
		            if(checkNum == startNum) {
		                  
		                startNum++;
		                jbutton.setVisible(false);
		                
		                /* 게임 성공 시 */
		                if(checkNum == 25) {
		                    
		                	timer.cancel(); // 성공 시 타이머 종료
		                	gamePan.setVisible(false); // 게임 패널 invisible 처리
		                	
		                    JLabel success = new JLabel("<HTML><body> 게임 성공! <br><br> 숨겨진 선호씨 사진 발견!");
		                    success.setFont(new Font("배달의민족 주아", Font.PLAIN, 16));
		                    JOptionPane.showMessageDialog(null, success);
		                    
		                    // stage 값은 변경하지 않는다.
//		    				String num = test.getText();
//		    				int stage2 = Integer.parseInt(num);
//		    				stage2++;
		    				
		    				// 호감도 10 증가
		    				String lovePointnum = lovePointLabel.getText();
		    				int lovePoint = Integer.parseInt(lovePointnum);
		    				lovePoint += 10;
		                    
		                    System.out.println("종료");
		                    dispose();
		                    
		                    /* 다음 스테이지 호출 */
		                    new chap03Narration1(name, stage, lovePoint);
		                  }
		                  
		               } else {
		            	   /* 게임 실패 시(잘못된 버튼 선택) */
		                  JLabel wrongNumber = new JLabel("<HTML><body> 게임 실패! <br><br> 잘못된 숫자를 선택하였습니다.");
		                  wrongNumber.setFont(new Font("배달의민족 주아", Font.PLAIN, 16));
		                        
		                  JOptionPane.showMessageDialog(null, wrongNumber);
		                  timer.cancel(); // 잘못된 버튼 눌러서 실패 시 타이머 종료
		                  
		                  // stage 값은 변경하지 않는다.
//		                  String num = test.getText();
//		                  int stage2 = Integer.parseInt(num);
//		                  stage2++;
		    				
		                  // 호감도 20 감소
		                  String lovePointnum = lovePointLabel.getText();
		                  int lovePoint = Integer.parseInt(lovePointnum);
		                  lovePoint -= 20;
		                  
		                  System.out.println("종료");
		                  dispose();
		               
		                  /* 다음 스테이지 호출 */
		                  new chap03Narration1(name, stage, lovePoint);
		                  }
		               
					}	
	    	});
	    	gamePan.add(button); // 게임 패널에 버튼 추가
		}
		
	    pan.add(gamePan); // 핑크 패널에 게임 패널 추가
	    
	    /* close 기능 */
	    // 팀 close 이미지 추가
	    Image  closeImg = new ImageIcon("image/close.png").getImage().getScaledInstance(50,50,0);
	    JLabel closewords = new JLabel(new ImageIcon(closeImg));
	    closewords.setSize(50, 50);
	    closewords.setLocation(900, 0);
	    pan.add(closewords);
	    
	    // close 투명버튼 및 기능 추가
	    JButton closeBtn = new JButton();
	    closeBtn.setSize(50, 50);
	    closeBtn.setLocation(910,10);
	    closeBtn.setBorderPainted(false); // 버튼 선 없애기
        closeBtn.setContentAreaFilled(false); // 투명 처리
        
	    closeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// 타이머 멈춰주기
				timer.cancel();
				// warning view의 메소드 사용하여 종료(warning) 팝업 띄움
				new warning(name, stage, lovePoint);
				
			}
		
	    });
	    
	    /* 메인 프레임 추가 요소 및 기본 설정 */
	    
        this.add(closeBtn);
	    this.add(gamePan);
	    this.add(gameBackground);
	    this.add(pan);
	    
	    this.setVisible(true);
	    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    
	}




}

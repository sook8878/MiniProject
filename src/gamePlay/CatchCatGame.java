package gamePlay;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.mini.view.chap04Narration1;
import com.mini.view.warning;


public class CatchCatGame extends JFrame  {
	
	// 게임버튼 변수
		private JButton cat = new JButton();
		private JButton chiwawa[] = new JButton[2];

		private int k = 0; //치와와 1
		private int i = 1; // 치와와 2

		// 닫기 버튼 변수
		private JButton btnClose;
		
		// 게임 안의 라벨 변수
		private static int catchCount = 0;
		private static int leftLife = 3;
		private JLabel countCatLabel;
		private JLabel countLeftHeartLabel;
		// 게임판 이미지용 라벨
	    private  JLabel backStreetImgLabel;
	    
		//타이머 변수
		static int count = 0;
		static int time = 31;

		//기본 생성자로 생성
		public CatchCatGame(String name, int stage, int lovePoint) {


			/* 1. 메인 프레임 생성 */
			// 프레임 이름 넣기
			super(" * 개조심 *");
			//프레임 생성위치 및 크기 지정
			this.setSize(1000, 680);
			// 리사이즈 불가
			this.setResizable(false); 
			//창이 가운데 나오게
			setLocationRelativeTo(null);
			this.setLayout(null);
			
			// 게임 시작 전 팝업
			JLabel BeforeGame = new JLabel("<HTML><body>고양이 잡기 게임 : <br><br>고양이 15 마리 잡으면 게임성공,<br> 시간 초과하거나<br> 성난 치와와 3번 잘못 잡으면 실패!!</body></HTML>");
		    BeforeGame.setFont(new Font("배달의민족 주아", Font.PLAIN, 16));
		    JOptionPane.showMessageDialog(null, BeforeGame,"지갑을 훔쳐간 고양이를 잡아라!!",JOptionPane.QUESTION_MESSAGE );


			///*2.*/프레임 뒷배경 색깔용 패널생성
			JPanel backGround = new JPanel();
			Color b = new Color(255,225,235);
			backGround.setBackground(b);
			backGround.setLocation(10, 10);
			backGround.setSize(965,620); // 프레임 크기에 맞게 지정
			backGround.setLayout(null);
			
			
			
		
			// /*3.*/backGround패널에 이미지 넣기
			// 이미지를 라벨에 넣는다.
		    Image backStreetImg = new ImageIcon("imageCatGame/bst1.jpg").getImage();
		    backStreetImg.getScaledInstance(10, 10, Image.SCALE_SMOOTH);
		    backStreetImgLabel = new JLabel(new ImageIcon(backStreetImg));
		    
		    backStreetImgLabel.setSize(900, 600);
		    backStreetImgLabel.setLocation(32, 70);
		    backGround.add(backStreetImgLabel);
		    
		    // 벛꽃이미지 ~~! 
		    Image cherryblosomImg = new ImageIcon("imageCatGame/cherryblossom.png").getImage();
		    cherryblosomImg.getScaledInstance(10, 10, Image.SCALE_SMOOTH);
		    JLabel cherryLabel = new JLabel(new ImageIcon(cherryblosomImg));
		    cherryLabel.setSize(50, 50);
		    cherryLabel.setLocation(213, 63);
		    backGround.add(cherryLabel);
		    //2
		    Image cherryblosomImg2 = new ImageIcon("imageCatGame/cherryblossom2.png").getImage();
		    JLabel cherryLabel2 = new JLabel(new ImageIcon(cherryblosomImg2));
		    cherryLabel2.setSize(50, 50);
		    cherryLabel2.setLocation(533, 65);
		    backGround.add(cherryLabel2);
		    //3
		    Image cherryblosomImg3 = new ImageIcon("imageCatGame/cherrybla3.png").getImage();
		    JLabel cherryLabel3 = new JLabel(new ImageIcon(cherryblosomImg3));
		    cherryLabel3.setSize(50, 50);
		    cherryLabel3.setLocation(231, 12);
		    backGround.add(cherryLabel3);
		    //4
		    Image cherryblosomImg4 = new ImageIcon("imageCatGame/sakura2.png").getImage();
		    JLabel cherryLabel4 = new JLabel(new ImageIcon(cherryblosomImg4));
		    cherryLabel4.setSize(110, 110);
		    cherryLabel4.setLocation(720, 52);
		    backGround.add(cherryLabel4);
		    

			/*4.*/
			// 게임이름용 라벨
			JLabel namePanelLabel = new JLabel("지갑을 훔쳐간 고양이를 잡아라!!");
			namePanelLabel.setFont(new Font("배달의민족 주아", Font.BOLD, 35));
			namePanelLabel.setBounds(280, 10, 450, 50);
			namePanelLabel.setForeground(Color.PINK);
			backGround.add(namePanelLabel);
			
			
			// 남은 고양이 수 알려주는 라벨 =============================================================
			countCatLabel = new JLabel();
			countCatLabel.setBounds(270, 80, 250, 40);
			countCatLabel.setFont(new Font("배달의민족 주아", Font.BOLD, 24));
			countCatLabel.setText("잡은 고양이 수 : " + catchCount +" 마리");
			this.add(countCatLabel);

			
			// 남은 목숨 알려주는 라벨================================================================
			countLeftHeartLabel = new JLabel();
			countLeftHeartLabel.setBounds(590, 80, 250, 40);
			countLeftHeartLabel.setFont(new Font("배달의민족 주아", Font.BOLD, 24));
			countLeftHeartLabel.setText("남은 목숨 : " + leftLife);
			this.add(countLeftHeartLabel);
			
			//================================================

			
			
			// 시계 이미지 추가================================================

			Image watchImg = new ImageIcon("imageCatGame/stopwc1.png").getImage();
			watchImg.getScaledInstance(10, 10, Image.SCALE_SMOOTH);
			JLabel watchImgLabel = new JLabel(new ImageIcon(watchImg));
			watchImgLabel.setSize(80, 80);
			watchImgLabel.setLocation(15, 17);
			backGround.add(watchImgLabel);
			//================================================

			
			// 시간용 타이머================================================
			// 30 초 1초마다 1초--
			JLabel timeLabel = new JLabel();
			timeLabel.setBounds(95, 35, 70, 70);
			//timeLabel.setOpaque(true); // 라벨에 색깔 설정해주려면 이걸ture
			
			timeLabel.setForeground(Color.BLACK);
			timeLabel.setText(time+" 초");
		
			timeLabel.setFont(new Font("배달의민족 주아", Font.BOLD,28));
			
			this.add(timeLabel);
			
			Timer timer = new Timer();
			
			TimerTask timerTask = new TimerTask() {
				
				@Override
				public void run() {
					
					if(time > 10) {
						time--;
						timeLabel.setText(time+" 초");

					}else if(time <= 10 && time > 0){
						timeLabel.setForeground(Color.MAGENTA);
						time--;
						timeLabel.setText(time+" 초 !");
					}
					else if(time ==0) {
						timeLabel.setBounds(95, 0, 100, 140);
						timeLabel.setFont(new Font("배달의민족 주아", Font.BOLD,23));
						timeLabel.setText("시간 초과!!");
						// 게임 종료시 버튼 사라지게 하는
						cat.setVisible(false);
						chiwawa[k].setVisible(false);
						chiwawa[i].setVisible(false);
						// 타이머 멈추게 하기
						timer.cancel();
						

						// 시간초과 시 경고창 ======================================== 호감도 -- =====================
						int timeEndGameLovePoint = lovePoint - 20;
						JLabel TimeEnd = new JLabel("시간초과 !! 고양이가 지갑을 물고 도망갔습니다!");
						TimeEnd.setFont(new Font("배달의민족 주아", Font.PLAIN, 15));
						      
							      
				        JOptionPane.showMessageDialog(null, TimeEnd,"실패 ㅠㅠ",JOptionPane.ERROR_MESSAGE);
						new chap04Narration1(name,stage,timeEndGameLovePoint);
						dispose();
						
					}

				}
			};
			timer.schedule(timerTask, 0, 1000);
			//===============================
			
			
			// 닫기 버튼 추가
			Image closeImg = new ImageIcon("imageCatGame/close.png").getImage();
			closeImg.getScaledInstance(10, 10, Image.SCALE_SMOOTH);
			JLabel closeLabel = new JLabel(new ImageIcon(closeImg));
			closeLabel.setSize(50, 50);
			closeLabel.setLocation(900, 0);
			backGround.add(closeLabel);
			
			// 닫기 기능 추가
			btnClose = new JButton();
			btnClose.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0)  {
					
				      // 종료시 warning 메소드 불러옴 ==========================================================================
					warning wn = new warning(name,stage,lovePoint);

					timer.cancel(); // 타이머 꺼주어야 함. 아니면 시간초과 팝업창이 뜸.
				}
			});
			btnClose.setLocation(900, 16);
			btnClose.setSize(52,35);
			btnClose.setBorderPainted(false); // 버튼의 외곽선을 없애준다
			btnClose.setContentAreaFilled(false); // 투명 버튼 만들기!!
			backGround.add(btnClose);
			//================================================

			// 고양이 버튼 이미지 넣고 생성하기
			
				
				cat = new JButton(new ImageIcon("imageCatGame/cat.jpg"));
				cat.setSize(60,40);
				
				cat.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						catchCount++;
						countCatLabel.setText("잡은 고양이 수 : " + catchCount + " 마리");

						// 게임 승리 조건
						if(catchCount == 15) { 
							// 게임 종료시 버튼 사라지게 하는
							cat.setVisible(false);
							chiwawa[k].setVisible(false);
							chiwawa[i].setVisible(false);
							// 타이머 멈추게 하기
							timer.cancel();
							
							// 게임성공시 호감도 ++ =====================================================
							
							int successGameLovePoint = lovePoint + 20;
						    JLabel EnoughCatchCat = new JLabel("지갑을 찾았습니다!!!");
						    EnoughCatchCat.setFont(new Font("배달의민족 주아", Font.PLAIN, 15));
						      
						    JOptionPane.showMessageDialog(null, EnoughCatchCat,"성공!!!!",JOptionPane.INFORMATION_MESSAGE);
						      
						    dispose();
						     
						    new chap04Narration1(name,stage,successGameLovePoint);
							
						}
			
					}
					});

			// 치와와 버튼 배열 생성
			for(k = 0; k < chiwawa.length; k++){
				chiwawa[k] = new JButton(new ImageIcon("imageCatGame/chiwawa.jpg"));
				chiwawa[k].setSize(60,40);
				
				chiwawa[k].addActionListener(new ActionListener() {
					
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
					

						leftLife--;
						countLeftHeartLabel.setText("남은 목숨 : " + leftLife);
						if(leftLife == 0) {
							// 게임 종료시 버튼 사라지게 하는 // 게임 실패 호감도 -- ===================================================
							cat.setVisible(false);
							chiwawa[k].setVisible(false);
							chiwawa[i].setVisible(false);
							// 타이머 멈추게 하기
							timer.cancel();
							
							int failGameLovePoint = lovePoint - 20;
							
							JLabel BeforeGame = new JLabel("치와와한테 뜯겨 너덜너덜 해졌습니다..");
						    BeforeGame.setFont(new Font("배달의민족 주아", Font.PLAIN, 15));
						      
						    JOptionPane.showMessageDialog(null, BeforeGame,"실패 ㅜㅜ",JOptionPane.ERROR_MESSAGE);
						    
						    new chap04Narration1(name,stage,failGameLovePoint);

							
							dispose();
						}
					}	
					});
				
				
			}
			//==================================================
			// 고양이 버튼 생성
			Timer timerCreateButton = new Timer();
			TimerTask timerTask2 = new TimerTask() {

				@Override
				public void run() {
					int x= (int)(Math.random()*800)+ 50;
					int y = (int)(Math.random()*290)+180;

					//  run마다 덮어 씌워짐
						cat.setLocation(x, y);
						
						backStreetImgLabel.add(cat);

				}
			
			};
			timerCreateButton.schedule(timerTask2, 0,590);
			
			// 1번째 치와와 
			Timer timerChiwawaPlus = new Timer();
			TimerTask timerTask3 = new TimerTask() {

				@Override
				public void run() {
					int x= (int)(Math.random()*800)+ 50;
					int y = (int)(Math.random()*290)+180;
					
						k=0;
						chiwawa[k].setLocation(x, y);
						backStreetImgLabel.add(chiwawa[k]);

					}
			
			};
			timerChiwawaPlus.schedule(timerTask3, 0,500);
			
			//2번째 치와와
			Timer timerChiwawaPlus2 = new Timer();
			TimerTask timerTask4 = new TimerTask() {

				@Override
				public void run() {
					int x= (int)(Math.random()*800)+ 50;
					int y = (int)(Math.random()*290)+180;

						i=1;
						chiwawa[i].setLocation(x, y);
						backStreetImgLabel.add(chiwawa[i]);
									
					}
			
			};
			timerChiwawaPlus2.schedule(timerTask4, 0,550);
			//=========================

			this.add(timeLabel);
			
			// 뒷배경용 패널 맨 마지막에 add
			this.add(backGround);
			
			// 프레임 보이기
			this.setVisible(true);
			
			// 프레임 종료와동시에 메모리 종료
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

		
	
	public static void main(String[] args) {
		new CatchCatGame("1",3,3);


	}



}

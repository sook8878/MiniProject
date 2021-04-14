package gamePlay;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import com.mini.view.chap02Narration0;
import com.mini.view.warning;

public class CardGame extends JFrame {

	/* 필요 변수 선언 */
	static JPanel pan;
	static JPanel panelNorth; 	//Top View
	static JPanel panelCenter;  //Game View
	static JLabel labelMessage; //게임 제목
	static JLabel clock;  		//타이머 시계 이미지
	static JLabel timeLabel;    //타이머 시간
	
	static JButton clsbtn;
	static JButton[] cardbtn = new JButton[16]; //버튼 초기화 배열
	static String[] images = {
			"1.png","2.png","3.png","4.png","5.png","6.png","7.png","8.png",
			"1.png","2.png","3.png","4.png","5.png","6.png","7.png","8.png",
	};
	
	static int openCount = 0; 	 //버튼을 눌렀을 때 카드가 열린 횟수(0,1,2)
	static int buttonIndex1 = 0; //첫 번째 카드 오픈(0~15의 값 가짐)
	static int buttonIndex2 = 0; //두 번째 카드 오픈(0~15의 값 가짐)
	static int successCount = 0; //맞춘 횟수(0~8의 값 가짐)
	
	static Color b = new Color(255, 225, 235); //바탕패널 컬러
	
	static Timer openTime; //카드가 보여지는 시간 설정
	static java.util.Timer playTimer = new java.util.Timer(); //게임 진행 타이머 생성
	static int sec = 40; //40초

	static Dialog fail;
	static Dialog success;
	static JLabel message;
	static JButton ok;
	public static int moveChap = 0; 
	
	/* 게임프레임 설정, 카드버튼 기능 설정 */
	public static class GameFrame extends JFrame {
		
		public GameFrame(String name, int stage, int lovePoint) { //게임프레임 설정
			
			this.setTitle("시뮬레이션");
			this.setSize(1000, 680); //프레임 크기
			this.setLayout(null); //위치를 직접 지정해서 배치
			this.setResizable(false); //창 크기 변경 불가
		    this.setLocationRelativeTo(null);
			
		    //gameUI(this);
		    
			pan = new JPanel(); //바탕패널 생성
			pan.setBounds(10, 10, 965, 620); //setBounds(가로위치, 세로위치, 가로길이, 세로길이)
			pan.setLayout(null);
			pan.setBackground(b);
			
			Image closeImg = new ImageIcon("image/close.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); //종료버튼 이미지 받아오기
			clsbtn = new JButton(new ImageIcon(closeImg));
			clsbtn.setBounds(900, 0, 50, 50); //종료버튼 위치, 크기
			clsbtn.addActionListener(new ActionListener() { //종료이벤트
				@Override
				public void actionPerformed(ActionEvent e) {
				
					warning fin = new warning(name, stage, lovePoint);
					}
			});
			
			clsbtn.setBorderPainted(false); 	//종료버튼 외곽선 제거
			clsbtn.setFocusPainted(false); 		//종료버튼 선택 시 생기는 테두리 사용 안함 설정
			clsbtn.setContentAreaFilled(false); //종료버튼 배경 제거(투명)
			
			panelNorth = new JPanel(); //게임제목 패널
			panelNorth.setBackground(Color.WHITE);
			panelNorth.setBounds(240,30,500,50); //게임제목 패널 위치, 크기
			
			labelMessage = new JLabel("Mini Game"); //게임제목 텍스트
			labelMessage.setBackground(Color.black);
		
			panelCenter = new JPanel();
			panelCenter.setLayout(new GridLayout(4,5,3,3)); //격자형태 만들기 (가로,세로,버튼 간 간격)
			panelCenter.setBounds(240,43,500,560); //버튼이 들어가는 패널의 크기 지정
			panelCenter.setBackground(b);
			
			for(int i = 0; i < 16; i++) { //카드버튼의 뒷면 이미지 저장
				cardbtn[i] = new JButton();
				cardbtn[i].setIcon(changeImage("back.png")); //둥근이미지
				cardbtn[i].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {//카드버튼 이벤트
						
						if(openCount == 2) { //카드가 2번 눌리면 더이상 눌리지 않도록 함
							return;
						}
						
						JButton btn = (JButton)e.getSource(); //눌린 버튼의 위치 저장(getSource() : 이벤트를 발생시킨 객체의 위치값을 가져옴)
						int index = getButtonIndex(btn); //눌린 버튼의 인덱스 저장
						btn.setIcon(changeImage(images[index])); //0번 버튼에 0번째 사진이 들어감
						
						openCount++;
						
						if(openCount == 1) { //첫 번째 카드 눌렀을 때
							buttonIndex1 = index; //인덱스 값을 저장
						}
						else if(openCount == 2) { //두 번째 카드 눌렀을 때
							buttonIndex2 = index; //인덱스 값을 저장

							//카드가 2장 다 눌렸음 -> 카드가 일치하는지 아닌지 판정
							boolean success = checkCard(buttonIndex1,buttonIndex2);
							if(success == true) {
								
								if(successCount < 8) { //전부 맞췄을 경우 마지막 한쌍이 다시 뒤집어지는 것을 막음
									openCount = 0; //카드 2번 열림을 초기화
								}
								
								successCount++; //성공횟수 1 증가
								
								if(successCount == 8) { //카드 8쌍 다 맞춤
									moveChap = 1;
									playTimer.cancel(); //타이머 종료시킴
									successView(); //성공 팝업창
									dispose(); //게임프레임 종료
									move(name, stage, lovePoint);
								}
							} 
							else {
								backToGame(); //아니면 다시 게임으로 돌아감
							}
						}
					}
				});
				panelCenter.add(cardbtn[i]);
				
				cardbtn[i].setBorderPainted(false); 	//카드 외곽선 제거
				cardbtn[i].setFocusPainted(false); 		//카드 선택 시 생기는 테두리 사용 안함 설정
				cardbtn[i].setContentAreaFilled(false); //카드 배경 제거(투명)
			}
			
			Image clockImg = new ImageIcon("image/clock.png").getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH); //타이머 이미지 받아오기
			clock = new JLabel(new ImageIcon(clockImg));
			clock.setBounds(11, 3, 45, 45);
			
			timeLabel = new JLabel();
			timeLabel.setLocation(65,2); //타이머 라벨 위치 지정
			timeLabel.setSize(50,50); //타이머 라벨 사이즈 지정
			timeLabel.setFont(new Font("배달의민족 주아", Font.PLAIN, 17));

			TimerTask timerTask = new TimerTask() { //타이머가 수행할 기능 생성
				@Override
				public void run() {
					
					timeLabel.setText(sec + "초");
					sec--; //1초 타이머
					
					if(sec < 0 ) { //타이머가 종료되면
						playTimer.cancel(); //타이머 종료시킴
						failView(); //실패 팝업창
						dispose();
						moveChap = 2;
						move(name, stage, lovePoint);
					}
				}
			};
			playTimer.schedule(timerTask, 0, 1000); //타이머가 task를 실행, 지연시간 0, 1000ms마다 반복
			
			pan.add(timeLabel);     	  //바탕패널에 시간 추가
			pan.add(clock);		  		  //바탕패널에 시계 이미지 추가
			panelNorth.add(labelMessage); //게임타이틀 문구 추가
			pan.add(clsbtn);			  //바탕패널에 클로즈 버튼 추가
			this.add(panelCenter);   	  //프레임에 카드판 추가
			//gameframe.add(panelNorth);  //프레임에 타이틀 패널 추가
			this.add(pan); 		 		  //프레임에 바탕패널 추가
		    
		    mixCard(); //카드를 랜덤하게 섞음		    
		    
			this.setVisible(true); //화면이 보여지도록
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //닫으면 깔끔히 종료

		}

		protected void move(String name, int stage, int lovePoint) {

		    if(moveChap == 1) { //게임성공시
		    	lovePoint += 20;
		    	new chap02Narration0(name, stage, lovePoint); //호감도 증가 후 이동
		    	
		    } else if (moveChap == 2) { //게임실패시
		    	lovePoint -= 20;
		    	new chap02Narration0(name, stage, lovePoint); //호감도 감소 후 이동
		    } 	
		}

		public int getButtonIndex(JButton btn) { //몇 번째 버튼이 눌렸는지 확인
			int index = 0;
			
			for(int i=0; i<16; i++) {	
				if(cardbtn[i] == btn) { //누른 버튼의 위치(btn)와 버튼(cardbtn[i])이 같다면 인덱스 반환
					index = i;
				}
			}
			return index;
		}
		

		public boolean checkCard(int index1, int index2) { //카드 2장이 짝인지 확인
			
			if(index1 == index2) { //버튼의 인덱스가 같으면 false 반환(같은 버튼을 눌렀다는 의미)
				return false;
			}
			if(images[index1].equals(images[index2])) { //인덱스의 이미지가 같으면 true 반환(파일명으로 비교)
				return true;
			} else { 
				return false;
			}
		}

		private void backToGame() { //카드 2장이 짝이 아닐 경우
			
			//타이머 - 1초 뒤에 틀린 카드 2장을 다시 뒤집음
			openTime = new Timer(300, new ActionListener() { //1000ms = 1s
				
				@Override
				public void actionPerformed(ActionEvent e) {

					openCount = 0; //카드 오픈 횟수 초기화
					cardbtn[buttonIndex1].setIcon(changeImage("back.png")); //카드 이미지는 다시 뒷면으로 변경
					cardbtn[buttonIndex2].setIcon(changeImage("back.png"));
					openTime.stop(); //1초 뒤 종료, 설정 안하면 무한히 돌아감
				}
			});
			openTime.start();
		}
	}
	
	/* 랜덤하게 카드 섞기 */
	static void mixCard() { 
		
		Random rand = new Random();
		
		for(int i = 0; i < 1000; i++) {
			int random = rand.nextInt(15) + 1; //1~15
			
			String temp = images[0]; //첫 번째 카드와 나머지 카드를 섞는 방식
			images[0] = images[random];
			images[random] = temp;
		}
	}
	
	/* 카드 앞면이미지 변경 메소드 
	 * (이미지 이름을 주면, scale을 해서 이미지 크기를 조정한 다음 가져옴)
	 */
	static ImageIcon changeImage(String filename) {
		ImageIcon icon = new ImageIcon("image/" + filename);
		Image originImage = icon.getImage();
		Image changedImage = originImage.getScaledInstance(125, 140, Image.SCALE_SMOOTH); //이미지크기
		ImageIcon icon_new = new ImageIcon(changedImage);
		return icon_new; //이미지 사이즈 조절 할 수 있게
	}
	
//	/* 종료 팝업창 */
//	static void Warning() {
//		   
//		JLabel warning = new JLabel("게임을 종료하시겠습니까?");
//		warning.setFont(new Font("배달의민족 주아", Font.PLAIN, 15));
//	            
//	    int result = JOptionPane.showConfirmDialog(null,warning,"Warning",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
//	            
//	    if(result == JOptionPane.YES_OPTION) {
//			playTimer.cancel(); //타이머 종료시킴
//			dispose();
//	    }
//	}
	
	/* 시작 팝업창 */
	public static void startView() {
		
		JLabel start = new JLabel("<HTML><body>카드 맞추기 게임<br><br>같은 카드 2개를 골라 짝을 찾아라 !<br>제한시간 40초 안에 모두 맞추면 성공!!<br><br></body></HTML>");
		start.setFont(new Font("배달의민족 주아", Font.PLAIN, 16)); //내용 폰트 설정
		
		JOptionPane fin = new JOptionPane(); //시작 팝업창
		fin.showMessageDialog(null,start,"Strat Game",JOptionPane.QUESTION_MESSAGE);
		
		/* 
		showMessageDialog(null,내용,타이틀,아이콘);
		showConfirmDialog(null,내용,타이틀,버튼옵션,아이콘);
		- 아이콘 종류
		X : JOptionPane.ERROR_MESSAGE
		i : JOptionPane.INFORMATION_MESSAGE
		! : JOptionPane.WARNING_MESSAGE
		? : JOptionPane.QUESTION_MESSAGE
		아이콘 표시 없음: JOptionPane.PLAIN_MESSAGE / 
		*/
	}
	
	/* 성공 팝업창 */
	static void successView() {
		
		JLabel success = new JLabel("카드 맞추기 성공 !",SwingConstants.CENTER);
		success.setFont(new Font("배달의민족 주아", Font.PLAIN, 16));
		
		JOptionPane fin = new JOptionPane(); //성공 팝업창
		fin.showMessageDialog(null,success,"Finish",JOptionPane.PLAIN_MESSAGE);
	}
	
	/* 실패 팝업창 */
	static void failView() {
		JLabel fail = new JLabel("카드 맞추기 실패 ㅠ_ㅠ",SwingConstants.CENTER);
		fail.setFont(new Font("배달의민족 주아", Font.PLAIN, 16));
		
		JOptionPane fin = new JOptionPane(); //실패 팝업창
		fin.showMessageDialog(null,fail,"Finish",JOptionPane.PLAIN_MESSAGE);
	}
	
//	/* main 메소드 */
//	public static void main(String[] args) {
//		
//		startView(); //시작팝업
//		GameFrame game = new GameFrame("name", 1, 50);
//	}
}
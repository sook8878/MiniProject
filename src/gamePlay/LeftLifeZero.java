package gamePlay;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.mini.view.chap04Narration1;

public class LeftLifeZero extends JFrame{
	
	public LeftLifeZero() {
	      
		JLabel BeforeGame = new JLabel("치와와한테 뜯겨 너덜너덜 해졌습니다..");
	      BeforeGame.setFont(new Font("배달의민족 주아", Font.PLAIN, 15));
	      
	       JOptionPane.showMessageDialog(null, BeforeGame,"실패 ㅜㅜ",JOptionPane.ERROR_MESSAGE);
	       // 주인공 사망///
	       new chap04Narration1();


}
public static void main(String[] args) {
	new LeftLifeZero();
}

}
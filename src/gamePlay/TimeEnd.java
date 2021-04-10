package gamePlay;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class TimeEnd extends JFrame{
	
	public TimeEnd() {

	    JLabel TimeEnd = new JLabel("시간초과 !! 고양이가 지갑을 물고 도망갔습니다!");
	    TimeEnd.setFont(new Font("배달의민족 주아", Font.PLAIN, 15));
	      
		      
		JOptionPane.showMessageDialog(null, TimeEnd,"실패 ㅠㅠ",JOptionPane.ERROR_MESSAGE);


}
 public static void main(String[] args) {
	new TimeEnd();
}

}

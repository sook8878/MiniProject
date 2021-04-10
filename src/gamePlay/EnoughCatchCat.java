package gamePlay;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class EnoughCatchCat extends JFrame {
	public EnoughCatchCat() {
	      
	      JLabel EnoughCatchCat = new JLabel("지갑을 찾았습니다!!!");
	      EnoughCatchCat.setFont(new Font("배달의민족 주아", Font.PLAIN, 15));
	      
	      JOptionPane.showMessageDialog(null, EnoughCatchCat,"성공!!!!",JOptionPane.INFORMATION_MESSAGE);
	      // 호감도 ++

	     


}
	
public static void main(String[] args) {
	new EnoughCatchCat();
}

}


package com.mini.view;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class warning {
	
	public warning()
	
	public warning(int stage, int lovePoint) {
		
		JLabel warning = new JLabel("프로그램을 종료하시겠습니까?");
		warning.setFont(new Font("배달의민족 주아", Font.PLAIN, 15));
		
		int result = JOptionPane.showConfirmDialog(null,warning,"경고문",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
		
		if(result == JOptionPane.YES_OPTION) {
			System.out.println("YES");
			System.exit(0);
		} else if(result == JOptionPane.NO_OPTION) {
			System.out.println("NO");
		} else {
			
		}
		
	}

}

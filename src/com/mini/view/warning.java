package com.mini.view;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.mini.controller.UserController;

public class warning {
	
	public warning() {
		
		JLabel warning = new JLabel("프로그램을 종료하시겠습니까?");
		warning.setFont(new Font("배달의민족 주아", Font.PLAIN, 15));
		
		int result = JOptionPane.showConfirmDialog(null,warning,"경고문",
				                 JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
		
		if(result == JOptionPane.YES_OPTION) {
			System.out.println("YES");
			System.exit(0);
		} else if(result == JOptionPane.NO_OPTION) {
			System.out.println("NO");
		} else {
			
		}
		
	}
	
	public warning(String name, int stage, int lovePoint) {
		
		JLabel warning = new JLabel("프로그램을 종료하시겠습니까?");
		warning.setFont(new Font("배달의민족 주아", Font.PLAIN, 15));
		
		int result = JOptionPane.showConfirmDialog(null,warning,"경고문",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
		
		if(result == JOptionPane.YES_OPTION) {
			if (!"".equals(name) && name != null) {
				//name이 비어있지 않고 name이 널이 아니면 db에 저장한다.
				new UserController().savaStage(name,stage,lovePoint);
			}
			System.out.println("YES");
			System.exit(0);
		} else if(result == JOptionPane.NO_OPTION) {
			System.out.println("NO");
		} else {
			
		}
		
	}

}

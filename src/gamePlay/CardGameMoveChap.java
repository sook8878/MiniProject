package gamePlay;

import com.mini.view.chap02Narration1;
import static gamePlay.CardGame.moveChap;

public class CardGameMoveChap {

	public static void move(String name, int stage2, int lovePoint) {

	    if(moveChap == 1) { //게임성공시
	    	lovePoint += 20;
	    	new chap02Narration1(name, stage2, lovePoint); //호감도 증가 후 이동
	    	
	    } else if (moveChap == 2) { //게임실패시
	    	lovePoint -= 20;
	    	new chap02Narration1(name, stage2, lovePoint); //호감도 감소 후 이동
	    } 	
	}
	

}

package com.mini.sound;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import oracle.net.aso.e;

public class SoundController {
	
	static Clip clip;

	public static void soundPlay(String fileUrl) {

		try {
			AudioInputStream stream = AudioSystem.getAudioInputStream
					                      (new File(fileUrl).getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(stream);
			clip.start();

		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void stop() {
		clip.stop();
		clip.close();
	}
}

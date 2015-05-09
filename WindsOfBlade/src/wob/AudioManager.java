package wob;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;

public class AudioManager {//TODO this sucks, fix it
	/*
	 *  Loads in all Audio on initialization
	 */
	public static Sequencer[] bgm;
	public static final int Duskypath = 0;
	public static final int EriePath = 1;
	public static final int HisMajesty = 2;
	public static final int ItsAnAdventure = 3;
	public static final int ListeningToAStory = 4;
	public static final int LostInMyBasement = 5;
	public static final int ThatHauntedHouse = 6;
	public static void LoadSounds(){
		File[] directory = new File("res/bgm").listFiles();
		bgm = new Sequencer[directory.length];
		for(int i=0;i<directory.length;i++){
			try{
				bgm[i]=MidiSystem.getSequencer();
				bgm[i].open();
				bgm[i].setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
				bgm[i].setSequence(new BufferedInputStream(new FileInputStream(new File(directory[i].toString()))));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public static void playBgm(int index){
		stopBgm();
		bgm[index].start();
		
	}
	public static void stopBgm(){
		for(Sequencer s:bgm){
			s.stop();
		}
	}
}

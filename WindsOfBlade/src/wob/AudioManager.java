package wob;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;

public class AudioManager {//TODO this sucks, fix it
	/*
	 *  Loads in all Audio on initialization
	 */
	private static Sequencer[] audio;
	private static String[] audioNames;
	private static LinkedList<String> tempNames = new LinkedList<String>();
	public static void playBgm(String filepath){
		int index = Arrays.binarySearch(audioNames,filepath);
		if(index<0){
			System.out.println("[WARNING] Missing or Invalid MIDI \""+filepath+"\"");
			return;
		}else
			audio[index].start();
	}
	public static void stopBgm(String filepath){
		int index = Arrays.binarySearch(audioNames,filepath);
		if(index<0){
			System.out.println("[WARNING] Missing or Invalid MIDI \""+filepath+"\"");
			return;
		}else
			audio[index].stop();
	}
	public static void stopAllBgm(){
		for(Sequencer s: audio)
			s.stop();
	}
	public static void LoadAudio(String root) throws IOException{
		loadAudioNames(root);
		audioNames = new String[tempNames.size()];
		audio = new Sequencer[audioNames.length];
		for(int i=0;i<audio.length;i++)
			audioNames[i]=tempNames.get(i);
		Arrays.sort(audioNames);
		for(int i=0;i<audio.length;i++){
			audio[i]=loadAudio(audioNames[i]);
		}
		tempNames.clear();
	}
	private static void loadAudioNames(String root){
		File imageResources = new File(root);
		for(String s: imageResources.list()){
			if(!s.contains("."))
				loadAudioNames(root+"/"+s);
			else
				tempNames.add(root+"/"+s);
		}
	}
	private static Sequencer loadAudio(String filepath){
		try{
			Sequencer s =MidiSystem.getSequencer();
			s.open();
			s.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
			s.setSequence(new BufferedInputStream(new FileInputStream(new File(filepath))));
			return s;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}

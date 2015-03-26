package wob;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;

public class AudioManager {
	/*
	 *  Loads in all Audio on initialization
	 */
	private static InputStream bgm;
	private static Sequencer sequencer;
	public static void LoadSounds(){
		try {
			sequencer = MidiSystem.getSequencer();
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			sequencer.open();
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bgm = new BufferedInputStream(new FileInputStream(new File("res/bgm/ItsAnAdventure.mid")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void play(){
		try {
			sequencer.setSequence(bgm);
		} catch (IOException | InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sequencer.start();
	}
}

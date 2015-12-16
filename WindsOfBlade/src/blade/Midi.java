package blade;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;

public class Midi implements Comparable<Midi>{
	Sequencer sequencer;
	File file;
	public Midi(File f){
		file=f;
		try{
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
			sequencer.setSequence(new BufferedInputStream(new FileInputStream(file)));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void start(){
		sequencer.start();
	}
	public void stop(){
		sequencer.stop();
	}
	@Override
	public int compareTo(Midi other) {
		return file.compareTo(other.file);
	}
	
}
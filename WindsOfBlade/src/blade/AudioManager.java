package blade;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * An Audio Manager
 * @author Brandon Wong
 */
public class AudioManager {
	private static Midi[] database;
	/**
	 * Loads all the files in the "sound" folder and puts them in a sorted array
	 * @see FileIO#loadFiles(String, LinkedList)
	 * @throws IOException
	 */
	public static void loadMIDIs() throws IOException{
		LinkedList<File> midiFiles = new LinkedList<File>();
		FileIO.loadFiles("sound", midiFiles);
		database = new Midi[midiFiles.size()];
		int i=0;
		for(File f:midiFiles){
			database[i] = new Midi(f);
			i++;
		}
		Arrays.sort(database);
	}
	/**
	 * Plays a specified MIDI file;
	 * @param filePath the file path of the MIDI file
	 */
	public static void playMIDI(String filePath){
		int index = Arrays.binarySearch(database, new File(filePath));
		if(index<0)
			return;
		database[index].start();
	}
	/**
	 * Stops playing a specified MIDI file
	 * @param filePath the file path of the MIDI file
	 */
	public static void stopMIDI(String filePath){
		int index = Arrays.binarySearch(database, new File(filePath));
		if(index<0)
			return;
		database[index].stop();
	}
	/**
	 * Stops playing all MIDIs
	 */
	public static void stopAllMIDI(){
		for(Midi s:database)
			s.stop();
	}
}

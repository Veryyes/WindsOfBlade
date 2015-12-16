package blade;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class FileIO {
	/**
	 * Copies all the File objects into LinkedList filepaths 
	 * @param rootDirectory the directory (including sub-directories) that will be searched through
	 * @param filepaths the LinkedList<File> that will contain all the File objects to every file in rootDirectory
	 * @throws IOException
	 */
	public static void loadFiles(String rootDirectory, LinkedList<File> filepaths) throws IOException{
		File root = new File(rootDirectory);
		for(String str:root.list()){
			if(new File(rootDirectory+"/"+str).isDirectory())
				loadFiles(rootDirectory+"/"+str, filepaths);
			else
				filepaths.add(new File(rootDirectory+"/"+str));
		}
	}
	/*
	 * TODO SAVING AND LOADING
	 */
}
